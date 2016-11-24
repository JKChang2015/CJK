package eNanoMapper;

import java.io.File;
import java.io.IOException;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddOntologyAnnotation;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.util.OWLOntologyMerger;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.parameters.Imports;

/**
 *
 * @author JKChang
 * @date 24-Nov-2016
 * @Description:
 *
 */
public class OntoMerger {

    private OWLOntologyManager man;
    private OWLOntology onto;

    //===========================constructor================================
    public OntoMerger(File owlFile) throws
            OWLOntologyCreationException, IOException {
        man = OWLManager.createConcurrentOWLOntologyManager();
        onto = man.loadOntologyFromOntologyDocument(owlFile);
    }

    public OntoMerger(String owlIRI) throws
            OWLOntologyCreationException, IOException {
        man = OWLManager.createConcurrentOWLOntologyManager();
        onto = man.loadOntologyFromOntologyDocument(IRI.create(owlIRI));
    }

    public OntoMerger(OWLOntology ontology) throws
            OWLOntologyCreationException, IOException {
        this.onto = ontology;
    }
    //=======================================================================
    
    public OWLOntology merge() {
        IRI iri = onto.getOntologyID().getOntologyIRI().get();
        System.out.println("Loaded ontology:" + iri);

        try {
            OWLOntologyMerger merger = new OWLOntologyMerger(man);
            onto = merger.createMergedOntology(man, IRI.create(iri + "_merg")); //output

            for (OWLOntology ontol : man.getOntologies()) {
                System.out.println(iri + " Copying annotations from "
                        + onto.getOntologyID().getOntologyIRI().get().toString());

                for (OWLAnnotation annotation : onto.getAnnotations()) {
                    //System.out.println(" Copying annotation: " + annotation.getProperty() + " -> " + annotation.getValue());
                    AddOntologyAnnotation annotationAdd = new AddOntologyAnnotation(onto, annotation);
                    man.applyChange(annotationAdd);
                }
            }

            System.out.println("Merged ontology: " + iri);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("fail to merge the ontology");
        }

        return onto;
    }

    public void merge(String owlURL) throws OWLOntologyCreationException {
        man = OWLManager.createConcurrentOWLOntologyManager();
        onto = man.loadOntologyFromOntologyDocument(IRI.create(owlURL)); //imput
        String owlFilename = owlURL;

        System.out.println("Loaded ontology: " + owlFilename);

        try {

            OWLOntologyMerger merger = new OWLOntologyMerger(man);
            onto = merger.createMergedOntology(man, IRI.create(owlURL + "_merg")); //output

            for (OWLOntology ontology : man.getOntologies()) {
                System.out.println(owlFilename + " Copying annotations from "
                        + ontology.getOntologyID().getOntologyIRI().get().toString());

                for (OWLAnnotation annotation : ontology.getAnnotations()) {
                    //System.out.println(" Copying annotation: " + annotation.getProperty() + " -> " + annotation.getValue());
                    AddOntologyAnnotation annotationAdd = new AddOntologyAnnotation(onto, annotation);
                    man.applyChange(annotationAdd);
                }
            }

            System.out.println(
                    "Merged ontology: " + owlFilename);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("fail to merge the ontology");
        }

    }

    public OWLOntology getOnto() {
        return onto;
    }

}
