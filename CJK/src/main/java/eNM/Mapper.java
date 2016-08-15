package eNM;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
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
import org.semanticweb.owlapi.model.AddOntologyAnnotation;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLImportsDeclaration;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.util.OWLOntologyMerger;


/* 
 @author jkchang
 @date 04-Aug-2016
 Task: mapping query terms with the external ontologies classe labels
Steps: 1. load query terms
       2. laad ontologies
        2.1 merge ontologies
        2.2 normalization
       3. mapping terms with each ontologies
       
 */
public class Mapper {

    private OWLOntologyManager man;
    private OWLOntology onto;
    private Set<String> keywords;

    //===========================constructor================================
    public Mapper(File keywordFile, File owlFile) throws
            OWLOntologyCreationException, IOException {
        this(keywordFile, new FileInputStream(owlFile));
    }

    public Mapper(File keywordFile, InputStream owlFile) throws
            OWLOntologyCreationException, IOException {
        this(new KeywordFile(keywordFile).getkeywords(), owlFile);
    }

    public Mapper(Set<String> keywords, InputStream owlFile) throws
            OWLOntologyCreationException, FileNotFoundException {
        man = OWLManager.createConcurrentOWLOntologyManager();
        onto = man.loadOntologyFromOntologyDocument(owlFile);
        keywords = new HashSet<String>();
    }
    //=======================================================================

    public static void main(String[] args) throws Exception {

        // Save keywords to 'keySet<String>'
        String kPath = "src\\main\\resources\\chemical description terms.txt";
        File kFile = new File(kPath);
        Set<String> keySet = new HashSet<String>();
        System.out.println("Loaded keywordFile from " + kPath);

        try {
            KeywordFile keyword = new KeywordFile(kFile);
            keySet = keyword.getkeywords();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("fail to load the keyword file ... ");
        }
        
        System.out.println("Finished loading keywords");

        // initialize ontologies
        String rootFolder = "..\\ontologies\\config";
        System.out.println("Searching configuration files in folder " + rootFolder);
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

                String owlURL = props.getProperty("owl");
                String owlFilename = owlURL;
                if (owlFilename.contains("/")) {
                    owlFilename = owlFilename.substring(owlFilename.lastIndexOf('/') + 1);
                }

            } catch (Exception e) {

            }
        }

    
    }

    public void combine(InputStream owlFile, String mergedOntologyIRI) throws OWLOntologyCreationException {
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

    public OWLOntology getOntology() {
        return this.onto;
    }

}
