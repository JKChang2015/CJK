package eNanoMapper;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

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
 * @Description: Checking the labels of the ontology, if there are some null
 *
 */
public class OntoLabelChecker {

    private OWLOntologyManager man;
    private Set<String> labels = new HashSet<String>();
    private ArrayList<String> allLabel = new ArrayList<String>();
    private ArrayList<String> URI = new ArrayList<String>();

    public OntoLabelChecker(File file) throws OWLOntologyCreationException, FileNotFoundException, IOException {
        this(OWLManager.createConcurrentOWLOntologyManager().loadOntologyFromOntologyDocument(file));
    }

    public OntoLabelChecker(OWLOntology onto) {

        man = OWLManager.createConcurrentOWLOntologyManager();
        Set<OWLClass> classes = onto.getClassesInSignature(); // load all the classes in Signature
        OWLDataFactory factory = man.getOWLDataFactory(); // Creat ontology factory

        System.out.println("size of the ontology is " + classes.size());

        int labelCount = 0;
        for (OWLClass clazz : classes) {
            Set<OWLAnnotationAssertionAxiom> annotations = onto.getAnnotationAssertionAxioms(clazz.getIRI());

            boolean hasLabel = false;

            for (OWLAnnotationAssertionAxiom annotation : annotations) {

                if (annotation.getProperty().equals(factory.getRDFSLabel()) && annotation.getValue() instanceof OWLLiteral) {
                    OWLLiteral lr = (OWLLiteral) annotation.getValue();
                    String result = (String) lr.getLiteral();
                    allLabel.add(result.trim());
                    hasLabel = true;
                }
            }
            if (!hasLabel) {
                String uri = clazz.getIRI().toString();
                URI.add(uri);
            }
        }
        System.out.println("size of labels sets is " + allLabel.size());
        System.out.println("size of unlabel classes is " + URI.size());
    }

    public ArrayList<String> getlabel() {
        return allLabel;
    }

    public ArrayList<String> getNonLabel() {
        return URI;
    }
}
