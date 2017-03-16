/* ----------------------------------------------------------------------------
return the list of file name
   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 08-Jul-2016
 */
package Ext;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import javax.swing.Action;

public class FileBrowser {

    public ArrayList<String> nameList = new ArrayList<>();

    
    public void listFile(String proPath, String fileType) {

        File folder = new File(proPath);
        FilenameFilter proFilter = new FilenameFilter() { //override filenameFilter
            @Override
            public boolean accept(File folder, String name) {
                return name.toLowerCase().endsWith(fileType);
            }
        };

        File[] files = folder.listFiles(proFilter);
        for (File file : files) {
            try {
                nameList.add(file.getName());
                System.out.println(file.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    
    
    public int getFileCount() {
        return nameList.size();
    }

    public static void main(String[] args) {
        FileBrowser br = new FileBrowser();
        br.listFile("/Users/jkchang/Downloads", "txt");
        System.out.println(br.getFileCount());

    }

}
