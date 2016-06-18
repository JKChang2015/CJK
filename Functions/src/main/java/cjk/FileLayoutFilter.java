package cjk;

import java.io.File;
import java.io.FilenameFilter;

/**
 * filter the particular file
 *
 * @author jkchang
 */
public class FileLayoutFilter implements FilenameFilter {

    private String ext;

    public FileLayoutFilter(String condition) {
        int i = condition.lastIndexOf(".");
        if (i >= 0) {
            ext = condition.toLowerCase().toString();
        } else {
            ext = condition.toLowerCase();
        }
    }

    public boolean accept(File dir, String name) {
        if (name.toLowerCase().endsWith(ext)) {
            return true;
        }
        return false;
    }
}
