package com.github.cjk;

import java.io.File;
import jxl.Workbook;
import java.lang.Exception;
import jxl.write.WritableWorkbook;
import jxl.write.WritableSheet;
import jxl.write.Label;


import com.github.eNM.KeywordFile;
import org.semanticweb.owlapi.*;
import com.github.eNM.OntoLabel;

import java.util.Set;
import java.util.HashSet;
import java.io.File;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class exl {

    public static void main(String[] args) throws Exception {
        
       String kPath = "src\\main\\resources\\keyword.txt";
        String ontoPath = "src\\main\\resources\\enm.owl";
        Set<String> keywords = new HashSet<String>();
        Set<String> ontoLabels = new HashSet<String>();

        File ontoFile = new File(ontoPath);
        File keywordFile = new File(kPath);

        KeywordFile kf = new KeywordFile(keywordFile);
        keywords = kf.getkeywords();
        OntoLabel lb = new OntoLabel(ontoFile);
        ontoLabels = lb.getlabel();

        File file = new File("src\\main\\resources\\result.xls");
        WritableWorkbook myexcel = Workbook.createWorkbook(file);
        WritableSheet sheet = myexcel.createSheet("new sheet", 0);
        jxl.write.Label title1 = new jxl.write.Label(0, 0, "New term");
        jxl.write.Label title2 = new jxl.write.Label(1, 0, "Contain?");
        jxl.write.Label title3 = new jxl.write.Label(2, 0, "Label");

        sheet.addCell(title1);
        sheet.addCell(title2);
        sheet.addCell(title3);

        int row = 1;
        int count = 0;

        for (String keyword : keywords) {
            System.out.println(++count + ".  " + keyword);
            jxl.write.Label addK = new jxl.write.Label(0, row, keyword);
            jxl.write.Label addFlag = new jxl.write.Label(1, row, "");
            jxl.write.Label addLab = new jxl.write.Label(2, row, "");

            for (String label : ontoLabels) {
                if (label.toLowerCase().equals(keyword.toLowerCase())) {
                    addFlag = new jxl.write.Label(1, row, "Yes");
                    addLab = new jxl.write.Label(2, row, label);
                    continue;
                }
                if (label.toLowerCase().contains(keyword.toLowerCase())) {
                    addFlag = new jxl.write.Label(1, row, "Similar");
                    addLab = new jxl.write.Label(2, row, label);
                    continue;
                }
                
            }
            sheet.addCell(addK);
            sheet.addCell(addFlag);
            sheet.addCell(addLab);
            row++;
        }

        myexcel.write();
        myexcel.close();

        System.out.println("finished");
        

    }

}
