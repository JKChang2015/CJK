package com.cjk.java100;

/**
 * @author jkchang
 * @date 24-Jul-2017
 * @Description: print pyramid
 */
public class j001 {

    public static void main(String[] args) {
        int line = 4;

        for (int i = 1; i <= line; i++) {

            for (int j = 0; j < line - i; j++) {
                System.out.print("   ");
            }

            for (int j = 0; j < i * 2 - 1; j++) {
                System.out.print(" * ");
            }

            System.out.println(" ");
        }
    }
}
