/* ----------------------------------------------------------------------------
    Test ontology slimmer locally
   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 07-Jul-2016
 */
package eNM;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.RDFXMLDocumentFormat;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.AddOntologyAnnotation;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLImportsDeclaration;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.RemoveImport;
import org.semanticweb.owlapi.model.SetOntologyID;
import org.semanticweb.owlapi.search.EntitySearcher;
import org.semanticweb.owlapi.search.Searcher;
import org.semanticweb.owlapi.util.OWLEntityRemover;
import org.semanticweb.owlapi.util.OWLOntologyMerger;
import org.semanticweb.owlapi.util.SimpleIRIMapper;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;

public class LSlimmer {

    private OWLOntologyManager man;
    private OWLOntology onto;

    public LSlimmer(File owlFile, String mergedOntologyIRI) throws OWLOntologyCreationException, FileNotFoundException {
        this(new FileInputStream(owlFile), mergedOntologyIRI);
    }

    public LSlimmer(InputStream owlFile) throws OWLOntologyCreationException {
        this(owlFile, null);
    }

    public LSlimmer(InputStream owlFile, String mergedOntologyIRI) throws OWLOntologyCreationException {
        man = OWLManager.createConcurrentOWLOntologyManager();
        onto = man.loadOntologyFromOntologyDocument(owlFile);

        if (System.getenv("WORKSPACE") != null) {   // Gets the value of the specified environment variable
            String root = System.getenv("WORKSPACE");
            System.out.println("Adding mappings with root: " + root);
            addMappings(man, root); //add local owl file to the URL mapping
        }

        if (mergedOntologyIRI != null) {
            // Load all of the DIRECT IMPORTs ontologies
            Set<OWLImportsDeclaration> importDeclarations = onto.getImportsDeclarations();
            for (OWLImportsDeclaration declaration : importDeclarations) {
                try {
                    man.loadOntology(declaration.getIRI());
                    System.out.println("Loaded imported ontology: " + declaration.getIRI());

                } catch (Exception exception) {
                    exception.printStackTrace();
                    System.out.println("Failed to load imported ontology: " + declaration.getIRI());
                }
            }

            // merge ontologies, specifying an IRI for the new ontology
            OWLOntologyMerger merger = new OWLOntologyMerger(man);
            onto = merger.createMergedOntology(man, IRI.create(mergedOntologyIRI));
            for (OWLOntology ontology : man.getOntologies()) {
                System.out.println(" Copying annotations from " + ontology.getOntologyID());

                for (OWLAnnotation annotation : ontology.getAnnotations()) {
                    System.out.println(" Copying annotation: " + annotation.getProperty() + " -> " + annotation.getValue());
                    AddOntologyAnnotation annotationAdd = new AddOntologyAnnotation(onto, annotation);
                    man.applyChange(annotationAdd);
                }
            }
        }

    }

    public static void main(String[] args) {
        boolean allSucceeded = true;
        String rootFolder = args[0]; //    <<<< ===================
        System.out.println("Searching configuration files in folder " + rootFolder);
        //Searching configuration files in .

        File dir = new File(rootFolder);
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".props");
            }
        });

        for (File file : files) {  // for each property file
            try {
                System.out.println("Slimming for  " + file.getName());
                Properties props = new Properties();
                props.load(new FileReader(file));

                //---------- source ------------------------------------ 
                String owlURL = props.getProperty("owl");
                String owlFilename = owlURL;
                if (owlFilename.contains("/")) {
                    owlFilename = owlFilename.substring(owlFilename.lastIndexOf('/') + 1);
                }

                //---------- output -----------------------------------
                String slimmedURL = props.getProperty("slimmed");
                String slimmedFilename = slimmedURL;
                if (slimmedFilename.contains("/")) {
                    slimmedFilename = slimmedFilename.substring(slimmedFilename.lastIndexOf('/') + 1);
                }

                //----------- iris ------------------------------------
                String irisFilename = props.getProperty("iris");

                File owlFile = new File(owlFilename);
                Slimmer slimmer = new Slimmer(owlFile, slimmedFilename);
                OWLOntology onto = slimmer.getOntology();

            } catch (Exception e) {
                e.printStackTrace();
                allSucceeded = false;
            }
        }
    }

    public OWLOntology getOntology() {
        return this.onto;
    }

    /**
     * Helper method that returns a collection sup/sub classes of the given
     * class.
     *
     * @param clazz
     * @param onto
     * @return
     */
    private Set<String> allSuperClasses(OWLClass clazz, OWLOntology onto) {
        Set<String> allSuperClasses = new HashSet<String>();
        Collection<OWLClassExpression> superClasses = Searcher.sup(onto.getSubClassAxiomsForSubClass(clazz));
        for (OWLClassExpression superClass : superClasses) {
            OWLClass superOwlClass = superClass.asOWLClass();
            String superIri = superOwlClass.getIRI().toString();
            allSuperClasses.add(superIri);
            // recurse
            allSuperClasses.addAll(allSuperClasses(superOwlClass, onto));
        }
        return allSuperClasses;
    }

    private Set<String> allSubClasses(OWLClass clazz, OWLOntology onto) {
        Set<String> allSubClasses = new HashSet<String>();
        System.out.println("clazz: " + clazz);
        Collection<OWLClassExpression> subClasses = Searcher.sub(onto.getSubClassAxiomsForSuperClass(clazz));
        System.out.println("subclass count: " + subClasses.size());
        for (OWLClassExpression subClass : subClasses) {
            // skip itself
            OWLClass subOwlClass = subClass.asOWLClass();
            System.out.println("subclass: " + subOwlClass);
            String subIri = subOwlClass.getIRI().toString();
            allSubClasses.add(subIri);
            // recurse
            allSubClasses.addAll(allSubClasses(subOwlClass, onto));
        }
        return allSubClasses;
    }

    /**
     * Save the ontology as OWL/XML. It first includes new meta data about the
     * slimming process.
     *
     * @param output
     * @param originalOWL
     * @throws OWLOntologyStorageException
     */
    public void saveAs(File output, String orinalOWL) throws OWLOntologyStorageException, FileNotFoundException {
        saveAs(new FileOutputStream(output), orinalOWL);
    }

    public void saveAs(OutputStream output, String originalOWL) throws OWLOntologyStorageException {
        // add provenance
        OWLDataFactory dataFac = man.getOWLDataFactory();

        // version info
        OWLLiteral lit = dataFac.getOWLLiteral(
                "This SLIM file was generated automatically by the eNanoMapper Slimmer "
                + "software library. For more information see "
                + "http://github.com/enanomapper/slimmer.");
        OWLAnnotationProperty owlAnnotationProperty
                = dataFac.getOWLAnnotationProperty(OWLRDFVocabulary.OWL_VERSION_INFO.getIRI());
        OWLAnnotation anno = dataFac.getOWLAnnotation(owlAnnotationProperty, lit);
        man.applyChange(new AddOntologyAnnotation(onto, anno));
        OWLAnnotationProperty pavImportedFrom = dataFac.getOWLAnnotationProperty(
                IRI.create("http://purl.org/pav/importedFrom")
        );
        anno = dataFac.getOWLAnnotation(pavImportedFrom, dataFac.getOWLLiteral(originalOWL));
        man.applyChange(new AddOntologyAnnotation(onto, anno));

        // generation tool
        lit = dataFac.getOWLLiteral("Slimmer");
        owlAnnotationProperty = dataFac.getOWLAnnotationProperty(
                IRI.create("http://www.geneontology.org/formats/oboInOwl#auto-generated-by")
        );
        anno = dataFac.getOWLAnnotation(owlAnnotationProperty, lit);
        man.applyChange(new AddOntologyAnnotation(onto, anno));

        // generation date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        lit = dataFac.getOWLLiteral(dateFormat.format(date));
        owlAnnotationProperty = dataFac.getOWLAnnotationProperty(
                IRI.create("http://www.geneontology.org/formats/oboInOwl#date")
        );
        anno = dataFac.getOWLAnnotation(owlAnnotationProperty, lit);
        man.applyChange(new AddOntologyAnnotation(onto, anno));

        // save to file
        RDFXMLDocumentFormat format = new RDFXMLDocumentFormat();
        format.setPrefix("ncicp", "http://ncicb.nci.nih.gov/xml/owl/EVS/ComplexProperties.xsd#");
        man.saveOntology(onto, format, output);
    }

    private Map<String, String> getNewSuperClasses(Set<Instruction> irisToSave) {
        Map<String, String> newSuperClasses = new HashMap<String, String>();
        for (Instruction instruction : irisToSave) {
            if (instruction.getNewSuperClass() != null) {
                newSuperClasses.put(instruction.getUriString(), instruction.getNewSuperClass());
            }
        }
        return newSuperClasses;
    }

    @SuppressWarnings("serial")
    Map<String, String> mappings = new HashMap<String, String>() {  //<K,V>
        {
            put("http://purl.obolibrary.org/obo/oae/RO_dev_import", "RO_dev_import.owl");
        }
    };

    private void addMappings(OWLOntologyManager m, String root) {
        if (!root.endsWith("/")) {
            root = root + "/";
        }
        for (String ontoIRI : mappings.keySet()) {
            String localPart = mappings.get(ontoIRI);
            m.addIRIMapper(new SimpleIRIMapper(IRI.create(ontoIRI), IRI.create("file://" + root + localPart)));
            System.out.println("  added: " + IRI.create("file://" + root + localPart));
            //added: file:///var/lib/jenkins.jenm/workspace/ENVO/RO_dev_import.owl
        }
    }

}
