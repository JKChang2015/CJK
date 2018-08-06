/**
 * Created by jkchang
 * 23/07/2018
 * Tag:
 * Description: Slimming the ontologies according to the configuration file
 */

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.RDFXMLDocumentFormat;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.search.EntitySearcher;
import org.semanticweb.owlapi.search.Searcher;
import org.semanticweb.owlapi.util.OWLEntityRemover;
import org.semanticweb.owlapi.util.OWLOntologyMerger;
import org.semanticweb.owlapi.util.SimpleIRIMapper;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;


public class Slimmer {

    private OWLOntologyManager man;
    private OWLOntology onto;

    public Slimmer(File owlFile, String mergedOntologyIRI) throws OWLOntologyCreationException, FileNotFoundException {
        this(owlFile.getName(), new FileInputStream(owlFile), mergedOntologyIRI);
    }

    public Slimmer(InputStream owlFile) throws OWLOntologyCreationException {
        this("undefined for InputStream", owlFile, null);
    }

    /**
     * Constructs a new Slimmer object that will slim the given OWL file.
     *
     * @param filename
     * @param owlFile
     * @param mergedOntologyIRI
     * @throws OWLOntologyCreationException
     */

    public Slimmer(String filename, InputStream owlFile, String mergedOntologyIRI) throws OWLOntologyCreationException {
        System.out.println("Loading OWL file: " + filename);
        man = OWLManager.createOWLOntologyManager();

        onto = man.loadOntologyFromOntologyDocument(owlFile);

        if (mergedOntologyIRI != null) {
            Set<OWLImportsDeclaration> importsDeclarations = onto.getImportsDeclarations();
            for (OWLImportsDeclaration declaration : importsDeclarations) {
                try {
                    man.loadOntology(declaration.getIRI());
                    System.out.println("Loaded imported ontology:" + declaration.getIRI());
                } catch (Exception exception) {
                    System.out.println("Failed to load imported ontology:" + declaration.getIRI());
                }
            }

            //merge all of the loaded ontologies, specifying a IRI for the new ontology
            OWLOntologyMerger merger = new OWLOntologyMerger(man);
            onto = merger.createMergedOntology(man, IRI.create(mergedOntologyIRI));
            for (OWLOntology ontology : man.getOntologies()) {
                System.out.println("Copying annotations from " + ontology.getOntologyID());
                for (OWLAnnotation annotation : ontology.getAnnotations()) {
                    System.out.println("..Copying annotations: " + annotation.getProperty() + "-->" + annotation.getValue());
                    AddOntologyAnnotation annotationAdd = new AddOntologyAnnotation(onto, annotation);
                    man.applyChange(annotationAdd);
                }
            }

        }
    }

    /**
     * Main method to allow running the Slimmer from the command line. The full slimming
     * process consists of a number of steps:
     * <ol>
     * <li>read the instructions that specify which ontology to slim</li>
     * <li>read the ontology to slim (including imports)</li>
     * <li>read the instructions that specify how the ontology is to be slimmed</li>
     * <li>remove everything from the ontology except what is to be kept, but after that still
     * delete things explicitly marked to be removed</li>
     * <li>remove owl:import statements from the OWL file</li>
     * <li>normalize term labels</li>
     * <li>save as OWL/XML (which includes updating the ontology metadata)</li>
     * </ol>
     *
     * @param args
     */

    public static void main(String[] args) {
        boolean allSusseeded = true;
        String rootFolder = args[0];
        System.out.println("Searching configuration file in " + rootFolder);
        File dir = new File(rootFolder);
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".props");
            }
        });

        // for all pros files
        for (File file : files) {
            try {
                System.out.println("Slimming for " + file.getName());

                //read the information about the ontology to process from property file
                Properties props = new Properties();
                props.load(new FileReader(file));

                String owlURL = props.getProperty("owl"); // Step 1, Load
                String irisFileName = props.getProperty("iris");  // Step 2, 3 , process
                String slimmedURI = props.getProperty("slimmed"); // Step 5, Save


                String owlFileName = owlURL;
                if (owlFileName.contains("/")) {
                    owlFileName = owlFileName.substring(owlFileName.lastIndexOf('/') + 1);

                }
                String slimmedFileName = slimmedURI;
                if (slimmedFileName.contains("/")) {
                    slimmedFileName = slimmedFileName.substring(slimmedFileName.lastIndexOf('/') + 1);
                }


                // 1. read the original ontology
                File owlFile = new File(owlFileName);
                Slimmer slimmer = new Slimmer(owlFile, slimmedFileName);
                OWLOntology onto = slimmer.getOntology();
                System.out.println("Loaded axioms: " + onto.getAxiomCount());

                // 2. read the configuration of keep/ remove
                File configFile = new File(rootFolder, irisFileName);
                Configuration config = new Configuration();
                try {
                    System.out.println("Reading config file: " + configFile);
                    config.read(configFile);
                } catch (Exception exception) {
                    System.out.println("Error while reading the config file: " + exception.getMessage());
                    System.exit(-1);
                }

                // 3. remove everything except for what is defined by the instructions
                Set<Instruction> irisToSave = config.getTreePartsToSave();
                slimmer.removeAllExcept(irisToSave);
                Set<Instruction> irisToRemove = config.getTreePartsToRemove();
                slimmer.removeAll(irisToRemove);


            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }

    public OWLOntology getOntology() {
        return this.onto;
    }

    /**
     * This methods removes all classes, data properties, and object properties, except those
     * URIs specified by the parameter. If a class is kept, the instructions also indicates
     * what the new parent of the class is.
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
            } else {
                // OK, keep this one. But does it have a new super class?
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

    private Map<String, String> getNewSuperClasses(Set<Instruction> irisToSave) {
        Map<String, String> newSuperClasses = new HashMap<String, String>();
        for (Instruction instruction : irisToSave) {
            if (instruction.getNewSuperClass() != null) {
                newSuperClasses.put(instruction.getUriString(), instruction.getNewSuperClass());
            }
        }
        return newSuperClasses;
    }

    /**
     * This functions applies the <code>D</code> and <code>U</code> statements from the configuration
     * files by traversing the OWL hierarchy and either including all parents or all children.
     *
     * @param instructions
     * @return
     */
    private Set<String> explode(Set<Instruction> instructions) {
        Set<String> singleIRIs = new HashSet<String>();
        for (Instruction instruction : instructions) {
            String iri = instruction.getUriString();
            if (instruction.getScope() == Instruction.Scope.UP) {
                System.out.println("Extracting " + iri + "...");
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
            } else if (instruction.getScope() == Instruction.Scope.DOWN) {
                System.out.println("Extracting " + iri + "...");
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
            } else if (instruction.getScope() == Instruction.Scope.SINGLE) {
                System.out.println("Extracting " + iri + "...");
                singleIRIs.add(iri);
            } else {
                System.out.println("Cannot handle this instruction: " + instruction.getScope());
            }
        }
        return singleIRIs;
    }

    private Set<String> allSuperClasses(OWLClass clazz,
                                        OWLOntology onto) {
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

    /**
     * Helper method that returns a collection sub classes of the given class.
     *
     * @param clazz
     * @param onto
     * @return
     */
    private Set<String> allSubClasses(OWLClass clazz,
                                      OWLOntology onto) {
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
}

