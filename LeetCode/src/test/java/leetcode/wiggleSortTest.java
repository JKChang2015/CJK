/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode;

import org.junit.Test;
import static org.junit.Assert.*;
import leetcode.wiggleSort;

/**
 *
 * @author jkchang
 */
public class wiggleSortTest {

    public wiggleSortTest() {
    }

    @Test
    public void testSomeMethod() {
        int[] nums = {1, 3, 4, 2, 6, 5, 8};
        wiggleSort ws = new wiggleSort();
        ws.wiggleSort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.append(nums[i] + " ");
        }
        System.out.println("");

    }

}
