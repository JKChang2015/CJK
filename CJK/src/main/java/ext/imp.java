/* ----------------------------------------------------------------------------

   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 30-Jun-2016
 */
package ext;

public class imp {

    public static void main(String[] args) {
        FileBrowser fb = new FileBrowser();
        String path = "C:\\Github\\ontologies\\config\\";
        fb.listFile(path, ".props");
        System.out.println("there are " + fb.getFileCount() + "files");

    }
}
