package com.github.eNM;

import java.io.InputStream;
import java.io.File;
import java.util.Set;
import java.util.HashSet;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.search.EntitySearcher;
import org.semanticweb.owlapi.model.OWLDataFactory;

public class Label {

    private OWLOntologyManager man;
    private OWLOntology onto;
    private Set<String> labels = new HashSet<String>();

    public Label(String owlPath) throws OWLOntologyCreationException {

        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(owlPath);
        man = OWLManager.createOWLOntologyManager();
        OWLDataFactory fatory = man.getOWLDataFactory();
        onto = man.loadOntologyFromOntologyDocument(stream);
        
        Set<OWLClass> entities = onto.getClassesInSignature();
        
        System.out.println("");
        System.out.println("");

        for (OWLClass clas : entities) {
            for (OWLAnnotation annot : EntitySearcher.getAnnotations(clas, onto)) {
                String lab = annot.getValue().toString();
            }
        }

    }

    public Set<String> getlabel() {
        return labels;
    }

}
