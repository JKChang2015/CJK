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

    }
}
