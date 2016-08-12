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
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;

public class test {

    public static void main(String[] args) {

        //test combine ontologies
        String ontoPath = "..\\ontologies\\config\\bao.props";
        File file = new File(ontoPath);
        OWLOntology comOnto;

        try {
            System.out.println("loading owl config file from " + ontoPath);
            Properties props = new Properties();
            props.load(new FileReader(file));

            String owlURL = props.getProperty("owl");
            String owlFilename = owlURL;
            if (owlFilename.contains("/")) {
                owlFilename = owlFilename.substring(owlFilename.lastIndexOf('/') + 1);
            }

            OWLOntologyManager man = OWLManager.createConcurrentOWLOntologyManager();
            OWLOntology onto = man.loadOntology(IRI.create(owlURL));
            System.out.println("");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("fail to load the property file");
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
    }

}
