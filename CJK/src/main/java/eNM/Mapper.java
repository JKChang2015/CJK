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
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;


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

    //construction ===========================================================
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

        String kPath = "src\\main\\resources\\keyword.txt";
        File keywordFile = new File(kPath);
        System.out.println("Loaded keywordFile from " + kPath);

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

            } catch (Exception e) {

            }
        }

        String ontoPath = " ";
        File ontoFile = new File(ontoPath);

        Mapper map = new Mapper(keywordFile, ontoFile);
        HashSet<String> set = (HashSet) map.keywords;
    }

    public void combine() {

    }

}
