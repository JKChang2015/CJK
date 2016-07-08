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

    String proPath = "C:\\Github\\ontologies\\config\\";
    File folder = new File(proPath);
    FilenameFilter filter = new FilenameFilter() {
        public boolean accept(File folder, String name) {
            return name.toLowerCase().endsWith(".props");
        }
    };

}
