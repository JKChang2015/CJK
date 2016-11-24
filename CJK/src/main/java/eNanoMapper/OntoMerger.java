package eNanoMapper;

import java.io.File;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddOntologyAnnotation;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.util.OWLOntologyMerger;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

/**
 *
 * @author JKChang
 * @date 24-Nov-2016
 * @Description:
 *
 */
public class OntoMerger {

    public void merge(File owlFile) throws OWLOntologyCreationException {
        OWLOntologyManager man = OWLManager.createConcurrentOWLOntologyManager();
        OWLOntology onto = man.loadOntologyFromOntologyDocument(owlFile); 
        IRI iri = onto.getOntologyID().getOntologyIRI().get();
        this.merge(iri.toString());     
    }

    public void merge(String owlURL) throws OWLOntologyCreationException {
        OWLOntologyManager man = OWLManager.createConcurrentOWLOntologyManager();
        OWLOntology onto = man.loadOntologyFromOntologyDocument(IRI.create(owlURL)); //imput
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

}
