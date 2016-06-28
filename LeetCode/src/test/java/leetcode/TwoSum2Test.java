package leetcode;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JKChang
 */
public class TwoSum2Test {
    
    public TwoSum2Test() {
    }

    @Test
    public void testSomeMethod() {
        
        int nums[] = {1,2,5,8,12,13};
        int target = 10;
        int[] result = new int[2];
        TwoSum2 ts = new TwoSum2();
        result = ts.twoSum(nums, target);

        if (result == null) {
            System.out.println("no numbers matching");
        } else {
            for (int i = 0; i < result.length; i++) {
                System.out.println(result[i]);
            }
        }
    }
    
}
