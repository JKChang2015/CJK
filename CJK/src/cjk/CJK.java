package cjk;

import cjk.hanoi;
import cjk.CombinFile;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.File;

public class CJK {

    public static void main(String[] args) {

        /*
        String path = "C:\\Github\\ontologies\\config";
        File folder = new File(path);
        File[] files = folder.listFiles();      
       
        try {
             CombinFile dd = new CombinFile(path);
        } catch (Exception e) {
            System.out.println("Error");
        } */
        String path = "C:\\Github\\CJK\\CJK\\output.txt";

        try {
            Reader reader = new FileReader(path);
            IrisChecker checker = new IrisChecker();
            checker.IrisChecker(reader);
           } catch (Exception e) {
            System.out.println("can't load the file");
        }
        
        
    }

}
