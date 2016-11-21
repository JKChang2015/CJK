package eNanoMapper.Util;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import eNanoMapper.Curation.OntoEntity;

/**
 *
 * @author JKChang
 * @date 21-Nov-2016
 * @Description: Write term Arraylist to the csv file
 *
 */
public class CsvFileWriter {

    // Delimiter of the csv file;
    //Header of the csv file;
    //String[] fileHeader = {"name", "label", "URI", "supname", "supuri", "def"};
    public void write(String fileName, String[] FILE_HEADER, ArrayList<OntoEntity> entities) {
        FileWriter fileWriter = null;
        CSVPrinter csvFilePrinter = null;
        final String NEW_LINE_SEPARATOR = "\n";

        //Create the CSVFormat object with "\n" as a record delimiter
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

        try {

            //initialize FileWriter object
            fileWriter = new FileWriter(fileName);

            //initialize CSVPrinter object 
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

            //Create CSV file header
            csvFilePrinter.printRecord(FILE_HEADER);
            
            for (OntoEntity entity: entities) {
                
            }

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter ... ");
            e.printStackTrace();

        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
                csvFilePrinter.close();
            } catch (Exception e) {
                System.out.println("Error while flushing/ closing fileWriter/ csvPrinter");
                e.printStackTrace();
            }

        }
    }

}
