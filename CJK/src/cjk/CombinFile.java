package cjk;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;

public class CombinFile {

    public CombinFile(String path) throws Exception {

        File folder = new File(path);
        File[] files = folder.listFiles();
        PrintWriter out = new PrintWriter(new FileWriter("output.txt", true), true);
        int fileCounter = 0;

        for (File file : files) {

            if (file.toString().endsWith("iris")) {
                fileCounter++;
                //System.out.println("");
               // System.out.println("========" + fileCounter + ".  " + file.toString() + "========");
               // out.println();
               // out.println("========" + fileCounter + ".  " + file.toString() + "========");
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = reader.readLine().trim();
                while (line != null) {
                    out.println(line);
                    System.out.println(line);
                    line = reader.readLine();
                }
            }
        }
        out.close();

    }

}
