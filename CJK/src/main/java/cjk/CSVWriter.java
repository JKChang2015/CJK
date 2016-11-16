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

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CSVWriter {

    //Delimiter used in CSV file
    private static final String NEW_LINE_SEPARATOR = "\n";

    //CSV file header
    private static final Object[] FILE_HEADER = {"id", "firstName", "lastName", "gender", "age"};

    public static void write(String fileName) {

        //Create new students objects
        Student student1 = new Student(1, "Ahmed", "Mohamed", "M", 25);
        Student student2 = new Student(2, "Sara", "Said", "F", 23);
        Student student3 = new Student(3, "Ali", "Hassan", "M", 24);
        Student student4 = new Student(4, "Sama", "Karim", "F", 20);
        Student student5 = new Student(5, "Khaled", "Mohamed", "M", 22);
        Student student6 = new Student(6, "Ghada", "Sarhan", "F", 21);

        //Create a new list of student objects
        List students = new ArrayList();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        students.add(student6);

        FileWriter fileWriter = null;
        CSVPrinter csvFilePrinter = null;

        //Create the CSVFormat object with "\n" as a record delimiter
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

        try {
            //initialize FileWriter object
            fileWriter = new FileWriter(fileName);

            //initialize CSVPrinter object 
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

            //Create CSV file header
            csvFilePrinter.printRecord(FILE_HEADER);

            for (int i = 0; i < students.size(); i++) {
                Student student = (Student) students.get(i);
                List studentDataRecord = new ArrayList();
                studentDataRecord.add(String.valueOf(student.getId()));
                studentDataRecord.add(student.getFirstName());
                studentDataRecord.add(student.getLastName());
                studentDataRecord.add(student.getGender());
                studentDataRecord.add(String.valueOf(student.getAge()));
                csvFilePrinter.printRecord(studentDataRecord);
            }
            System.out.println("CSV file was created successfully ... ");

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter ... ");
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
                csvFilePrinter.close();
            } catch (Exception e) {
                System.out.println("Error while flushing/ closing fileWriter/ csvPrinter");
                e.printStackTrace();
            }

        }

    }

    public static void main(String[] args) {
        
         CSVWriter t = new  CSVWriter();
         t.write("abb.csv");

    }
}
//        String rootFolder = "..\\ontologies\\config\\";
//        
//        Map<String, HashSet<String>> map = new HashMap<String, HashSet<String>>();
//        HashSet<String> set = new HashSet<String>();
//        set.add("one");
//        map.put("1", set);
//        set.add("one");
//        map.put("1", set);
//        set.add("one");
//        map.put("1", set);

//        map.put("1", null);
//        map.put("2", null);
//        map.put("3", null);
//
//        if (map.get("1")== null) {
//            HashSet<String> set = new HashSet<String>();
//            map.put("1", set);
//        }
//
//        String add = "111111";
//        map.get("1").add(add);
//
//        map.get("1").add("one");
//        map.get("1").add("one_one");
//        map.get("1").add("yi");
//
//        String path = "C:\\GitHub\\CJK\\ontologies\\fruit.owl";
//        File file = new File(path);
//        OntoFile onto = new OntoFile(file);
//
//        Map<String, String> res = new HashMap<String, String>();
//        res = onto.getLabels();
//
//        int count = 0;
//
//        for (String key : res.keySet()) {
//            count++;
//            System.out.println(count + ". " + key + "  " + res.get(key));
//        }
//
//    }
//}
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

