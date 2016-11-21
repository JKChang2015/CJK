/* ----------------------------------------------------------------------------

   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 30-Jun-2016
 */
package Demo;

import Ext.AnimatedGIFEncoder;
import java.io.File;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class CreatGIF {

    public static void main(String[] args) {
        AnimatedGIFEncoder ae = new AnimatedGIFEncoder();
        String folderPath = "..//records//";
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        ae.start("res.gif");
        for (File file : files) {
            try {
                BufferedImage img = ImageIO.read(file);
                ae.setDelay(500);
                ae.addFrame(img);
                // BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ae.finish();

    }
}
