package eNM.ExcelToOWL;

import eNM.Matching.MapTerm;
import java.io.File;
import jxl.Workbook;
import java.lang.Exception;
import jxl.write.WritableWorkbook;
import jxl.write.WritableSheet;
import jxl.write.Label;

import eNM.Matching.KeywordFile;
import org.semanticweb.owlapi.*;
import eNM.Matching.OntoLabel;

import java.util.ArrayList;
import java.util.HashSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

/**
 * After import new terms from excel, overwrite the excel to record the progress
 *
 * @author jkchang
 */
public class SaveExcel {

    public void save(ArrayList<OntoEntity> ontoEntities, String path) throws WriteException, IOException {

        File file = new File(path);
        WritableWorkbook myexcel = Workbook.createWorkbook(file);
        WritableSheet sheet = myexcel.createSheet("new sheet", 0);

        sheet.addCell(new jxl.write.Label(0, 0, "New term"));
        sheet.addCell(new jxl.write.Label(1, 0, "URI"));
        sheet.addCell(new jxl.write.Label(2, 0, "SuperClass"));
        sheet.addCell(new jxl.write.Label(3, 0, "Super URI"));
        sheet.addCell(new jxl.write.Label(4, 0, "Definition"));

        int row = 1;

        for (OntoEntity oe : ontoEntities) {
            sheet.addCell(new jxl.write.Label(0, row, oe.getLabel()));
            sheet.addCell(new jxl.write.Label(1, row, oe.getUri()));
            sheet.addCell(new jxl.write.Label(2, row, oe.getSupClassName()));
            sheet.addCell(new jxl.write.Label(3, row, oe.getSupURI()));
            sheet.addCell(new jxl.write.Label(4, row, oe.getDef()));
            row++;
        }

        myexcel.write();
        myexcel.close();

        System.out.println("finished save " + path + " to Excel....");

    }

}
