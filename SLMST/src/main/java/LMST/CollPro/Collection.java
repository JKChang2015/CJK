/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LMST.CollPro;

import java.util.ArrayList;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import org.terrier.indexing.*;

/**
 *
 * @author JKChang
 */
public class Collection {

    public void index() throws IOException {
        File f = new File("src/main/resources/sample.txt");

        InputStream file = new FileInputStream(f);
        TRECWebCollection coll = new TRECWebCollection(file);
        

    }

    public static void main(String[] args) throws IOException {

    }

}
