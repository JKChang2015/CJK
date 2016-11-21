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

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import eNanoMapper.Curation.OntoEntity;

/**
 *
 * @author JKChang
 * @date 16-Nov-2016
 * @Description: Read CSV file to ArrayList
 *
 */
public class CsvFileReader {

    private final ArrayList<OntoEntity> ontoEntities = new ArrayList<OntoEntity>();
    
    
    // ontology entity attribute
    private final String ONTOENTITY_NAME = "name";
    private final String ONTOENTITY_LABEL = "label";
    private final String ONTOENTITY_URI = "URI";
    private final String ONTOENTITY_SUPNAME = "supname";
    private final String ONTOENTITY_SUPURI = "supuri";
    private final String ONTOENTITY_DEF = "def";
    
    

    public static void readCSVFile(String header, String owlFile, String CSVFile, String termURI, int StartID) {
        String outputOWL = owlFile.substring(0, owlFile.lastIndexOf('.')) + "_res.owl";
        String outputCSV = CSVFile.substring(0, CSVFile.lastIndexOf('.')) + "_res.xls";

    }

    public ArrayList<OntoEntity> read(String header, File CSVFile) throws IOException {
       
        
        
        FileReader fileReader = null;
        CSVParser csvFilePaser = null;
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(header);
        
        try {
            fileReader = new FileReader(CSVFile);
            csvFilePaser = new CSVParser(fileReader, csvFileFormat);
            List csvRecords = csvFilePaser.getRecords();
            
            for (int i = 1; i < csvRecords.size(); i++) {
                CSVRecord record = (CSVRecord) csvRecords.get(i);
                OntoEntity ontoEnti = new OntoEntity();
                
            }
            
            
        } catch (Exception e) {
            
            
        }
        
        

        return ontoEntities;
    }

}
