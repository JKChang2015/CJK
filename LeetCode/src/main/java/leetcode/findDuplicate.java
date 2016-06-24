/* ----------------------------------------------------------------------------
287. Find the Duplicate Number

Given an array nums containing n + 1 integers where each integer is between 1 
and n (inclusive), prove that at least one duplicate number must exist. Assume 
that there is only one duplicate number, find the duplicate one.

Note:
1. You must not modify the array (assume the array is read only).
2. You must use only constant, O(1) extra space.
3. Your runtime complexity should be less than O(n2).
4. There is only one duplicate number in the array, but it could be repeated 
more than once.
   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 23-Jun-2016
 */
package leetcode;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class findDuplicate {
    
    public int findDuplicate(int[] nums){
        // XOR
        return 0;
    }

    public int findDuplicate2(int[] nums) {
        // binary search, Pigeonhole principle
        
        
        return 0;
    }

    public int findDuplicate3(int[] nums) { // O(NlogN) O(1)
        // sort approach. Changed nums, not satrisfied requirement
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return nums[i];
            }
        }
        return 0;
    }

    public int findDuplicate4(int[] nums) { //O(N) O(N)
        //Hash Set, need extra Space, not satisfied requirement

        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return i;
            }
            set.add(nums[i]);
        }
        return 0;
    }

    public int findDuplicate5(int[] nums) {  //O(N^2) O(1)
        //Brute force O(N^2), too much, not satisfied requirement

        for (int i = 0; i < nums.length - 1; i++) {
            int temp = nums[i];
            System.out.println(temp);
            for (int j = i + 1; j < nums.length; j++) {
                if (temp == nums[j]) {
                    return temp;
                }
            }
        }
        return 0;
    }

}
