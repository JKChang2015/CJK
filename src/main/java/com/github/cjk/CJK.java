package com.github.cjk;

import com.github.eNM.Label;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.HashSet;
import java.util.Set;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.RDFXMLDocumentFormat;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.AddOntologyAnnotation;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLImportsDeclaration;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.RemoveImport;
import org.semanticweb.owlapi.model.SetOntologyID;
import org.semanticweb.owlapi.search.EntitySearcher;
import org.semanticweb.owlapi.search.Searcher;
import org.semanticweb.owlapi.util.OWLEntityRemover;
import org.semanticweb.owlapi.util.OWLOntologyMerger;
import org.semanticweb.owlapi.util.SimpleIRIMapper;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;

/**
 *
 * @author jkchang
 */
public class CJK {

    public static void main(String[] args) throws Exception {

        String kPath = "src\\main\\resources\\keyword.txt";
        String ontoPath = "src\\main\\resources\\enanomapper.owl";
        String ontoTest = "src\\main\\resources\\fruit.owl";
        
        Set<String> ontoLabel = new HashSet<String>();
        File owlFile = new File(ontoPath);
        
        Label label = new Label(owlFile);
        ontoLabel = label.getlabel();
        
       
        
       
        BufferedReader keywordReader = new BufferedReader(new FileReader(kPath));
        String line = keywordReader.readLine();
        while (line != null) {
            System.out.println(line);
            line = keywordReader.readLine();
        }
        System.out.println("\n");


        
        
       
        
         /*  
        
        String kPath = "C:\\Users\\jkchang\\Desktop\\keyword.txt";
        String ontoPath = "C:\\Users\\jkchang\\Desktop\\enanomapper.owl";
        String ontoTest = "C:\\Users\\jkchang\\Desktop\\fruit.owl";
        Set<String> keySet = new HashSet<String>();
        Set<OWLClass> entities = new HashSet<OWLClass>();
        Set<String> labelSet = new HashSet<String>();

        File file = new File(kPath);

        try {
            BufferedReader keywordReader = new BufferedReader(new FileReader(file));
            String line = keywordReader.readLine();
            while (line != null) {
                keySet.add(line.trim());
                line = keywordReader.readLine();
            }

        } catch (Exception e) {
            System.out.println("Fail to read the file");
        }

        int count = 1;
        for (String keyword : keySet) {
            System.out.println(count++ + "\t" + keyword);
        }

        OWLOntologyManager man = OWLManager.createOWLOntologyManager();

        try {
            File ontoFile = new File(ontoTest);
            OWLOntology onto = man.loadOntologyFromOntologyDocument(ontoFile);
            Set<OWLClass> cls = onto.getClassesInSignature();
            for (OWLClass cl : cls) {
                String name = cl.toString();
                System.out.println(name);
            }
        } catch (Exception e) {
            System.out.println("fail to load the file");
        }

        System.out.println("lll");

             
        String path = "C:\\Github\\ontologies\\config";
        File folder = new File(path);
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.toString().endsWith("iris")) {
                try {
                    IrisChecker checker = new IrisChecker();
                    checker.IrisChecker(file);
                } catch (Exception e) {
                    System.out.println("can't load the file");
                }
            }
        }
         */
    }
}
