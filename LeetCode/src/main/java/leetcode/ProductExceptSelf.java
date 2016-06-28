/* -----------------------------------------------------------------------------
238. Product of Array Except Self 
Given an array of n integers where n > 1, nums, return an array output such that
output[i] is equal to the product of all the elements of nums except nums[i].

Solve it without division and in O(n).
For example, given [1,2,3,4], return [24,12,8,6].

Follow up:
Could you solve it with constant space complexity? (Note: The output array does 
not count as extra space for the purpose of space complexity analysis.)
--------------------------------------------------------------------------------
@author JKChang
@data 2016-6-21
 */
package leetcode;

public class ProductExceptSelf {

    // Product of a element = Product of left elements * Product of right elements
    public int[] productExceptSelf(int[] nums) { //时间 O(N) 空间 O(1)
        if (nums.length <= 1) {
            return nums;
        }
        int[] results = new int[nums.length];
        int leftProduct = 1;
        int rightProduct = 1;

        // use product value from right to left to initial result array
        for (int i = (nums.length - 1); i >= 0; i--) {
            results[i] = rightProduct;
            rightProduct = rightProduct * nums[i];
        }

        // left [i] = left * left[i-1];
        for (int i = 0; i < nums.length; i++) {
            results[i] *= leftProduct;
            leftProduct = nums[i] * leftProduct;
        }
        return results;
    }

}
