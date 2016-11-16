package util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

/**
 *
 * @author JKChang
 * @date 16-Nov-2016
 * @Description: browse / return particular type of files
 *
 */
public class FileBrowser {

    private ArrayList<String> nameList = new ArrayList<String>();

    public ArrayList<String> listFilename(String folderPath, String fileType) {

        File folder = new File(folderPath);
        FilenameFilter proFilter = new FilenameFilter() {
            @Override
            public boolean accept(File folder, String name) {
                return name.toLowerCase().endsWith(fileType);
            }
        };

        File[] files = folder.listFiles(proFilter);
        for (File file : files) {
            try {
                nameList.add(file.getName());
                System.out.println(file.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return nameList;
    }

    public int getFileCount() {
        return nameList.size();
    }

}
