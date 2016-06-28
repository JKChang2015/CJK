package leetcode;

import org.junit.Test;
import static org.junit.Assert.*;
import leetcode.ContainsDuplicate;

/*
 * @author jkchang
 * @date 22-Jun-2016
 */
public class ContainsDuplicateTest {

    @Test
    public void testSomeMethod() {
        int[] nums = {1, 2, 3, 4};
        ContainsDuplicate cd = new ContainsDuplicate();
        boolean res = cd.containsDuplicate(nums);
        System.out.println("result is " + res);
    }
    
    @Test
    public void testFalse(){
        int[] nums = {2, 2, 3, 4};
        ContainsDuplicate cd = new ContainsDuplicate();
        boolean res = cd.containsDuplicate(nums);
        System.out.println("result is " +res);
    }

}
