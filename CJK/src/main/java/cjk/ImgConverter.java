package cjk;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * Creat a gif format annotation from multiple images
 *
 * @param source
 * @param formatName
 * @param result
 * @date 2016/07/11
 * @author JKChang
 */
public class ImgConverter {

    public static void convert(String source, String formatName, String result) {
        try {
            File f = new File(source);
            f.canRead();
            BufferedImage src = ImageIO.read(f);
            ImageIO.write(src, formatName, new File(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
