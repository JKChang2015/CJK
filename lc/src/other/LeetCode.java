package other;

import array._001twoSum;
import java.util.*;

public class LeetCode {

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            if (i % 1 != 0) {
                continue;
            }
            if (i % 2 != 1) {
                continue;
            }
            if (i % 3 != 0) {
                continue;
            }
            if (i % 4 != 1) {
                continue;
            }
            if (i % 5 != 4) {
                continue;
            }
            if (i % 6 != 3) {
                continue;
            }
            if (i % 7 != 0) {
                continue;
            }
            if (i % 8 != 1) {
                continue;
            }
            if (i % 9 != 0) {
                continue;
            }
            System.out.println("the number is " + i);

        }

    }
}
