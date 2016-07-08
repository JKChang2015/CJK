/* ----------------------------------------------------------------------------
return the list of file name
   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 08-Jul-2016
 */
package ext;

import java.io.File;
import java.io.FilenameFilter;

public class FileBrowser {

    public void browser(String proPath) {

        File folder = new File(proPath);
        FilenameFilter proFilter = new FilenameFilter() {
            @Override
            public boolean accept(File folder, String name) {
                return name.toLowerCase().endsWith(".props");
            }
        };
        
        File[] files = folder.listFiles(proFilter);
        for (File file: files) {
            System.out.println(file.getName());
        }

    }

}
