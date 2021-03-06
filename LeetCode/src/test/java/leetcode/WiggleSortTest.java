/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jkchang
 */
public class WiggleSortTest {
    
    public WiggleSortTest() {
    }
    
    @Test
    public void WiggleSort() {
        int[] nums = {1, 3, 4, 2, 6, 5, 8};
        WiggleSort ws = new WiggleSort();
        ws.wiggleSort(nums);
        printfr(nums);
    }
    
    @Test
    public void WiggleSort2() {
        int[] nums = {1, 3, 4, 2, 6, 5, 8, 10, 21, 4};
        WiggleSort ws = new WiggleSort();
        ws.wiggleSort2(nums);
        printfr(nums);        
    }
    
    public void printfr(int[] nums) {
        System.out.append(Integer.toString(nums[0]));
        for (int i = 1; i < nums.length; i++) {
            if (nums[i-1] > nums[i]) {
                System.out.append(" > ");
            } 
            
            else {
                System.out.append(" < ");
            }
            System.out.append(Integer.toString(nums[i]));
        }
        System.out.println("");
    }
    
}
