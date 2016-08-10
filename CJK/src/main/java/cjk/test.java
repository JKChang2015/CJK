/* -----------------------------------------------------------------------------

--------------------------------------------------------------------------------
@author JKChang
@data Aug 10, 2016
 */
package cjk;

import eNM.KeywordFile;
import java.io.File;
import java.util.Set;
import java.util.HashSet;

public class test {

    public static void main(String[] args) {

        String kPath = "src\\main\\resources\\chemical description terms.txt";
        File file = new File(kPath);
        Set<String> keySet = new HashSet<String>();
        try {
            KeywordFile keyword = new KeywordFile(file);
            keySet = keyword.getkeywords();
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("fail to load the keyword file");
        }
        
    }

}
