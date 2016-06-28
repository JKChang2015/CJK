package leetcode;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
/*
 * @date 2016-6-26
 * @author JKChang
 */
public class ThreeSumTest {

    public ThreeSumTest() {
    }

    @Test
    public void testSomeMethod() {
        
        int [] S = {-1, 0, 1, 2, -1, -4};
        ThreeSum ts = new ThreeSum();
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res = ts.threeSum(S);
        System.out.println("");
        
        
    }

}