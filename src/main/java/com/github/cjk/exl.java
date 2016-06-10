package com.github.cjk;

import java.io.File;
import jxl.Workbook;
import java.lang.Exception;
import jxl.write.WritableWorkbook;
import jxl.write.WritableSheet;
import jxl.write.Label;

public class exl {

    public static void main(String[] args) throws Exception {
        
        String a = "information retrieval";
        String b = "Information";
        String c = "information";
        String d = "informat";
        String e = "informationretrievl";
        
        System.out.println(a.contains(b));
        System.out.println(a.toLowerCase().contains(b.toLowerCase()));
        System.out.println(a.contains(c));
        System.out.println(a.contains(d));
        System.out.println(a.contains(e));

        System.out.println("finish");
        
        
        File file = new File("result.xls");
        WritableWorkbook myexcel = Workbook.createWorkbook(file);
        WritableSheet sheet1 = myexcel.createSheet("new sheet", 0);
        Label l1 = new Label(0, 0, "first");
        Label l2 = new Label(0, 1, "second");
        
        sheet1.addCell(l1);
        sheet1.addCell(l2);
        myexcel.write();
        myexcel.close();
        

    }

}
