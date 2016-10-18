package eNM.Matching;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

/**
 *
 * @author jkchang
 */
public class KeywordFile {

    Set<String> keywords = new HashSet<String>();

    public KeywordFile(File file) throws IOException {
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
