package leetcode;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
/*
 * @date 2016-6-26
 * @author JKChang
 */
public class threeSumTest {

    public threeSumTest() {
    }

    @Test
    public void testSomeMethod() {
        
        int [] S = {-1, 0, 1, 2, -1, -4};
        threeSum ts = new threeSum();
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res = ts.threeSum(S);
        System.out.println("");
        
        
    }

}