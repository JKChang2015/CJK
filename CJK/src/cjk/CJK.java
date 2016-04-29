package cjk;

import cjk.hanoi;
import java.io.BufferedReader;
import java.io.StringReader;
import java.io.File;

public class CJK {

    public static void main(String[] args) {
        String path = "C:\\Github\\ontologies\\config";
        File file = new File(path);
        File [] files = file.listFiles();
        
        for(File temp : files){
            System.out.println(temp.toString());
        }
        
    }

}
