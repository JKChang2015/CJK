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
    FilenameFilter filter = (File folder1, String name) -> name.toLowerCase().endsWith(".props");

}
