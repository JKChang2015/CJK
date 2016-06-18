
/*=============================================================================
 1. Two Sum My Submissions Question

 Given an array of integers, return indices of the two numbers such that they 
 add up to a specific target.

 You may assume that each input would have exactly one solution.

 Example:
 Given nums = [2, 7, 11, 15], target = 9,

 Because nums[0] + nums[1] = 2 + 7 = 9,
 return [0, 1].
 ===============================================================================
 */
package array;

import java.util.*;

public class _001twoSum {

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            Integer diff = (Integer) target - nums[i];
            if (map.containsKey(diff)) {
                int[] result = {map.get(diff) + 1, i + 1};
                return result;
            }
            map.put(nums[i], i);
        }
        return null;
    }
    
}
