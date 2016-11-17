package eNanoMapper.Util;

import java.io.IOException;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import jxl.write.WriteException;

import eNanoMapper.Curation.OntoEntity;

/**
 *
 * @author JKChang
 * @date 16-Nov-2016
 * @Description: Read CSV file to ArrayList
 *
 */
public class CsvFileReader {
    
    ArrayList<OntoEntity> ontoEntities = new ArrayList<OntoEntity>();
    

    public static void readCSVFile(String head, String owlFile, String CSVFile, String termURI, int StartID) {
        String outputOWL = owlFile.substring(0, owlFile.lastIndexOf('.')) + "_res.owl";
        String outputCSV = CSVFile.substring(0, CSVFile.lastIndexOf('.')) + "_res.xls";



    }

}
