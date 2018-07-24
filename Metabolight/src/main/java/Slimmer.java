/**
 * Created by jkchang
 * 23/07/2018
 * Tag:
 * Description: Slimming the ontologies according to the configuration file
 */

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

    public OWLOntology getOntology() {
        return this.onto;
    }

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
                    owlFileName = owlFileName.substring(owlFileName.lastIndexOf('/')+1);

                }
                String slimmedFileName = slimmedURI;
                if (slimmedFileName.contains("/")) {
                    slimmedFileName = slimmedFileName.substring(slimmedFileName.lastIndexOf('/')+1);
                }


                // 1. read the original ontology
                File owlFile = new File(owlFileName);
                Slimmer slimmer = new Slimmer(owlFile,slimmedFileName);
                OWLOntology onto = slimmer.getOntology();
                System.out.println("Loaded axioms: " + onto.getAxiomCount());

                // 2. read the configuration of keep/ remove












            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }
}

