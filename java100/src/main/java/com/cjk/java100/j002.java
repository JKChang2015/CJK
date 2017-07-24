package com.cjk.java100;

/**
 *
 * @author jkchang
 * @date 24-Jul-2017
 * @Description:
 *
 */
public class j002 {

    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j <= i; j++) {

                System.out.print(j + " x " + i + " =  " + i * j + "\t");

            }
            System.out.println("");
        }
    }

}
