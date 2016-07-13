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
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLImportsDeclaration;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;

public class impl {

    public static void main(String[] args) throws Exception {

        File folder = new File("..//ontologies//");
        File[] files = folder.listFiles();

        OWLOntologyManager man = OWLManager.createOWLOntologyManager();
        for (File file : files) {
            man.loadOntologyFromOntologyDocument(file);
        }
        System.out.println("");
        
        
    }
}

//
//        OWLOntologyManager man = OWLManager.createOWLOntologyManager();
//        File file = new File("..//ontologies//bao_complete.owl");
//        OWLOntology onto = man.loadOntologyFromOntologyDocument(file);
//        Set<OWLImportsDeclaration> importDeclarations = onto.getImportsDeclarations();
//
//        for (OWLImportsDeclaration declaration : importDeclarations) {
//            man.loadOntology(declaration.getIRI());
//            System.out.println("Load imported ontology: " + declaration.getIRI());
//        }
//        
//        for(OWLOntology ontology: man.getOntologies()){
//            System.out.println("==============================================");
//            System.out.println(ontology.getOntologyID().toString() );
//            for (OWLAnnotation annotation : ontology.getAnnotations()) {
//                 System.out.println(" Copying annotation: " + annotation.getProperty() + " -> " + annotation.getValue());
//            }
//            System.out.println(" ");
//        }
//        
//
//        System.out.println("");
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
