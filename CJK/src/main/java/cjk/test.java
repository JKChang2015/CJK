/* -----------------------------------------------------------------------------

--------------------------------------------------------------------------------
@author JKChang
@data Aug 10, 2016
 */
package cjk;

import eNM.Matching.KeywordFile;
import eNM.Matching.MapTerm;
import eNM.Matching.OntoFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

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

    public static void main(String[] args) throws OWLOntologyCreationException, FileNotFoundException {
        String rootFolder = "..\\ontologies\\config\\";
        
        Map<String, HashSet<String>> map = new HashMap<String, HashSet<String>>();
//        HashSet<String> set = new HashSet<String>();
//        set.add("one");
//        map.put("1", set);
//        set.add("one");
//        map.put("1", set);
//        set.add("one");
//        map.put("1", set);

        map.put("1", null);
        map.put("2", null);
        map.put("3", null);

        if (map.get("1")== null) {
            HashSet<String> set = new HashSet<String>();
            map.put("1", set);
        }

        String add = "111111";
        map.get("1").add(add);

        map.get("1").add("one");
        map.get("1").add("one_one");
        map.get("1").add("yi");

        String path = "C:\\GitHub\\CJK\\ontologies\\fruit.owl";
        File file = new File(path);
        OntoFile onto = new OntoFile(file);

        Map<String, String> res = new HashMap<String, String>();
        res = onto.getLabels();

        int count = 0;

        for (String key : res.keySet()) {
            count++;
            System.out.println(count + ". " + key + "  " + res.get(key));
        }

    }
}

//        //test combine ontologies
//        String owlURL = "";
//        String owlFilename = "";
//
//        String ontoPath = "..\\ontologies\\config\\bao.props";
//        File file = new File(ontoPath);
//        try {
//            System.out.println("loading owl config file from " + ontoPath);
//            Properties props = new Properties();
//            props.load(new FileReader(file));
//
//            owlURL = props.getProperty("owl");
//            owlFilename = owlURL;
//            if (owlFilename.contains("/")) {
//                owlFilename = owlFilename.substring(owlFilename.lastIndexOf('/') + 1);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("fail to load the property file");
//        }
//
//        OWLOntologyManager man = OWLManager.createConcurrentOWLOntologyManager(); //onto manager
//        OWLOntologyMerger merger = new OWLOntologyMerger(man);
//        OWLOntology onto = man.loadOntologyFromOntologyDocument(IRI.create(owlURL)); //input
//        System.out.println("size of the onto is " + onto.getClassesInSignature().size());
//        System.out.println("Loaded ontology:" + owlFilename);
//        String mergURL = new StringBuilder(owlURL).insert(owlURL.lastIndexOf('.'), "_merged").toString();
//        onto = merger.createMergedOntology(man, IRI.create(mergURL)); //output
//
// 
//        for (OWLOntology ontology : man.getOntologies()) {
//            System.out.println(" Copying annotations from " + ontology.getOntologyID().getOntologyIRI().get().toString());
//
//            for (OWLAnnotation annotation : ontology.getAnnotations()) {
//                //System.out.println(" Copying annotation: " + annotation.getProperty() + " -> " + annotation.getValue());
//                AddOntologyAnnotation annotationAdd = new AddOntologyAnnotation(onto, annotation);
//                man.applyChange(annotationAdd);
//            }
//        }
//        System.out.println("size of the merged onto is " + onto.getClassesInSignature().size());
//        System.out.println("\n\n\n" + "======================");
//        System.out.println("merged: " + owlFilename);
//    }
//
//}
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

