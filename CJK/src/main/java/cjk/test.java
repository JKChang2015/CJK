/* -----------------------------------------------------------------------------

--------------------------------------------------------------------------------
@author JKChang
@data Aug 10, 2016
 */
package cjk;

import eNM.KeywordFile;
import java.io.File;
import java.io.FileReader;
import java.util.Set;
import java.util.HashSet;
import java.util.Properties;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddOntologyAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.util.OWLOntologyMerger;

public class test {

    public static void main(String[] args) throws OWLOntologyCreationException {

        //test combine ontologies
        String owlURL = "";
        String owlFilename = "";

        String ontoPath = "..\\ontologies\\config\\bao.props";
        File file = new File(ontoPath);
        try {
            System.out.println("loading owl config file from " + ontoPath);
            Properties props = new Properties();
            props.load(new FileReader(file));

            owlURL = props.getProperty("owl");
            owlFilename = owlURL;
            if (owlFilename.contains("/")) {
                owlFilename = owlFilename.substring(owlFilename.lastIndexOf('/') + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("fail to load the property file");
        }

        OWLOntologyManager man = OWLManager.createConcurrentOWLOntologyManager();; //onto manager
        OWLOntologyMerger merger = new OWLOntologyMerger(man);
        OWLOntology onto = man.loadOntologyFromOntologyDocument(IRI.create(owlURL)); //input
        System.out.println("size of the onto is " + onto.getClassesInSignature().size());
        System.out.println("Loaded ontology:" + owlFilename);
        onto = merger.createMergedOntology(man, IRI.create(owlURL + "_merg")); //output

        for (OWLOntology ontology : man.getOntologies()) {
            System.out.println(" Copying annotations from " + ontology.getOntologyID());

            for (OWLAnnotation annotation : ontology.getAnnotations()) {
                //System.out.println(" Copying annotation: " + annotation.getProperty() + " -> " + annotation.getValue());
                AddOntologyAnnotation annotationAdd = new AddOntologyAnnotation(onto, annotation);
                man.applyChange(annotationAdd);
            }
        }
        System.out.println("size of the merged onto is " + onto.getClassesInSignature().size());
        System.out.println("\n\n\n" + "======================");
        System.out.println("merged: " + owlFilename);
    }

}

//test loading terms from keyword file
//        String kPath = "src\\main\\resources\\chemical description terms.txt";
//        File file = new File(kPath);
//        Set<String> keySet = new HashSet<String>();
//        try {
//            KeywordFile keyword = new KeywordFile(file);
//            keySet = keyword.getkeywords();
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("fail to load the keyword file");
//        }

