package cjk;

import java.io.File;
import jxl.Workbook;
import java.lang.Exception;
import jxl.write.WritableWorkbook;
import jxl.write.WritableSheet;
import jxl.write.Label;

import eNanoMapper.Mapping.KeywordLoader;
import org.semanticweb.owlapi.*;
import eNM.Matching.OntoLabel;

import java.util.Set;
import java.util.HashSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class exl {

    public static void main(String[] args) throws Exception {

        String kPath = "src\\main\\resources\\keyword.txt";
        String ontoPath = "src\\main\\resources\\enm.owl";
        Set<String> keywords = new HashSet<String>();
        Set<String> ontoLabels = new HashSet<String>();

        File ontoFile = new File(ontoPath);
        File keywordFile = new File(kPath);

        KeywordLoader kf = new KeywordLoader(keywordFile);
        keywords = kf.getkeywords();
        OntoLabel lb = new OntoLabel(ontoFile); //===================
        ontoLabels = lb.getlabel();

        File file = new File("src\\main\\resources\\result.xls");
        WritableWorkbook myexcel = Workbook.createWorkbook(file);
        WritableSheet sheet = myexcel.createSheet("new sheet", 0);

        sheet.addCell(new jxl.write.Label(0, 0, "New term"));
        sheet.addCell(new jxl.write.Label(1, 0, "Contain?"));
        sheet.addCell(new jxl.write.Label(2, 0, "Label"));

        int row = 1;
        int count = 0;

        for (String keyword : keywords) {
            System.out.println(++count + ".  " + keyword);
            //jxl.write.Label addK = new jxl.write.Label(0, row, keyword);
            //jxl.write.Label addFlag = new jxl.write.Label(1, row, "");
            //jxl.write.Label addLab = new jxl.write.Label(2, row, "");

            for (String label : ontoLabels) {
                if (label.toLowerCase().equals(keyword.toLowerCase())) {
                    sheet.addCell(new jxl.write.Label(1, row, "Yes"));
                    sheet.addCell(new jxl.write.Label(2, row, label));
                    //addFlag = new jxl.write.Label(1, row, "Yes");
                    //addLab = new jxl.write.Label(2, row, label);
                    continue;
                }
                if (label.toLowerCase().contains(keyword.toLowerCase())) {
                    sheet.addCell(new jxl.write.Label(1, row, "Similar"));
                    sheet.addCell(new jxl.write.Label(2, row, label));
//                    addFlag = new jxl.write.Label(1, row, "Similar");
//                    addLab = new jxl.write.Label(2, row, label);
                    continue;
                }

            }
            sheet.addCell(new jxl.write.Label(0, row, keyword));
            //sheet.addCell(addFlag);
            //sheet.addCell(addLab);
            row++;
        }

        myexcel.write();
        myexcel.close();

        System.out.println("finished");

    }

}
