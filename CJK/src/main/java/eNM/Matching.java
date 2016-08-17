package eNM;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.WriteAbortedException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddOntologyAnnotation;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLImportsDeclaration;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.util.OWLOntologyMerger;

/**
 * Matching new terms with all the ontology labels
 *
 * @author jkchang
 */
public class Matching {

    public static void main(String[] args) {

        Map<String, HashSet<MapTerm>> exactMatch = new HashMap<String, HashSet<MapTerm>>();
        Map<String, HashSet<MapTerm>> fuzzyMatch = new HashMap<String, HashSet<MapTerm>>();
        Set<String> keywords = new HashSet<String>();
        Map<String, String> labels = new HashMap<String, String>();

        //====================Keyword set====================================
        String kPath = "src\\main\\resources\\chemical description terms.txt";
        File kFile = new File(kPath);

        try {
            KeywordFile keyword = new KeywordFile(kFile);
            keywords = keyword.getkeywords();
            System.out.println("Finished loading keywords.... ");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("fail to load the keyword file ... " + "\n\n");
        }
        
        //initialize result map
        for (String keyword : keywords) {
            exactMatch.put(keyword, null);
            fuzzyMatch.put(keyword, null);
        }

        //========================Ontology ==================================
        String rootFolder = "..\\ontologies\\config";
        File dir = new File(rootFolder);
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".props");
            }
        });

        for (File file : files) {
            try {
                // load the config file
                Properties props = new Properties();
                props.load(new FileReader(file));
                String owlURL = props.getProperty("owl");
                String owlFilename = props.getProperty("iris");
                if (owlFilename.contains(".")) {
                    owlFilename = owlFilename.substring(0, owlFilename.lastIndexOf('.')) + ".owl";
                }

                System.out.println("=================================");
                System.out.println("\t " + owlFilename);
                System.out.println("=================================");

                // load Ontology & labels
                OntoFile ontoF = new OntoFile(IRI.create(owlURL));
                ontoF.merge();
                labels = ontoF.getLabels();

                //matching
                int count = 0;
                for (String keyword : keywords) {
                    System.out.println(++count + ". matching " + keyword);

                    for (String label : labels.keySet()) {
                        if (label.toLowerCase().equals(keyword.toLowerCase())) {
                            //add mapterm to exactMatch
                            if (exactMatch.get(keyword) == null) {
                                HashSet<MapTerm> mapTerms = new HashSet<MapTerm>();
                                exactMatch.put(keyword, mapTerms);
                            }

                            MapTerm mapT = new MapTerm();
                            mapT.setLabel(label);
                            mapT.setOntoName(owlFilename);
                            mapT.setURI(labels.get(label));
                            exactMatch.get(keyword).add(mapT);
                        }

                        if (label.toLowerCase().contains(keyword.toLowerCase())) {
                            //add mapterm to exactMatch
                            if (fuzzyMatch.get(keyword) == null) {
                                HashSet<MapTerm> mapTerms = new HashSet<MapTerm>();
                                fuzzyMatch.put(keyword, mapTerms);
                            }

                            MapTerm mapT = new MapTerm();
                            mapT.setLabel(label);
                            mapT.setOntoName(owlFilename);
                            mapT.setURI(labels.get(label));
                            fuzzyMatch.get(keyword).add(mapT);
                        }

                    }

                }

                Matching match = new Matching();

                match.saveResToExcel(exactMatch, owlFilename + "_exact");
                match.saveResToExcel(fuzzyMatch, owlFilename + "_fuzzy");

            } catch (OWLOntologyCreationException e) {
                e.printStackTrace();
                System.out.println("fail to creat ontology ... ");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("fail to load the keyword file ... ");
            } catch (WriteException we) {
                we.printStackTrace();
                System.out.println("fail to write data to the excel");
            }

        }

    }

    public void saveResToExcel(Map<String, HashSet<MapTerm>> res, String name) throws WriteException {
        File file = new File("src\\main\\resources\\" + name + ".xls");
        try {
            WritableWorkbook myexcel = Workbook.createWorkbook(file);
            WritableSheet sheet = myexcel.createSheet("new sheet", 0);

            sheet.addCell(new jxl.write.Label(0, 0, "New term"));
            sheet.addCell(new jxl.write.Label(1, 0, "onto Name"));
            sheet.addCell(new jxl.write.Label(2, 0, "Label"));
            sheet.addCell(new jxl.write.Label(3, 0, "URI"));

            int row = 1;
            int count = 0;

            for (String keyword : res.keySet()) {
                System.out.println(++count + ".  " + keyword);
                sheet.addCell(new jxl.write.Label(0, row, keyword));

                jxl.write.Label addOntoName = new jxl.write.Label(1, row, "");
                jxl.write.Label addLab = new jxl.write.Label(2, row, "");
                jxl.write.Label addLabURI = new jxl.write.Label(3, row, "");

                if (res.get(keyword) != null) {
                    HashSet<MapTerm> mapterms = res.get(keyword);
                    for (MapTerm mapterm : mapterms) {
                        sheet.addCell(new jxl.write.Label(1, row, mapterm.getOntoName()));
                        sheet.addCell(new jxl.write.Label(2, row, mapterm.getLabel()));
                        sheet.addCell(new jxl.write.Label(3, row, mapterm.getURI()));
                        row++;
                    }

                } else {
                    row++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fail to write the excel file... ");
        } catch (WriteException we) {
            we.printStackTrace();
            System.out.println("fail to write data to the excel");
        }

    }

}
