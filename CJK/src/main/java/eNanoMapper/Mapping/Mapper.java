package eNanoMapper.Mapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
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

    public void mapping() {

    }

}
