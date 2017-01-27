package eNanoMapper.Mapping;

import org.semanticweb.owlapi.model.OWLOntology;
import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.lang.StringBuilder;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddOntologyAnnotation;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.util.OWLOntologyMerger;

/**
 * load ontology from file, from url, from config file
 *
 * @author jkchang
 */
public class OntoLoader {

    private OWLOntology onto;
    private OWLOntologyManager man;

    // load the ontology from URL
    public OntoLoader(Properties props) throws OWLOntologyCreationException {
        this(IRI.create(props.getProperty("owl")));  //--> *(String URL)
    }

    public OntoLoader(IRI iri) throws OWLOntologyCreationException {
        System.out.println("loading ontology file form " + iri.toString());
        man = OWLManager.createConcurrentOWLOntologyManager();
        onto = man.loadOntology(iri);

    }

    // load the from owlFile:
    public OntoLoader(File owlFile) throws OWLOntologyCreationException, FileNotFoundException {
        this(new FileInputStream(owlFile));  // --> (InputStream owlFile)
    }

    public OntoLoader(InputStream owlFile) throws OWLOntologyCreationException, FileNotFoundException {
        System.out.println("loading ontology file form " + owlFile.toString());
        man = OWLManager.createConcurrentOWLOntologyManager();
        onto = man.loadOntologyFromOntologyDocument(owlFile);

    }

    public void merge() throws OWLOntologyCreationException {
        OWLOntologyMerger merger = new OWLOntologyMerger(man);
        String URL = onto.getOntologyID().getOntologyIRI().toString();
        String mergURL = new StringBuilder(URL).insert(URL.lastIndexOf('.'), "_merged").toString();
        onto = merger.createMergedOntology(man, IRI.create(mergURL)); //output
        // merge the annotations
        for (OWLOntology ontology : man.getOntologies()) {
            //System.out.println(" Copying annotations from " + ontology.getOntologyID().getOntologyIRI().get().toString());

            for (OWLAnnotation annotation : ontology.getAnnotations()) {
                //System.out.println(" Copying annotation: " + annotation.getProperty() + " -> " + annotation.getValue());
                AddOntologyAnnotation annotationAdd = new AddOntologyAnnotation(onto, annotation);
                man.applyChange(annotationAdd);
            }
        }
        System.out.println("merged: " + URL.substring(URL.lastIndexOf('/') + 1) + "...");
    }

    public Map<String, String> getLabels() {
        //PrintWriter out = new PrintWriter(new FileWriter("labels.txt", true), true);
        int count = 0;
        Set<OWLClass> classes = onto.getClassesInSignature(); // load all the classes in Signature
        OWLDataFactory factory = man.getOWLDataFactory(); // Creat ontology factory
        Map<String, String> labels = new HashMap<String, String>();

        for (OWLClass clazz : classes) {

            count++;
            Set<OWLAnnotationAssertionAxiom> annotations = onto.getAnnotationAssertionAxioms(clazz.getIRI());  //get all the Annotation Assertion of 
            for (OWLAnnotationAssertionAxiom annotation : annotations) {
                if (annotation.getProperty().equals(factory.getRDFSLabel()) && annotation.getValue() instanceof OWLLiteral) {
                    String uri = clazz.getIRI().toString();
                    OWLLiteral lr = (OWLLiteral) annotation.getValue();
                    String result = (String) lr.getLiteral();
                    labels.put(result.trim(), uri);

                    //System.out.println(count + ".  " + result);
                }
            }
        }
        return labels;
    }

    public Map<String, String> getDef() {
        int count = 0;
        Set<OWLClass> classes = onto.getClassesInSignature(); // load all the classes in Signature
        OWLDataFactory factory = man.getOWLDataFactory(); // Creat ontology factory
        Map<String, String> defs = new HashMap<String, String>();

        for (OWLClass clazz : classes) { //for each classes
            count++;
            Set<OWLAnnotationAssertionAxiom> annotations = onto.getAnnotationAssertionAxioms(clazz.getIRI());  //get all the Annotation Assertion of 
            System.out.println(count + ". " + clazz.getIRI().toString());
            for (OWLAnnotationAssertionAxiom an : annotations) {
                System.out.println("annoation property"+an.getProperty());
            }
            System.out.println(" ");

            if (count == 10) {
                System.out.println("finish");
                System.out.println("");
            }

        }

        return defs;
    }

    public OWLOntology getOnto() {
        return onto;
    }
}
