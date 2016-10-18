package eNM.ExcelToOWL;

import java.io.IOException;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Load term list from excel file and add to OWL file. Function: 1. load excel
 * (term name, superclass and definition) 2. load owlfile as txt file 3. convert
 * term list to owl file 4. save the owl file
 *
 * The basic information need to be added to ontology: 1. term name 2. url 3.
 * term label 4. definition 5. superclass
 *
 * If possible record the new term's url to output
 *
 * @author jkchang
 */
public class ExcelToOWL {

    public static void main(String[] args) throws IOException {

        String owlFile = "src\\main\\resources\\add.owl";
        String outputOWLFile = "src\\main\\resources\\res.owl";
        String termExcelFile = "src\\main\\resources\\new terms.xls";

        String termURI = "http://purl.enanomapper.org/onto/ENM_0";
        int startID = 5;

        //save new terms to Array
        ArrayList<OntoEntity> ontoEnti = new ArrayList<OntoEntity>();
        ReadExcel reader = new ReadExcel();
        reader.setInputFile(termExcelFile);
        ontoEnti = reader.read();

        // copy the owl file to output;
        File fin = new File(owlFile);
        FileInputStream fis = new FileInputStream(fin);
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));

        FileWriter fstream = new FileWriter(outputOWLFile, false); //overwrite
        BufferedWriter out = new BufferedWriter(fstream);

        String aLine = null;
        while ((aLine = in.readLine()) != null) {
            if (aLine.contains("</rdf:RDF>")) {
                break;
            }
            out.write(aLine);
            out.newLine();
        }

        in.close();
        out.close();

        //write new entity to the file
        try {
            File file = new File("src\\main\\resources\\res.owl");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for (OntoEntity oe : ontoEnti) {
                String ID = String.format("%06d", startID);
                String uri = termURI + ID;
                oe.setUri(uri);
                startID++;

                pw.println("");
                pw.println(oe.printSpliter());
                pw.println("");
                pw.println(oe.printStrt() + oe.printUri());
                pw.println(oe.printSupURI());
                pw.println(oe.printDef());
                pw.println(oe.printLable());
                pw.println(oe.printEnd());
                pw.println("");
                pw.println("");
                pw.println("");
            }

            pw.printf("</rdf:RDF>");
            pw.printf("");
            pw.printf("<!-- Generated by the OWL API (version 4.2.6.20160910-2108) https://github.com/owlcs/owlapi -->");

            pw.close();
            System.out.println("Data successfully appended at the end of file");

        } catch (Exception ioe) {
            System.out.println("Exception occurred:");
            ioe.printStackTrace();
        }

//        String supUri = "http://purl.enanomapper.org/onto/ENM_0000002";
//        String def = "banana def";
//        String label = "banana";
//
//        OntoEntity oe = new OntoEntity();
//        oe.setLabel(label);
//        oe.setDef(def);
//        oe.setUri(uri);
//        oe.setSupURI(supUri);
//
//        System.out.println(oe.printSpliter());
//        System.out.println(oe.printStrt() + oe.printUri());
//        System.out.println(oe.printSupURI());
//        System.out.println(oe.printDef());
//        System.out.println(oe.printLable());
//        System.out.println(oe.printEnd());
//        System.out.println("");

    }
}
