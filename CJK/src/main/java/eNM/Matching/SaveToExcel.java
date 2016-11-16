package eNM.Matching;

import eNM.Matching.MapTerm;
import java.util.HashSet;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;


/**
 *
 * @author jkchang
 */
public class SaveToExcel {

    public void save(Map<String, HashSet<MapTerm>> res, String name) throws WriteException, IOException {

        File file = new File("src\\main\\resources\\" + name + ".xls");
        WritableWorkbook myexcel = Workbook.createWorkbook(file);
        WritableSheet sheet = myexcel.createSheet("new sheet", 0);

        sheet.addCell(new jxl.write.Label(0, 0, "New term"));
        sheet.addCell(new jxl.write.Label(1, 0, "Onto Name"));
        sheet.addCell(new jxl.write.Label(2, 0, "Label"));
        sheet.addCell(new jxl.write.Label(3, 0, "URI"));

        int row = 1;

        for (String keyword : res.keySet()) {
            sheet.addCell(new jxl.write.Label(0, row, keyword));
            if (res.get(keyword) != null) {
                HashSet<MapTerm> set = res.get(keyword);
                for (MapTerm mt : set) {
                    sheet.addCell(new jxl.write.Label(1, row, mt.getOntoName()));
                    sheet.addCell(new jxl.write.Label(2, row, mt.getLabel()));
                    sheet.addCell(new jxl.write.Label(3, row, mt.getURI()));
                    row++;
                }
            } else {
                row++;
            }
        }
        myexcel.write();
        myexcel.close();

        System.out.println("finished save " + name +" to Excel....");

    }

}
