package com.github.cjk;

import com.github.eNM.Label;
import com.github.eNM.KeywordFile;

import java.util.Set;
import java.util.HashSet;
import java.io.File;

/**
 *
 * @author jkchang
 */
public class CJK {

    public static void main(String[] args) throws Exception {

        String kPath = "src\\main\\resources\\keyword.txt";
        String ontoPath = "src\\main\\resources\\enm.owl";
        String ontoTest = "src\\main\\resources\\fruit.owl";

        File ontoFile = new File(ontoPath);
        File keywordFile = new File(kPath);

        Set<String> keywords = new HashSet<String>();
        Set<String> ontoLabels = new HashSet<String>();

        KeywordFile kf = new KeywordFile(keywordFile);
        keywords = kf.getkeywords();
        Label lb = new Label(ontoFile);
        ontoLabels = lb.getlabel();

        System.out.println("finished");

    }
}
