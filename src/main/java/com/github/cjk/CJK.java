package com.github.cjk;

import com.github.eNM.Label;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.search.Searcher;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.RemoveImport;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.SetOntologyID;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.util.SimpleIRIMapper;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.search.EntitySearcher;
import org.semanticweb.owlapi.util.OWLEntityRemover;
import org.semanticweb.owlapi.util.OWLOntologyMerger;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLAnnotationValue;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.AddOntologyAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLImportsDeclaration;
import org.semanticweb.owlapi.formats.OWLXMLDocumentFormat;
import org.semanticweb.owlapi.formats.RDFXMLDocumentFormat;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLLiteral;
import uk.ac.manchester.cs.owl.owlapi.*;

/**
 *
 * @author jkchang
 */
public class CJK {

    public static void main(String[] args) throws Exception {

        String kPath = "src\\main\\resources\\keyword.txt";
        String ontoPath = "src\\main\\resources\\enm.owl";
        String ontoTest = "src\\main\\resources\\fruit.owl";

        Set<String> keywords = new HashSet<String>();
        Set<String> ontoLabel = new HashSet<String>();

        // 1. Load keywords
        try {
            BufferedReader keywordReader = new BufferedReader(new FileReader(kPath));
            String line = keywordReader.readLine().trim();
            while (line != null) {
                line = keywordReader.readLine().trim();
                keywords.add(line);
            }
            System.out.println("\n");
        } catch (Exception e) {
            System.out.println("Fail to load the keyword file form " + kPath);
        }

        // record the results
        PrintWriter out = new PrintWriter(new FileWriter("labels.txt", true), true);
        int count = 1;

        //2. Load ontology labels
        try {
            File file = new File(ontoPath);
            OWLOntologyManager man = OWLManager.createOWLOntologyManager();
            OWLDataFactory factory = man.getOWLDataFactory();
            OWLOntology onto = man.loadOntologyFromOntologyDocument(file);
            Set<OWLClass> classes = onto.getClassesInSignature();

            for (OWLClass en : classes) {
                System.out.println(count + ".  " + en.toString() + "\t\t\t\t ");
                out.println(count + ".  " + en.toString() + "\t\t\t\t ");
                count++;
                Set<OWLAnnotationAssertionAxiom> ann = onto.getAnnotationAssertionAxioms(en.getIRI());
                for (OWLAnnotationAssertionAxiom axiom : ann) {
                    if (axiom.getProperty().equals(factory.getRDFSLabel()) && axiom.getValue() instanceof OWLLiteral) {
                        OWLLiteral lr = (OWLLiteral) axiom.getValue();
                        String result = (String) lr.getLiteral();
                        System.out.println(result + "\n");
                        out.println(result);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Fail to load the OWLfile form " + ontoPath);
        }
        out.close();

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
