package leetcode;

import org.junit.Test;

/**
 *
 * @author JKChang
 */
public class ProductExceptSelfTest {

    @Test
    public void testSomeMethod() {
        ProductExceptSelf ps = new ProductExceptSelf();
        int[] test = {1, 3, 4, 2, 5};
        int[] result = new int[test.length];
        result = ps.productExceptSelf(test);
        for (int i = 0; i < result.length; i++) {
            System.out.append( result[i]+ "  ");
        }
    }

}
