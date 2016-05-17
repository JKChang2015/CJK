package cjk;

import eNM.IrisChecker;
import cjk.hanoi;
import cjk.CombinFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.File;
import java.util.Set;
import java.util.HashSet;
import org.semanticweb.owlapi.*;


public class CJK {

    public static void main(String[] args) {

        String kPath = "C:\\Users\\jkchang\\Desktop\\keyword.txt";
        String ontoPath = "C:\\Users\\jkchang\\Desktop\\enanomapper.owl";
        Set<String> keySet = new HashSet<String>();
        Set<String> labelSet = new HashSet<String>();

        File file = new File(kPath);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                keySet.add(line.trim());
                line = reader.readLine();
            }         

        } catch (Exception e) {
            System.out.println("Fail to read the file");
        }
        
        int count = 1;
        for (String keyword: keySet) {
            System.out.println(count++ + "\t" + keyword);
        }

        /*        
        String path = "C:\\Github\\ontologies\\config";
        File folder = new File(path);
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.toString().endsWith("iris")) {
                try {
                    IrisChecker checker = new IrisChecker();
                    checker.IrisChecker(file);
                } catch (Exception e) {
                    System.out.println("can't load the file");
                }
            }
        }
         */
    }
}
