package cjk;

import eNM.IrisChecker;
import cjk.hanoi;
import cjk.CombinFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.File;
import java.util.Set;
import java.util.HashSet;

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

public class CJK1 {


    public static void main(String[] args) {

        String kPath = "C:\\Users\\jkchang\\Desktop\\keyword.txt";
        String ontoPath = "C:\\Users\\jkchang\\Desktop\\enanomapper.owl";
        String ontoTest = "C:\\Users\\jkchang\\Desktop\\fruit.owl";
        Set<String> keySet = new HashSet<String>();
        Set<OWLClass> entities = new HashSet<OWLClass>();
        Set<String> labelSet = new HashSet<String>();

        File file = new File(kPath);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                keySet.add(line.trim());
                line = reader.readLine();
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

        /*        
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
