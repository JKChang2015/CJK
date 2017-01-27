package eNanoMapper.Util;

/**
 *
 * @author jkchang
 * @date 27-Jan-2017
 * @Description: Check the definition of the ontologies
 *
 */
import eNanoMapper.Mapping.OntoLoader;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import java.util.Map;
import java.io.File;

public class DefChecker {

    private OWLOntology onto;
    private OWLOntologyManager man;

    public static void main(String[] args) throws OWLOntologyCreationException{
        //load the eNM ontology
        String path = "/Users/jkchang/Github/ontologies/enanomapper.owl";
        File owlFile = new File(path);
        
        OntoLoader loader = new OntoLoader(IRI.create(owlFile));
        loader.merge();
        Map<String,String> defs = loader.getDef();
        

    }

}
