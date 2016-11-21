package eNanoMapper.Util;


import java.io.File;
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
    private final String ENTITY_NAME = "name";
    private final String ENTITY_LABEL = "label";
    private final String ENTITY_URI = "URI";
    private final String ENTITY_SUPNAME = "supname";
    private final String ENTITY_SUPURI = "supuri";
    private final String ENTITY_DEF = "def";


    public ArrayList<OntoEntity> read(String[] header, File CSVFile) throws IOException {

        FileReader fileReader = null;
        CSVParser csvFilePaser = null;
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(header);

        try {
            fileReader = new FileReader(CSVFile);
            csvFilePaser = new CSVParser(fileReader, csvFileFormat);
            List csvRecords = csvFilePaser.getRecords();

            for (int i = 1; i < csvRecords.size(); i++) {
                CSVRecord record = (CSVRecord) csvRecords.get(i);
                OntoEntity entity = new OntoEntity(record.get(ENTITY_NAME),
                        record.get(ENTITY_LABEL), record.get(ENTITY_URI),
                        record.get(ENTITY_SUPNAME), record.get(ENTITY_SUPURI),
                        record.get(ENTITY_DEF));
                ontoEntities.add(entity);
            }
            
            for(OntoEntity enti : ontoEntities){
                System.out.println(enti.getName());
                System.out.println(enti.getLabel());
                System.out.println(enti.getUri());
                System.out.println(enti.getSupClassName());
                System.out.println(enti.getSupURI());
                System.out.println(enti.getDef());
                System.out.println("============================== \n");
            }

        } catch (Exception e) {
            System.out.println("Error in CsvFileReader");
            e.printStackTrace();

        } finally {
            try {
                fileReader.close();
                csvFilePaser.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader/csvFileParser !!!");
                e.printStackTrace();
            }
        }

        return ontoEntities;
    }

}
