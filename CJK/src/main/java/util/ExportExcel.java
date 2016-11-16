package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author JKChang
 * @date 16-Nov-2016
 * @Description:
 *
 */
public class ExportExcel {
    
    // save(Map<String, HashSet<MapTerm>> res, String name) throws WriteException, IOException {

    public void save(Map map, ArrayList title, String savePath, String saveName) {
        
        File file = new File(savePath);
        HSSFWorkbook workbook = new HSSFWorkbook();

        try {
            FileOutputStream out = new FileOutputStream(new File("C:\\new.xls"));
            workbook.write(out);
            out.close();
            System.out.println("Excel written successfully..");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
