package eNanoMapper;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Set;
import java.util.HashSet;

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
 * @date 17-Nov-2016
 * @Description: load labels from Ontology
 *
 */

public class OntoLabel {

    private OWLOntologyManager man;
    private Set<String> labels = new HashSet<String>();

    public OntoLabel(File file) throws OWLOntologyCreationException, FileNotFoundException, IOException {
        this(OWLManager.createConcurrentOWLOntologyManager().loadOntologyFromOntologyDocument(file));
    }

    public OntoLabel(OWLOntology onto) {
        //PrintWriter out = new PrintWriter(new FileWriter("labels.txt", true), true);
        int count = 0;
        man = OWLManager.createConcurrentOWLOntologyManager();
        Set<OWLClass> classes = onto.getClassesInSignature(); // load all the classes in Signature
        OWLDataFactory factory = man.getOWLDataFactory(); // Creat ontology factory

        for (OWLClass clazz : classes) {
            count++;
            Set<OWLAnnotationAssertionAxiom> annotations = onto.getAnnotationAssertionAxioms(clazz.getIRI());  //get all the Annotation Assertion of 
            for (OWLAnnotationAssertionAxiom annotation : annotations) {
                if (annotation.getProperty().equals(factory.getRDFSLabel()) && annotation.getValue() instanceof OWLLiteral) {
                    OWLLiteral lr = (OWLLiteral) annotation.getValue();
                    String result = (String) lr.getLiteral();
                    labels.add(result.trim());
                    System.out.println(count + ".  " + result);
//                    out.println(result);
                }
            }
        }
    }

    public Set<String> getlabel() {
        return labels;
    }

}
