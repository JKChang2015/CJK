package eNM;

import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import eNM.MapTerm;
import java.io.InputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddOntologyAnnotation;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLImportsDeclaration;

import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.util.OWLOntologyMerger;

/* 
 @author jkchang
 @date 04-Aug-2016
 Task: mapping query terms with the external ontologies
Steps: 1. load query terms
       2. laad ontologies
        2.1 merging ontologies
        2.2 normalization
       3. mapping terms with each ontologies
       
 */
public class Mapper {

    private Map<String, MapTerm> mapping;
    private OWLOntologyManager man;
    private OWLOntology onto;
    private HashSet<String> keySet;

    public static void main(String[] args) {

        //keywords
        String keywordPath = "";

        //ontology documents
        String rootFolder = "..\\ontologies\\config";
        System.out.println("Loading configuration files in folder " + rootFolder);
        File dir = new File(rootFolder);  //filted the folder
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".props");
            }
        });

        for (File file : files) {
            
            // 1. combine the ontology
        }

    }

    public void loadKeyword(String path) {
        File kFile = new File(path);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(kFile));
            String line = reader.readLine();
            while (line != null) {
                keySet.add(line.trim());
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println("Fail to read the keyword file");
        }
    }

    public void combine(InputStream owlFile, String mergedOntologyIRI) throws OWLOntologyCreationException {
        man = OWLManager.createConcurrentOWLOntologyManager();
        onto = man.loadOntologyFromOntologyDocument(owlFile);
        if (mergedOntologyIRI != null) {
            // load all DIRECt imports ontologies
            Set<OWLImportsDeclaration> importDeclarations = onto.getImportsDeclarations();
            for (OWLImportsDeclaration declaration : importDeclarations) {
                try {
                    man.loadOntology(declaration.getIRI());
                    System.out.println("Loaded imported ontology: " + declaration.getIRI());

                } catch (Exception exception) {
                    exception.printStackTrace();
                    System.out.println("Failed to load imported ontology: " + declaration.getIRI());
                }
            }
            // merge ontologies, specifying an IRI for the new ontology
            OWLOntologyMerger merger = new OWLOntologyMerger(man);
            onto = merger.createMergedOntology(man, IRI.create(mergedOntologyIRI));
            for (OWLOntology ontology : man.getOntologies()) {
                System.out.println(" Copying annotations from " + ontology.getOntologyID());

                for (OWLAnnotation annotation : ontology.getAnnotations()) {
                    System.out.println(" Copying annotation: " + annotation.getProperty() + " -> " + annotation.getValue());
                    AddOntologyAnnotation annotationAdd = new AddOntologyAnnotation(onto, annotation);
                    man.applyChange(annotationAdd);
                }
            }

        }
    }

}
