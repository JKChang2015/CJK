package eNanoMapper;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLLiteral;

/**
 *
 * @author JKChang
 * @date 23-Jan-2017
 * @Description:
 *
 */
public class impl {

    public static void main(String[] args) throws OWLOntologyCreationException, FileNotFoundException, IOException {

        String path = "C:\\Users\\jkchang\\Desktop\\mee.owl";
        File owlFile = new File(path);
        //OntoLabel ol = new OntoLabel(owlFile);

        OntoLabelChecker checker = new OntoLabelChecker();
        HashMap<String, ArrayList<String>> dupli = checker.checkDuplicated(owlFile);
        System.out.println("size of duplicated term set is " + dupli.size());
        checker.listDuplicated();
        System.out.println("");

//        ArrayList<String> unLabel = checker.getNonLabelIRI();
//        ArrayList<String> label = checker.getlabels();
//        
//        int count = 0;
//        
//        for (int i = 0; i < label.size(); i++) {
//            for (int j = i+1; j < label.size(); j++) {
//                if (i != j && label.get(i).equalsIgnoreCase(label.get(j))){
//                    count ++;
//                    System.out.println(count + ". "+label.get(i));
//                }
//            }
//        }
//        
//        Set<String> res = ol.getNonLabel();
//        int ct = 0;
//        for (String s: res) {
//            ct++;
//            System.out.println(ct + ". "+s);
//        }
    }

}
