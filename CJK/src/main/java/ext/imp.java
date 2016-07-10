/* ----------------------------------------------------------------------------

   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 30-Jun-2016
 */
package ext;

public class imp {

    public static void main(String[] args) {
        System.out.println("");
        if (System.getenv("WORKSPACE")!= null) {
            String root = System.getenv("WORKSPACE");
            System.out.println(root);
        }
        System.out.println("finished");
        System.out.println("");
        
        FileBrowser fb = new FileBrowser();
        String path = "C:\\Github\\ontologies\\config\\";
        fb.listFile(path, ".props");
        System.out.println("there are " + fb.getFileCount() + " files");

    }
}
