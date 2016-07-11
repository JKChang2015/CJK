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
            addMappings(man, root);
        }

        if (mergedOntologyIRI != null) {
            
            // Load all of the ontologies
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
            for (OWLOntology ontology: man.getOntologies()) {
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
