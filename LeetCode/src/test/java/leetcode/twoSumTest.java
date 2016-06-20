package leetcode;

import org.junit.Test;
import static org.junit.Assert.*;
import leetcode.twoSum;

/**
 *
 * @author jkchang
 */
public class twoSumTest {

    public twoSumTest() {
    }

    @Test
    public void testNormal() {
        int nums[] = {1, 4, 5, 2, 8, 10};
        int target = 10;
        int[] result = new int[2];
        twoSum ts = new twoSum();
        result = ts.twoSum(nums, target);

        if (result == null) {
            System.out.println("no numbers matching");
        } else {
            for (int i = 0; i < result.length; i++) {
                System.out.println(result[i]);
            }
        }
    }

    @Test
    public void testNull() {
        int nums[] = null;
        int target = 20;
        int[] result = new int[2];
        twoSum ts = new twoSum();
        result = ts.twoSum(nums, target);

        if (result == null) {
            System.out.println("no numbers matching");
        } else {
            for (int i = 0; i < result.length; i++) {
                System.out.println(result[i]);
            }
        }
    }
    
    @Test
    public void testLessthanTwo() {
        int nums[] = {1};
        int target = 20;
        int[] result = new int[2];
        twoSum ts = new twoSum();
        result = ts.twoSum(nums, target);

        if (result == null) {
            System.out.println("no numbers matching");
        } else {
            for (int i = 0; i < result.length; i++) {
                System.out.println(result[i]);
            }
        }
    }
    
    @Test
    public void testUseItself() {
        int nums[] =  {1, 4, 5, 2, 8, 10};
        int target = 20;
        int[] result = new int[2];
        twoSum ts = new twoSum();
        result = ts.twoSum2(nums, target);

        if (result == null) {
            System.out.println("no numbers matching");
        } else {
            for (int i = 0; i < result.length; i++) {
                System.out.println(result[i]);
            }
        }
    }

}
