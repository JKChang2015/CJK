package eNanoMapper.Mapping;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author JKChang
 * @date 21-Nov-2016
 * @Description: Load keywords txt file to Sets
 *
 */
public class KeywordLoader {

    Set<String> keywords = new HashSet<String>();

    public KeywordLoader(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine().trim();
        int count = 0;
        while (line != null) {
            count++;
            keywords.add(line.trim());
            System.out.println(count + ".  " + line);
            line = reader.readLine();
        }
    }

    public Set<String> getkeywords() {
        return keywords;
    }
}
