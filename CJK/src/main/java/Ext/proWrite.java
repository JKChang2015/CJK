/* ----------------------------------------------------------------------------
java.util.properties
   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 06-Jul-2016
 */
package Ext;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class proWrite {

    public static void main(String[] args) {

        Properties prop = new Properties();
        OutputStream output = null;

        try {
            output = new FileOutputStream("config.properties");
            prop.setProperty("pro1", "ppp1");
            prop.setProperty("pro2", "ppp2");
            prop.setProperty("pro3", "ppp3");

            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    

}
