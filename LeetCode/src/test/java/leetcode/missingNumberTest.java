package leetcode;

import org.junit.Test;
import static org.junit.Assert.*;

/*
 * @date 2016-6-27
 * @author JKChang
 */
public class missingNumberTest {

    public missingNumberTest() {
    }

    @Test
    public void testSomeMethod() {
        int[] arr = {0, 1, 2, 3, 5};
        missingNumber nm = new missingNumber();
        int res = nm.missingNumber(arr);
        System.out.println("resul is " + res);

    }

}
