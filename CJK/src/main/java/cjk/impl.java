/* ----------------------------------------------------------------------------

   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 29-Jun-2016
 */
package cjk;

import java.io.File;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Set;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLImportsDeclaration;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;

public class impl {

    public static void main(String[] args) throws Exception {

        OWLOntologyManager man = OWLManager.createOWLOntologyManager();
        File file = new File("..//ontologies//bao_complete.owl");
        OWLOntology onto = man.loadOntologyFromOntologyDocument(file);
        Set<OWLImportsDeclaration> importDeclarations = onto.getImportsDeclarations();
        
        System.out.println("");
        for (OWLImportsDeclaration declaration : importDeclarations) {

        }

        System.out.println("");

//        System.out.println(System.getenv("WORKSPACE"));
//        System.out.println("");
//
//        String folerPath = "C:\\Github\\ontologies\\external\\";
//        File file = new File(folerPath);
//        if (file.exists()) {
//            File[] files = file.listFiles();
//            for (File f : files) {
//                if (f.toString().endsWith("owl")) {
//                    String name = f.getName();
//                    System.out.println(name);
//                }
//
//            }
//
//        }
    }
}
