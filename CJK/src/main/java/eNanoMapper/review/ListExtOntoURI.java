package eNanoMapper.review;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author JKChang
 * @date 25-Jan-2017
 * @Description: List the URI of the external ontologies
 *
 */
public class ListExtOntoURI {

    public static void main(String[] args) {

        File file1 = new File("/../eNM/all class ID.txt");
        File file2 = new File("/../eNM/URI.txt"); ///Users/jkchang/Github/CJK/CJK/eNM

        try {
            Scanner s1 = new Scanner(file1);
            Scanner s2 = new Scanner(file2);

            ArrayList<String> ids = new ArrayList<String>();
            ArrayList<String> all = new ArrayList<String>();

            while (s1.hasNext()) {
                all.add(s1.next());
            }

            while (s2.hasNext()) {
                ids.add(s2.next());
            }
            
            System.out.println(all.get(0));
            System.out.println(all.get(1));
            
            System.out.println("");

            for (String a : all) {
                boolean contain = false;
                for (String id : ids) {
                    if (a.contains(id)) {
                        contain = true;
                        break;
                    }
                }
                if (!contain) {

                    System.out.println(a);
                }
            }

            System.out.println("");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");

        }

        System.out.println("");

    }

}
