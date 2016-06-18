/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package array;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jkchang
 */
public class _001twoSumTest {
    
   
    @Test
    public void testTwoSum() {
       System.out.println("twoSum");
        int[] nums = {1, 2, 10, 3, 9};
        int target = 13;
        _001twoSum instance = new _001twoSum();
        int[] expResult = {3, 4};
        int[] result = instance.twoSum(nums, target);

        assertArrayEquals(expResult, result);

    }

    
    
}
