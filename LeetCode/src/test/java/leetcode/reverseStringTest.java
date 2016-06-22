/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode;

import org.junit.Test;
import leetcode.reverseString;
import static org.junit.Assert.*;

/**
 *
 * @author jkchang
 */
public class reverseStringTest {

    @Test
    public void testReverseString() {
        String str = "Hello world";
        reverseString rs = new reverseString();
        String result = rs.reverseString(str);
        System.out.println("\n\n" + result + "\n\n");
    }

    @Test
    public void testEmpty() {
        String str = " ";
        reverseString rs = new reverseString();
        String result = rs.reverseString(str);
        System.out.println("\n\n" + result + "\n\n");
    }

}
