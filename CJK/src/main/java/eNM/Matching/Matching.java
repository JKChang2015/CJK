package eNM.Matching;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import jxl.write.WriteException;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

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
        int exact = 0;
        int fuzzy = 0;

        //====================Keyword set====================================
   //     String kPath = "src\\main\\resources\\chemical description terms.txt";
        String kPath = "src\\main\\resources\\NM components types.txt";
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
                System.out.println("matching keywords with " + owlFilename + "....");
                int extCount = 0;
                int fuzzyCount = 0;

                for (String keyword : keywords) {

                    for (String label : labels.keySet()) {

                        if (label.toLowerCase().contains(keyword.toLowerCase())) {
                            //add mapterm to exactMatch
                            if (fuzzyMatch.get(keyword) == null) {
                                HashSet<MapTerm> mapTerms = new HashSet<MapTerm>();
                                fuzzyMatch.put(keyword, mapTerms);
                            }

                            MapTerm mapT = new MapTerm(owlFilename, label, labels.get(label));
                            fuzzyMatch.get(keyword).add(mapT);
                            fuzzyCount++;
                            fuzzy++;
                        }

                        if (label.toLowerCase().equals(keyword.toLowerCase())) {

                            //add mapterm to exactMatch
                            if (exactMatch.get(keyword) == null) {
                                HashSet<MapTerm> mapTerms = new HashSet<MapTerm>();
                                exactMatch.put(keyword, mapTerms);
                            }

                            MapTerm mapT = new MapTerm(owlFilename, label, labels.get(label));
                            exactMatch.get(keyword).add(mapT);
                            extCount++;
                            exact++;
                        }

                    }

                }

                System.out.println(extCount + " exact matching....");
                System.out.println(fuzzyCount + " fuzzy matching....");

            } catch (OWLOntologyCreationException e) {
                e.printStackTrace();
                System.out.println("fail to creat ontology ... ");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("fail to load the keyword file ... ");
            }

        } // end for each ontology

        try {
            SaveToExcel se = new SaveToExcel();
            se.save(exactMatch, "exact");
            System.out.println("save " + exact + " exact match....");
            se.save(fuzzyMatch, "fuzzy");
            System.out.println("save " + fuzzy + " fuzzy match....");

            int fcc = 0;
            for (String key : fuzzyMatch.keySet()) {
                if (fuzzyMatch.get(key) != null) {
                    fcc++;
                }
            }

            System.out.println("There are " + fcc + " new terms have been fuzzy matched");

            int ecc = 0;
            for (String key : exactMatch.keySet()) {
                if (fuzzyMatch.get(key) != null) {
                    ecc++;
                }
            }
            System.out.println("There are " + ecc + " new terms have been exact matched");

        } catch (WriteException we) {
            we.printStackTrace();
            System.out.println("fail to write data to the excel");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("fail to load the keyword file ... ");
        }

    }

}
