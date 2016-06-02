/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cjk;

public class hanoi {

    private static int step = 0;

    public void hanoi(char from, char temp, char to, int n) {

        if (n == 1) {
            step++;
            System.out.println("Step " + step + ": " + from + "→" + to);
        } else {
            hanoi(from, to, temp, n - 1);
            step++;
            System.out.println("Step " + step + ": " + from + "→" + to);
            hanoi(temp, from, to, n - 1);
        }
    }
}
