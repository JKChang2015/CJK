package com.github.eNM;

import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
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

public class Label {

    private OWLOntologyManager man;
    private OWLOntology onto;
    private Set<String> labels = new HashSet<String>();

    public Label(File file) throws OWLOntologyCreationException, FileNotFoundException, IOException {
//        PrintWriter out = new PrintWriter(new FileWriter("labels.txt", true), true);
        int count = 0;

        man = OWLManager.createOWLOntologyManager(); // Creat ontology manager
        OWLDataFactory factory = man.getOWLDataFactory(); // Creat ontology factory
        onto = man.loadOntologyFromOntologyDocument(file); // load ontology from file
        Set<OWLClass> classes = onto.getClassesInSignature(); // load all the classes in Signature

        for (OWLClass clazz : classes) {
            count++;
            Set<OWLAnnotationAssertionAxiom> annotations = onto.getAnnotationAssertionAxioms(clazz.getIRI());  //get all the Annotation Assertion of 
            for (OWLAnnotationAssertionAxiom annotation : annotations) {
                if (annotation.getProperty().equals(factory.getRDFSLabel()) && annotation.getValue() instanceof OWLLiteral) {
                    OWLLiteral lr = (OWLLiteral) annotation.getValue();
                    String result = (String) lr.getLiteral();
                    System.out.println(count + ".  "+result);
//                    out.println(result);
                }
            }
        }

  //      out.close();
    }

    public Set<String> getlabel() {
        return labels;
    }

}
