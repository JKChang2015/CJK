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
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DefChecker {

    //private OWLOntology onto;
    private OWLOntologyManager man;

    public static void main(String[] args) throws OWLOntologyCreationException, FileNotFoundException {

        //load the list of the def URIs
        String defURIList = "defURI.txt";
        File defFile = new File(defURIList);
        Scanner s1 = new Scanner(defFile);

        Set<String> URIs = new HashSet<String>();
        while (s1.hasNext()) {
            URIs.add(s1.next());
        }
        
        System.out.println("Total number of Definitions is " + URIs.size());

        //load the eNM ontology
        String ontoPath = "/Users/jkchang/Github/ontologies/enanomapper.owl";
        File owlFile = new File(ontoPath);
        System.out.println("Load ontology from " + ontoPath);

        OntoLoader loader = new OntoLoader(owlFile);
        loader.merge();
        OWLOntology onto = loader.getOnto();
        
        

    }

}
