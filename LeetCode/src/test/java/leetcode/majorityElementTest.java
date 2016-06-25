package leetcode;

import org.junit.Test;
import static org.junit.Assert.*;
import leetcode.majorityElement;

/*
 * @author jkchang
 * @date 22-Jun-2016
 */
public class majorityElementTest {

    public majorityElementTest() {
    }

    @Test
    public void testSomeMethod() {
        int[] arr = {1, 2, 3, 3, 3, 2, 2, 2, 2, 3};
        majorityElement me = new majorityElement();
        int res = me.majorityElement(arr);
        System.out.println("the majority nume in the arr is " + res);
    }

    @Test
    public void testSomeMethod2() {
        int[] arr = {1, 2, 4, 3, 3, 3, 2, 2, 2, 2, 3};
        majorityElement me = new majorityElement();
        int res = me.majorityElement2(arr);
        System.out.println("the majority nume in the arr is " + res);
    }

}
