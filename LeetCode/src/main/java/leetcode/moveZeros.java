/* ----------------------------------------------------------------------------
283. Move Zeroes 
Given an array nums, write a function to move all 0's to the end of it while 
maintaining the relative order of the non-zero elements.
For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums 
should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 21-Jun-2016
 */
package leetcode;

public class moveZeros {

    public void moveZeroes(int[] nums) {
        // change the index, not change the value
        int pos = 0; 
        for (int curt = 0; curt < nums.length; curt++) {
            if (nums[curt] !=0) {
                nums[pos] = nums[curt];
                pos++;
            }
        }
        for (; pos < nums.length; pos++) {
            nums[pos] = 0;
        }
    }
}
