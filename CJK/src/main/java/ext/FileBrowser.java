/* ----------------------------------------------------------------------------
return the list of file name
   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 08-Jul-2016
 */
package ext;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class FileBrowser {

    public ArrayList<String> nameList;
    public int fileCount;

    public void browser(String proPath, String fileType) {

        File folder = new File(proPath);
        FilenameFilter proFilter = new FilenameFilter() {
            @Override
            public boolean accept(File folder, String name) {
                return name.toLowerCase().endsWith("fileType");
            }
        };

        File[] files = folder.listFiles(proFilter);
        for (File file : files) {
            try {
                System.out.println(file.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
