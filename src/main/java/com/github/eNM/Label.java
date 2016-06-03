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

public class Label {

    private OWLOntologyManager man;
    private OWLOntology onto;
    private Set<String> labels = new HashSet<String>();

    public Label(File owlFile) throws OWLOntologyCreationException {
        man = OWLManager.createOWLOntologyManager();
        onto = man.loadOntologyFromOntologyDocument(owlFile);
        Set<OWLClass> entities = onto.getClassesInSignature();

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
