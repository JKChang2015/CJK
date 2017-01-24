package eNanoMapper;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Iterator;

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
 * @Description: Checking the labels of the ontology
 *
 */
public class OntoLabelChecker {

    private ArrayList<String> allLabel = new ArrayList<String>();
    private ArrayList<String> IRI = new ArrayList<String>();
    private HashMap<String, ArrayList<String>> dupli = new HashMap<String, ArrayList<String>>();

    public OntoLabelChecker() {

    }

    public OntoLabelChecker(File file) throws OWLOntologyCreationException, FileNotFoundException, IOException {
        this(OWLManager.createConcurrentOWLOntologyManager().loadOntologyFromOntologyDocument(file));
    }

    public OntoLabelChecker(OWLOntology onto) {

        OWLOntologyManager man = OWLManager.createConcurrentOWLOntologyManager();
        Set<OWLClass> classes = onto.getClassesInSignature(); // load all the classes in Signature
        OWLDataFactory factory = man.getOWLDataFactory(); // Creat ontology factory

        System.out.println("The ontology inclued " + classes.size() + " classes");

        for (OWLClass clazz : classes) {
            boolean hasLabel = false;

            Set<OWLAnnotationAssertionAxiom> annotations = onto.getAnnotationAssertionAxioms(clazz.getIRI());
            for (OWLAnnotationAssertionAxiom annotation : annotations) {
                if (annotation.getProperty().equals(factory.getRDFSLabel()) && annotation.getValue() instanceof OWLLiteral) {
                    OWLLiteral lr = (OWLLiteral) annotation.getValue();
                    String result = (String) lr.getLiteral();
                    allLabel.add(result.trim());
                    hasLabel = true;
                }
            }

            if (!hasLabel) {  //If don't have a Label
                String uri = clazz.getIRI().toString();
                IRI.add(uri);
            }

        }

        System.out.println("size of labels sets is " + allLabel.size());
        System.out.println("size of unlabel classes is " + IRI.size());
    }

    public HashMap<String, ArrayList<String>> checkDuplicated(File file) throws OWLOntologyCreationException, FileNotFoundException, IOException {

        return this.checkDuplicated(OWLManager.createConcurrentOWLOntologyManager().loadOntologyFromOntologyDocument(file));
    }

    public HashMap<String, ArrayList<String>> checkDuplicated(OWLOntology onto) {

        OWLOntologyManager man = OWLManager.createConcurrentOWLOntologyManager();
        Set<OWLClass> classes = onto.getClassesInSignature(); // load all the classes in Signature
        OWLDataFactory factory = man.getOWLDataFactory(); // Creat ontology factory

        System.out.println("The ontology inclued " + classes.size() + " classes");

        for (OWLClass clazz : classes) {

            Set<OWLAnnotationAssertionAxiom> annotations = onto.getAnnotationAssertionAxioms(clazz.getIRI());
            for (OWLAnnotationAssertionAxiom annotation : annotations) {
                if (annotation.getProperty().equals(factory.getRDFSLabel()) && annotation.getValue() instanceof OWLLiteral) {
                    OWLLiteral lr = (OWLLiteral) annotation.getValue();
                    String result = (String) lr.getLiteral().trim().toLowerCase();

                    if (dupli.containsKey(result)) {
                        ArrayList list = dupli.get(result);
                        list.add(clazz.getIRI().toString());
                    } else {
                        ArrayList<String> list = new ArrayList<String>();
                        list.add(clazz.getIRI().toString());
                        dupli.put(result, list);
                    }

                }
            }
        }
        
        Iterator<Map.Entry<String,ArrayList<String>>> it =  dupli.entrySet().iterator();
        
        while (it.hasNext()) {
            Map.Entry<String, ArrayList<String>> entry = it.next();
            if (entry.getValue().size() < 2) {
             //   System.out.println("delete " + entry.getKey());
                it.remove();               
            }
        }
    

        return dupli;
    }

    public ArrayList<String> getlabels() {
        return allLabel;
    }

    public ArrayList<String> getNonLabelIRI() { //return IRI of non-labeled classes
        return IRI;
    }

    public void listLabels() { // list all the labels
        this.showList(allLabel);
    }

    public void listNonLableURI() {  //list all the non-labeled classes's IRI
        this.showList(IRI);
    }

    public void listDuplicated() {
         Iterator<Map.Entry<String,ArrayList<String>>> it =  dupli.entrySet().iterator();
         
        int count = 0;
        while (it.hasNext()) {
            count ++;
            Map.Entry<String, ArrayList<String>> entry = it.next();
            System.out.println(count + ". " + entry.getKey());
            ArrayList<String> list = entry.getValue();
            for (String iri: list) {
                System.out.println("\t" + iri);
            }
        }

    }

    private void showList(ArrayList<String> list) {
        int count = 0;

        for (String s : list) {
            count++;
            System.out.println(count + ". " + s);
        }
    }

}
