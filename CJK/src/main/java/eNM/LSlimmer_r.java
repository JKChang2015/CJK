package eNM;

import eNanoMapper.Configuration;
import eNanoMapper.configuration.Instruction;
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

/**
 * ----------------------------------------------------------------------------
 * Test ontology slimmer locally
 * ----------------------------------------------------------------------------
 *
 * @author jkchang
 * @date 07-Jul-2016
 */
public class LSlimmer_r {

    private OWLOntologyManager man;
    private OWLOntology onto;

    public LSlimmer_r(File owlFile, String mergedOntologyIRI) throws OWLOntologyCreationException, FileNotFoundException {
        this(new FileInputStream(owlFile), mergedOntologyIRI);
    }

    public LSlimmer_r(InputStream owlFile) throws OWLOntologyCreationException {
        this(owlFile, null);
    }

    /**
     * @param owlFile (input file)
     * @param mergedOntologyIRI (output IRI)
     * @throws OWLOntologyCreationException
     */
    public LSlimmer_r(InputStream owlFile, String mergedOntologyIRI) throws OWLOntologyCreationException {
        man = OWLManager.createConcurrentOWLOntologyManager();
        onto = man.loadOntologyFromOntologyDocument(owlFile);

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

        // Load the ontology file from local disk, and slimmed it locally
        // imput: File    
        String rootFolder = "..\\ontologies\\config\\run";
        System.out.println("Searching configuration files in folder " + rootFolder); //Searching configuration files in .

        File dir = new File(rootFolder);
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".props");
            }
        });

//        for (File file : files) {
//
//            try {
//                System.out.println("Load local ontology " + file.getName());
//
//                // read the prop file
//                Properties pros = new Properties();
//                pros.load(new FileReader(file));
//
//                String owlURL = pros.getProperty("owl");
//                String owlFilename = owlURL;
//                if (owlFilename.contains("/")) {
//                    owlFilename = owlFilename.substring(owlFilename.lastIndexOf('/') + 1);
//                }
//                
//                System.out.println(rootFolder + "\\"+ owlFilename);
//
//                OWLOntologyManager man = OWLManager.createOWLOntologyManager();
//                OWLDataFactory factory = man.getOWLDataFactory();
//                File ontoFile = new File(rootFolder + "\\"+ owlFilename);
//                OWLOntology onto = man.loadOntologyFromOntologyDocument(ontoFile);
//
//                System.out.println("Load file " + owlFilename + " from " + rootFolder);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.out.println("fail to load file");
//            }
//
//        }

        for (File file : files) {  // for each property file
            try {
                System.out.println("Slimming for  " + file.getName());

                //read the information about the ontology to process
                Properties props = new Properties();
                props.load(new FileReader(file));

                //---------- source ------------------------------------ 
                String owlURL = props.getProperty("owl");
                String owlFilename = owlURL;
                if (owlFilename.contains("/")) {
                    owlFilename = owlFilename.substring(owlFilename.lastIndexOf('/') + 1);
                }
                //---------- output -----------------------------------
                String slimmedURI = props.getProperty("slimmed");
                String slimmedFilename = slimmedURI;
                if (slimmedFilename.contains("/")) {
                    slimmedFilename = slimmedFilename.substring(slimmedFilename.lastIndexOf('/') + 1);
                }
                //----------- iris ------------------------------------
                String irisFilename = props.getProperty("iris");

                // 1. read the original ontology
                File owlFile = new File("..\\ontologies\\config\\run\\" + owlFilename);
                LSlimmer_r slimmer = new LSlimmer_r(owlFile, slimmedFilename);
                OWLOntology onto = slimmer.getOntology();
                System.out.println("Loaded Ontology: " + slimmedFilename);
                System.out.println("Loaded axioms: " + onto.getAxiomCount());

                // 2. read the configuration of what to keep/remove
                File configFile = new File(rootFolder, irisFilename); // read iris file
                Configuration config = new Configuration();
                try {
                    System.out.println("Reading config file: " + configFile);
                    config.read(configFile);
                } catch (Exception exception) {
                    System.out.println("Error while reading the config file: " + exception.getMessage());
                    exception.printStackTrace();
                    System.exit(-1);
                }

                // 3. remove everything except for what is defined by the instructions
                Set<Instruction> irisToSave = config.getTreePartsToSave();
                slimmer.removeAllExcept(irisToSave);  // remove except
                Set<Instruction> irisToRemove = config.getTreePartsToRemove();
                slimmer.removeAll(irisToRemove);  // remove all

                // 4. remove owl:imports
                Set<OWLImportsDeclaration> importDeclarations = onto.getImportsDeclarations();
                for (OWLImportsDeclaration declaration : importDeclarations) {
                    System.out.println("Removing imports: " + declaration.getIRI());
                    RemoveImport removeImport = new RemoveImport(onto, declaration);
                    slimmer.man.applyChange(removeImport);
                }

                // 5. update descriptions and labels
                Set<OWLClass> entities = onto.getClassesInSignature();
                for (OWLClass clazz : entities) {
                    for (OWLAnnotation annot : EntitySearcher.getAnnotations(clazz, onto)) {
                        if (annot.getProperty().getIRI().toString().equals("http://purl.org/dc/elements/1.1/description")) {
                            System.out.println("  description: " + annot.getValue());
                            OWLDataFactory factory = slimmer.man.getOWLDataFactory();
                            OWLAnnotationProperty newDescription = factory.getOWLAnnotationProperty(IRI.create("http://purl.obolibrary.org/obo/IAO_0000115"));
                            OWLAnnotation commentAnno = factory.getOWLAnnotation(newDescription, annot.getValue());
                            System.out.println("  new description: " + commentAnno);
                            OWLAxiom ax = factory.getOWLAnnotationAssertionAxiom(clazz.getIRI(), commentAnno);
                            slimmer.man.applyChange(new AddAxiom(onto, ax));
                        }
                    }
                }

                // 6. remove some nasty NPO properties (WORKAROUND: may be removed later)
                entities = onto.getClassesInSignature();
                for (OWLClass clazz : entities) {
                    Set<OWLAnnotationAssertionAxiom> annots = onto.getAnnotationAssertionAxioms(clazz.getIRI());
                    Set<OWLAnnotationAssertionAxiom> toRemove = new HashSet<OWLAnnotationAssertionAxiom>();
                    for (OWLAnnotationAssertionAxiom axiom : annots) {
                        if (axiom.getProperty().getIRI().toString().equals("http://purl.bioontology.org/ontology/npo#FULL_SYN")
                                || axiom.getProperty().getIRI().toString().equals("http://purl.bioontology.org/ontology/npo#definition")) {
                            toRemove.add(axiom);
                        }
                    }
                    slimmer.man.removeAxioms(onto, toRemove);
                }

                // 7. save in OWL/XML format
                SetOntologyID ontologyIDChange = new SetOntologyID(onto, IRI.create(slimmedURI));
                slimmer.man.applyChange(ontologyIDChange);
                File output = new File(slimmedFilename);
                System.out.println("Saving to: " + output.getAbsolutePath());
                slimmer.saveAs(output, owlURL);

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

    public OWLOntology getOntology() {
        return this.onto;
    }

    /**
     * This methods removes all classes, data properties, and object properties,
     * except those URIs specified by the parameter. If a class is kept, the
     * intrductions also indicates what the new parent of the class is.
     *
     * @param irisToSave which IRIs are to be kept
     */
    public void removeAllExcept(Set<Instruction> irisToSave) {
        Set<String> singleIRIs = explode(irisToSave);
        Map<String, String> newSuperClasses = getNewSuperClasses(irisToSave);
        System.out.println("" + singleIRIs);

        // remove classes
        OWLEntityRemover remover = new OWLEntityRemover(Collections.singleton(onto));
        for (OWLClass ind : onto.getClassesInSignature()) {
            String indIRI = ind.getIRI().toString();
            System.out.println(indIRI);
            if (!singleIRIs.contains(indIRI)) {
                System.out.println("Remove: " + indIRI);
                ind.accept(remover);
            } else // OK, keep this one. But does it have a new super class?
            {
                if (newSuperClasses.containsKey(indIRI)) {
                    String newSuperClass = newSuperClasses.get(indIRI);
                    OWLDataFactory factory = man.getOWLDataFactory();
                    System.out.println("Super class: " + newSuperClass);
                    OWLClass superClass = factory.getOWLClass(IRI.create(newSuperClass));
                    OWLAxiom axiom = factory.getOWLSubClassOfAxiom(ind, superClass);
                    System.out.println("Adding super class axiom: " + axiom);
                    AddAxiom addAxiom = new AddAxiom(onto, axiom);
                    man.applyChange(addAxiom);
                }
            }
        }

        // remove properties
        for (OWLObjectProperty axiom : onto.getObjectPropertiesInSignature()) {
            String propIRI = axiom.getIRI().toString();
            System.out.println(propIRI);
            if (!singleIRIs.contains(propIRI)) {
                System.out.println("Remove: " + propIRI);
                axiom.accept(remover);
            }
        }
        for (OWLDataProperty axiom : onto.getDataPropertiesInSignature()) {
            String propIRI = axiom.getIRI().toString();
            System.out.println(propIRI);
            if (!singleIRIs.contains(propIRI)) {
                System.out.println("Remove: " + propIRI);
                axiom.accept(remover);
            }
        }

        man.applyChanges(remover.getChanges());
    }

    /**
     * This method removes all IRIs given by the parameter.
     *
     * @param irisToRemove
     */
    public void removeAll(Set<Instruction> irisToRemove) {
        Set<String> singleIRIs = explode(irisToRemove);
        System.out.println("" + singleIRIs);

        OWLEntityRemover remover = new OWLEntityRemover(Collections.singleton(onto));
        for (OWLClass ind : onto.getClassesInSignature()) {
            String indIRI = ind.getIRI().toString();
            System.out.println(indIRI);
            if (singleIRIs.contains(indIRI)) {
                System.out.println("Remove: " + indIRI);
                ind.accept(remover);
            }
        }

        // remove properties
        for (OWLObjectProperty axiom : onto.getObjectPropertiesInSignature()) {
            String propIRI = axiom.getIRI().toString();
            System.out.println(propIRI);
            if (singleIRIs.contains(propIRI)) {
                System.out.println("Remove: " + propIRI);
                axiom.accept(remover);
            }
        }
        for (OWLDataProperty axiom : onto.getDataPropertiesInSignature()) {
            String propIRI = axiom.getIRI().toString();
            System.out.println(propIRI);
            if (singleIRIs.contains(propIRI)) {
                System.out.println("Remove: " + propIRI);
                axiom.accept(remover);
            }
        }

        man.applyChanges(remover.getChanges());
    }

    /**
     * This functions applies the <code>D</code> and <code>U</code> statements
     * from the configuration files by traversing the OWL hierarchy and either
     * including all parents or all children.
     *
     * @param instructions
     * @return
     */
    private Set<String> explode(Set<Instruction> instructions) {

        Set<String> singleIRIs = new HashSet<String>();

        for (Instruction instruction : instructions) {
            String iri = instruction.getUriString();
            // load SUPERclasses
            if (instruction.getScope() == Instruction.Scope.UP) {
                System.out.println("Extracting SuperClass of " + iri + "...");
                Set<OWLEntity> entities = onto.getEntitiesInSignature(IRI.create(iri));
                if (entities.size() > 0) {
                    OWLEntity entity = entities.iterator().next();
                    if (entity instanceof OWLClass) {
                        OWLClass clazz = (OWLClass) entity;
                        System.out.println("Class " + clazz);
                        Set<String> superClasses = allSuperClasses(clazz, onto);
                        for (String superClass : superClasses) {
                            System.out.println("Extracting " + superClass + "...");
                            singleIRIs.add(superClass);
                        }
                    }
                }
                singleIRIs.add(iri);
            } else if (instruction.getScope() == Instruction.Scope.DOWN) {  // load SUBclasses
                System.out.println("Extracting SubClass of " + iri + "...");
                Set<OWLEntity> entities = onto.getEntitiesInSignature(IRI.create(iri));
                if (entities.size() > 0) {
                    OWLEntity entity = entities.iterator().next();
                    if (entity instanceof OWLClass) {
                        OWLClass clazz = (OWLClass) entity;
                        System.out.println("Class " + clazz);
                        Set<String> subClasses = allSubClasses(clazz, onto);
                        for (String subClass : subClasses) {
                            System.out.println("Extracting " + subClass + "...");
                            singleIRIs.add(subClass);
                        }
                    }
                }
                singleIRIs.add(iri);
            } else if (instruction.getScope() == Instruction.Scope.SINGLE) {// load single class
                System.out.println("Extracting " + iri + "...");
                singleIRIs.add(iri);
            } // error
            else {
                System.out.println("Cannot handle this instruction: " + instruction.getScope());
            }
        }
        return singleIRIs;
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
        //Gets all of the subclass axioms where the left hand side (the subclass) is equal to the specified class.
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
        //Gets all of the subclass axioms where the right hand side (the superclass) is equal to the specified class.
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
