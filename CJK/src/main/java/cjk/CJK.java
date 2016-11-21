package cjk;

import eNM.Matching.OntoLabel;

import java.util.Set;
import java.util.HashSet;
import java.io.File;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLAnnotationValue;

/**
 * .,;:([+-=
 *
 * @author jkchang
 */
public class CJK {

    //static private OWLOntologyManager man;
    static private OWLOntology onto;
    static Set<String> labels = new HashSet<String>();

    public static void main(String[] args) throws Exception {
        String ontoPath = "src\\main\\resources\\enm.owl";
        File ontoFile = new File(ontoPath);

        OWLOntologyManager man = OWLManager.createOWLOntologyManager();
        OWLDataFactory factory = man.getOWLDataFactory();
        OWLOntology onto = man.loadOntologyFromOntologyDocument(ontoFile);
        Set<OWLClass> classes = onto.getClassesInSignature();

        // count the Classes
        int clsCount = 0;
        for (OWLClass clazz : classes) {
            clsCount++;
            System.out.println(clsCount);
        }

        System.out.println("total number of classes is " + clsCount);

        // list the label of the classes
        for (OWLClass clazz : classes) {
            
            Set<OWLAnnotationAssertionAxiom> annotations = onto.getAnnotationAssertionAxioms(clazz.getIRI());
            for (OWLAnnotationAssertionAxiom ano : annotations) {
                OWLAnnotationValue va = ano.getValue();
                System.out.println(va.toString());
                
            }
            System.out.println("==============================");

        }

        //count the def
        // count the isDefBy
    }
}
