package leetcode;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JKChang
 */
public class twoSum2Test {
    
    public twoSum2Test() {
    }

    @Test
    public void testSomeMethod() {
        
        int nums[] = {1,2,5,8,12,13};
        int target = 10;
        int[] result = new int[2];
        twoSum2 ts = new twoSum2();
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
