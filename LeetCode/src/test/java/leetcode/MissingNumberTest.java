package leetcode;

import org.junit.Test;
import static org.junit.Assert.*;

/*
 * @date 2016-6-27
 * @author JKChang
 */
public class MissingNumberTest {

    public MissingNumberTest() {
    }

    @Test
    public void testSomeMethod() {
        int[] arr = {0, 1, 2, 3, 5};
        MissingNumber nm = new MissingNumber();
        int res = nm.missingNumber(arr);
        System.out.println("resul is " + res);

    }

}
