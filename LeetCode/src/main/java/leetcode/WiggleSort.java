/* ----------------------------------------------------------------------------
    280. Wiggle Sort
    Given an unsorted array nums, reorder it in-place such that 
    nums[0] <= nums[1] >= nums[2] <= nums[3]....
    For example, given nums = [3, 5, 2, 1, 6, 4], 
    one possible answer is [1, 6, 2, 5, 3, 4].
   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 20-Jun-2016
 */
package leetcode;

import java.util.Arrays;

public class WiggleSort {
    
    public void wiggleSort(int[] nums) {  //O(NlogN) O(1)
        //1. Sorted 2. exchange every two elements
        Arrays.sort(nums);
        for (int i = 1; i < nums.length - 1; i += 2) {
            int temp = nums[i];
            nums[i] = nums[i + 1];
            nums[i + 1] = temp;
        }
    }
    
    public void wiggleSort2(int[] nums) {  //O(N) O(1)
        // A1<A2>A3<A4>A5
        for (int i = 0; i < nums.length - 1; i++) {
            if (((i + 1) % 2 == 1 && nums[i] < nums[i + 1]) //odd
                    || ((i + 1) % 2 == 0 && nums[i] > nums[i + 1])) { //even
                int temp = nums[i];
                nums[i] = nums[i + 1];
                nums[i + 1] = temp;
            }
        }
    }
    
}
