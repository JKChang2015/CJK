/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cjk;

import java.io.File;
import java.util.LinkedList;

/**
 * Traverse and load file in a Folder
 *
 * @author jkchang
 */
public class traverseFolder {

    public void traverseFolder(String path) {
        int fileNum = 0, folderNum = 0;
        File file = new File(path);
        if (file.exists()) {
            LinkedList<File> list = new LinkedList<File>();
            File[] files = file.listFiles();
            for (File file2 : files) {
                if (file2.isDirectory()) {
                    System.out.println("Folder" + file2.getAbsolutePath());
                    list.add(file2);
                    fileNum++;
                } else {
                    System.out.println("File" + file2.getAbsolutePath());
                    folderNum++;
                }
            }
            File temp_file;
            
            while (!list.isEmpty()) {
                temp_file = list.removeFirst();
                files = temp_file.listFiles();
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("Folder" + file2.getAbsolutePath());
                        list.add(file2);
                        fileNum++;
                    } else {
                        System.out.println("File" + file2.getAbsolutePath());
                        folderNum++;
                    }
                }
            }

        } else {
            System.out.println("The file is not exsiting");
        }

        System.out.println("Totoal number of folder: " + folderNum);
        System.out.println("Total number of file: " + fileNum);

    }

}
