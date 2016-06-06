/*
 * Get the value inside the rdf:label in the following OWL file
    <oboInOwl:hasSynonym>
        <rdf:Description>
            <rdf:type rdf:resource="&oboInOwl;Synonym"/>
            <rdfs:label xml:lang="en">Endocardiums</rdfs:label>
        </rdf:Description>
    </oboInOwl:hasSynonym>
 * 
 * oboInOwl:hasSynonym is an annotation property.
 */
package com.github.eNM;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLOntologyDocumentTarget;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.io.SystemOutDocumentTarget;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAnnotationSubject;
import org.semanticweb.owlapi.model.OWLAnonymousIndividual;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;
//import org.semanticweb.owlapi.model.OWLOntologyFormat;
//import org.semanticweb.owlapi.io.RDFXMLOntologyFormat;

public class LabelExample {
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

