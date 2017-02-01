package eNM.Matching;

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

import eNanoMapper.Mapping.KeywordLoader;
import eNanoMapper.OntoLabel;

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
        this(new KeywordLoader(keywordFile).getkeywords(), owlFile);
    }

    public Mapper(Set<String> keywords, InputStream owlFile) throws
            OWLOntologyCreationException, FileNotFoundException {
        man = OWLManager.createConcurrentOWLOntologyManager();
        onto = man.loadOntologyFromOntologyDocument(owlFile);
        keywords = new HashSet<String>();
    }
    //=======================================================================

    public static void main(String[] args) throws Exception {

        Map<String, HashSet<MapTerm>> res = null;

        // 1. load keywords to 'keySet<String>'
        String kPath = "src\\main\\resources\\chemical description terms.txt";
        File kFile = new File(kPath);
        Set<String> keySet = new HashSet<String>();
        System.out.println("Loaded keywordFile from " + kPath);

        try {
            KeywordLoader keyword = new KeywordLoader(kFile);
            keySet = keyword.getkeywords();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("fail to load the keyword file ... ");
        }

        System.out.println("Finished loading keywords");

        for (String keyword : keySet) {
            res.put(keyword, null);
        }

        // 2. filter folder, load config file 
        String rootFolder = "..\\ontologies\\config";
        System.out.println("Searching configuration files in folder " + rootFolder);
        File dir = new File(rootFolder);
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".props");
            }
        });

        // 3. for each ontology file ---------------------------------------
        for (File file : files) {
            try {
                // 3.1 load the config file
                Properties props = new Properties();
                props.load(new FileReader(file));

                // 3.2 load the ontology
                String owlURL = props.getProperty("owl");
                String owlFilename = owlURL;
                if (owlFilename.contains("/")) {
                    owlFilename = owlFilename.substring(owlFilename.lastIndexOf('/') + 1);
                }

                OWLOntologyManager man = OWLManager.createConcurrentOWLOntologyManager();
                OWLOntology onto = man.loadOntologyFromOntologyDocument(IRI.create(owlURL)); //imput
                System.out.println("Loaded ontology: " + owlFilename);

                // 3.3 merge the ontology
                OWLOntologyMerger merger = new OWLOntologyMerger(man);
                onto = merger.createMergedOntology(man, IRI.create(owlURL + "_merg")); //output

                for (OWLOntology ontology : man.getOntologies()) {
                    System.out.println(owlFilename + " Copying annotations from " + ontology.getOntologyID().getOntologyIRI().get().toString());

                    for (OWLAnnotation annotation : ontology.getAnnotations()) {
                        //System.out.println(" Copying annotation: " + annotation.getProperty() + " -> " + annotation.getValue());
                        AddOntologyAnnotation annotationAdd = new AddOntologyAnnotation(onto, annotation);
                        man.applyChange(annotationAdd);
                    }
                }

                System.out.println("Merged ontology: " + owlFilename);

                // 3.4 get labels
                OntoLabel lb = new OntoLabel(onto);
                HashSet<String> labels = (HashSet) lb.getlabel();

                // 3.5 matching
                // 3.6 save the result
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("fail to load property files... ");
            }
        }

    }

    public OWLOntology getOntology() {
        return this.onto;
    }

}
