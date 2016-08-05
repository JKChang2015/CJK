/* ----------------------------------------------------------------------------

   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 04-Aug-2016
 */
package eNM;

import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import eNM.MapTerm;
import java.io.InputStream;
import java.io.File;
import java.io.FilenameFilter;
import org.semanticweb.owlapi.apibinding.OWLManager;

import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

public class Mapper {

    private Map<String, MapTerm> mapping;
    private OWLOntologyManager man;
    private OWLOntology onto;

    public static void main(String[] args) {
        String rootFolder = "..\\ontologies\\config";
        System.out.println("Loading configuration files in folder " + rootFolder);

        File dir = new File(rootFolder);
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".props");
            }
        });

        for (File file : files) {

        }

    }

    void combine(InputStream owlFile, String mergedOntologyIRI) throws OWLOntologyCreationException {
        man = OWLManager.createConcurrentOWLOntologyManager();
        onto = man.loadOntologyFromOntologyDocument(owlFile);
    }

}
