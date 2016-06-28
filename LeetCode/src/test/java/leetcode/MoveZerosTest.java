/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode;

import org.junit.Test;
import static org.junit.Assert.*;
import leetcode.MoveZeros;
/**
 *
 * @author jkchang
 */
public class MoveZerosTest {
    
    @Test
    public void testSomeMethod() {
        int[] nums = { 0, 1, 0, 3, 0, 12, 4, 0};
        MoveZeros mz = new MoveZeros();
        mz.moveZeroes(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.append(nums[i] + "  ");
        }
                System.out.println("");
    }
    
}
