package eNM.ExcelToOWL;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 *
 * @author jkchang
 */
public class ReadExcel {

    private String inputFile;
    private ArrayList<OntoEntity> ontoEntities = new ArrayList<OntoEntity>();

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public ArrayList<OntoEntity> read() throws IOException {
        // a column after anther
        File inputWorkbook = new File(inputFile);
        Workbook w;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            Sheet sheet = w.getSheet(0);

            for (int i = 1; i < sheet.getRows(); i++) {
                OntoEntity newEntity = new OntoEntity();
                newEntity.setName(sheet.getCell(0,i).getContents().trim());
                newEntity.setLabel(sheet.getCell(0,i).getContents().trim());
                newEntity.setSupClassName(sheet.getCell(1,i).getContents().trim());
                newEntity.setSupURI(sheet.getCell(2,i).getContents().trim());
                newEntity.setDef(sheet.getCell(3,i).getContents().trim());
                
                ontoEntities.add(newEntity);
                                   
            }

        } catch (BiffException e) {
            e.printStackTrace();
        }
        return ontoEntities;
    }


//    public static void main(String[] args) throws IOException {
//        ReadExcel test = new ReadExcel();
//        test.setInputFile("src\\main\\resources\\new terms.xls");
//        test.read();
//
//
//    }

}
