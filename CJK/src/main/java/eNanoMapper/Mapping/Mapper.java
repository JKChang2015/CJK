package eNanoMapper.Mapping;

import eNM.Matching.MapTerm;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

/**
 *
 * @author JKChang
 * @date 21-Nov-2016
 * @Description: Mapping the ontology labels with term list
 *
 */
public class Mapper {

    private OWLOntologyManager man;
    private OWLOntology onto;
    private Set<String> keywords;
    private ArrayList<MapTerm> res;

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
        this.keywords = keywords;
    }
    //=======================================================================

    public Map ExactMapping() {

        Map<String, HashSet<MapTerm>> exactMap = new HashMap<String, HashSet<MapTerm>>();
        for (String keyword : keywords) {
            exactMap.put(keyword, null);
            
        }

        return exactMap;

    }

    public Map FuzzyMapping() {
        Map<String, HashSet<MapTerm>> fuzzyMap = new HashMap<String, HashSet<MapTerm>>();
        for (String keyword : keywords) {
            fuzzyMap.put(keyword, null);
        }

        return fuzzyMap;
    }

}
