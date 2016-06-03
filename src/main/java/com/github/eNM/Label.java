package com.github.eNM;

import java.io.InputStream;
import java.io.File;
import java.util.Set;
import java.util.HashSet;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;


public class Label {

    private OWLOntologyManager man;
    private OWLOntology onto;
    private Set<String> labels = new HashSet<String>();

    public Label(File owlFile) throws OWLOntologyCreationException {
        man = OWLManager.createOWLOntologyManager();
        onto = man.loadOntologyFromOntologyDocument(owlFile);
        
        
    }
    
    public Set<String> getlabel(){
        return labels;
    }

}
