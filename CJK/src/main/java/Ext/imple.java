package Ext;

import eNanoMapper.Curation.OntoEntity;
import eNanoMapper.Curation.Curator;
import eNanoMapper.Util.CsvFileReader;
import eNanoMapper.OntoMerger;
import eNM.Matching.*;

import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Set;
import java.util.HashSet;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import eNanoMapper.OntoLabel;

/**
 *
 * @author JKChang
 * @date 17-Nov-2016
 * @Description:
 *
 */
public class imple {

    public static void main(String[] args) throws OWLOntologyCreationException {
        File file = new File ("src\\main\\resources\\iao-ext.owl");
        OntoMerger merger = new OntoMerger();
        merger.merge(file);
        
        
        
        
        
//        //test csvFile Reader, throws IOException
//        CsvFileReader reader = new CsvFileReader();
//        String[] header = {"name", "label", "URI", "supname", "supuri", "def"};
//        File csvFile = new File("fruit.csv");
//        reader.read(header, csvFile);

        
        
// Test List all the entity lables:  throws OWLOntologyCreationException, FileNotFoundException, IOException
//        File file = new File ("src\\main\\resources\\iao-ext.owl");
//        OntoLabel labels = new OntoLabel(file);
//        
//        Set<String> result = labels.getlabel();
//        for (String label : result) {
//            System.out.println(label);
//        }
        // Ontology Example:
//        OntoEntity cl = new OntoEntity();
//        cl.setName("algorithm type");
//        cl.setLabel("algorithm type LABEL");
//        cl.setUri("http://purl.enanomapper.org/onto/ENM_8000001");
//        cl.setDef("A chemical entity information format specification is an information content entity which specifies a format in which information about a molecular entity should be encoded. ");
//        cl.setSupURI("http://purl.obolibrary.org/obo/IAO_0000030");
//        cl.setSupClassName("information content entity");
//        
//        Curator curator = new Curator();
//        System.out.println(curator.curate(cl));
    }

}
