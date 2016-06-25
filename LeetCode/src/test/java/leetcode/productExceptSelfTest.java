package leetcode;

import org.junit.Test;
import neww.productExceptSelf;

/**
 *
 * @author JKChang
 */
public class productExceptSelfTest {

    @Test
    public void testSomeMethod() {
        productExceptSelf ps = new productExceptSelf();
        int[] test = {1, 3, 4, 2, 5};
        int[] result = new int[test.length];
        result = ps.productExceptSelf(test);
        for (int i = 0; i < result.length; i++) {
            System.out.append( result[i]+ "  ");
        }
    }

}
