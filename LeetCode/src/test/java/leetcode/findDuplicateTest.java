package leetcode;

import org.junit.Test;
import static org.junit.Assert.*;
import leetcode.findDuplicate;

/*
 * @author jkchang
 * @date 23-Jun-2016
 */
public class findDuplicateTest {


    @Test
    public void testSomeMethod() {
        int[] arr = {1, 2, 3, 4, 5, 6, 4, 7, 8, 9};
        findDuplicate fd = new findDuplicate();
        int res = fd.findDuplicate5(arr);
        System.out.println("Duplicate number is  " + res);
    }

}
