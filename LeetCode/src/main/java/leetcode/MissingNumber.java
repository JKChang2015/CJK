/* ----------------------------------------------------------------------------
268. Missing Number 
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find 
the one that is missing from the array.

For example,
Given nums = [0, 1, 3] return 2.

Note:
Your algorithm should run in linear runtime complexity. Could you implement it 
using only constant extra space complexity?
   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 23-Jun-2016
 */
package leetcode;

public class MissingNumber {

    public int missingNumber(int[] nums) {
        // res = non-missing sum - missing sum
        int len = nums.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
        }
        int res = (len + 1) * len / 2 - sum;
        return res;
    }

    public int missingNumber2(int[] nums) {
        //binary searching

        return 0;
    }

    public int missingNumber3(int[] nums) {
        // XOR
        int x = 0;
        for (int i = 0; i < nums.length; i++) {
            x ^= i;
        }
        for (int i = 0; i < nums.length; i++) {
            x ^= nums[i];
        }
        return x;
    }

}
