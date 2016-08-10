package eNM;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.io.IOException;

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
        String ontoPath = "src\\main\\resources\\enm.owl";
        Set<String> keywords = new HashSet<String>();
        Set<String> ontoLabels = new HashSet<String>();

        File ontoFile = new File(ontoPath);
        File keywordFile = new File(kPath);

        KeywordFile kf = new KeywordFile(keywordFile);
        keywords = kf.getkeywords();

    }

}

