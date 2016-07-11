/* ----------------------------------------------------------------------------

   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 29-Jun-2016
 */
package cjk;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class impl {

    public static void main(String[] args) {
       
        String folerPath = "C:\\Github\\ontologies\\external\\";
        File file = new File(folerPath);
        if (file.exists()) {
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.toString().endsWith("owl")) {
                    String name = f.getName();
                    System.out.println(name);
                }

            }

        }

    }
}
