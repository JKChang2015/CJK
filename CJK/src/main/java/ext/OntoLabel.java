package ext;

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

public class OntoLabel {

    private OWLOntologyManager man;
    private OWLOntology onto;
    private Set<String> labels = new HashSet<String>();

    public OntoLabel(File file) throws OWLOntologyCreationException, FileNotFoundException, IOException {

        this(OWLManager.createConcurrentOWLOntologyManager().loadOntologyFromOntologyDocument(file));
    }

    public OntoLabel(OWLOntology onto) {
        //PrintWriter out = new PrintWriter(new FileWriter("labels.txt", true), true);
        int count = 0;
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

    /*
    // creat manager
    final static OWLOntologyManager manager = OWLManager.createOWLOntologyManager(); 
    // manager.factory
    final static OWLDataFactory factory = manager.getOWLDataFactory();
    // base IRI
    final static String ns = "http://example.org/";
    // identify a individual x
    final static OWLIndividual x = factory.getOWLNamedIndividual(IRI.create(ns + "x"));
    // identify a Anonymous individual y
    final static OWLAnonymousIndividual y = factory.getOWLAnonymousIndividual();
    // identify a ObjectProperty 'hasSynonym'
    final static OWLObjectProperty hasSynonym = factory.getOWLObjectProperty(IRI.create(ns + "hasSynonym"));

    public static OWLOntology getSampleOntology() throws OWLOntologyCreationException, OWLOntologyStorageException {
        // creat an ontology
        OWLOntology ontology = manager.createOntology();
        // add Axiom using 
        manager.addAxiom(ontology, factory.getOWLClassAssertionAxiom(factory.getOWLClass(IRI.create(ns + "Synonym")), y));
        
        // add Axiom using hasSynonym link x and y
        // addAxiom(OWLOntology ont, OWLAxiom axiom)
        manager.addAxiom(ontology, factory.getOWLObjectPropertyAssertionAxiom(hasSynonym, x, y));
        
        
        OWLAnnotation ann = factory.getOWLAnnotation(factory.getOWLAnnotationProperty(OWLRDFVocabulary.RDFS_LABEL.getIRI()), factory.getOWLLiteral("Endocardium", "en"));
        manager.addAxiom(ontology, factory.getOWLAnnotationAssertionAxiom(y, ann));
        manager.saveOntology(ontology, System.out);
        return ontology;
    }

    public static void main(String[] args) throws OWLOntologyCreationException, OWLOntologyStorageException {
        OWLOntology ontology = getSampleOntology();
        for (OWLIndividual object : x.getObjectPropertyValues(hasSynonym, ontology)) {
            for (OWLAnnotationAssertionAxiom aAxiom : ontology.getAnnotationAssertionAxioms((OWLAnnotationSubject) object)) {
                if (aAxiom.getProperty().equals(factory.getRDFSLabel())) {
                    System.out.println(aAxiom.getValue());
                }
            }
        }
    } 
     */
}
