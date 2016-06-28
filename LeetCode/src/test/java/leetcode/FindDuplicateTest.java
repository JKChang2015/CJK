package leetcode;

import org.junit.Test;
import static org.junit.Assert.*;
import leetcode.FindDuplicate;

/*
 * @author jkchang
 * @date 23-Jun-2016
 */
public class FindDuplicateTest {


    @Test
    public void testSomeMethod() {
        int[] arr = {1, 2, 3, 4, 5, 6, 4, 7, 8, 9};
        FindDuplicate fd = new FindDuplicate();
        int res = fd.findDuplicate5(arr);
        System.out.println("Duplicate number is  " + res);
    }

}
