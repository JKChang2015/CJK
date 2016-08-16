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
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

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

        Map<String, HashSet<MapTerm>> res = null;

        //====================Keyword set====================================
        String kPath = "src\\main\\resources\\chemical description terms.txt";
        File kFile = new File(kPath);
        Set<String> keySet = new HashSet<String>();
        Set<String> labels = new HashSet<String>();

        try {
            KeywordFile keyword = new KeywordFile(kFile);
            keySet = keyword.getkeywords();
            System.out.println("Finished loading keywords");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("fail to load the keyword file ... ");
        }

        for (String keyword : keySet) {
            res.put(keyword, null);
        }

        //========================Ontology ==================================
        String rootFolder = "..\\ontologies\\config";
        System.out.println("Searching configuration files in folder " + rootFolder);
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

                // load Ontology & labels
                OntoFile ontoF = new OntoFile(IRI.create(owlURL));
                ontoF.merge();
                labels = ontoF.getLabelSet();
                
                

            } catch (OWLOntologyCreationException e) {
                e.printStackTrace();
                System.out.println("fail to creat ontology ... ");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("fail to load the keyword file ... ");
            }
        }

    }

}
