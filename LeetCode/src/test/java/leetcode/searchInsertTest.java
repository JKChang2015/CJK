package leetcode;

import org.junit.Test;
import static org.junit.Assert.*;

/*
 * @date 2016-6-26
 * @author JKChang
 */
public class searchInsertTest {

    public searchInsertTest() {
    }

    @Test
    public void testSomeMethod() {
        int [] arr = { 1, 3, 4, 5, 7};
        int t1 = 2;
        int t2 = 6;
        searchInsert si = new searchInsert();
        int res1 = si.searchInsert(arr, t1);
        int res2 = si.searchInsert(arr, t2);
        
        System.out.println("2 insert to " + res1);
        System.out.println("6 insert to " + res2);
    }

}