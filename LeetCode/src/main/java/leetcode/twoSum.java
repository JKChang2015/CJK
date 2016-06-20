/* ----------------------------------------------------------------------------
    001	Two Sum
    Given an array of integers, return indices of the two numbers such that they add up to a specific target.
    You may assume that each input would have exactly one solution.

    Example:
    Given nums = [2, 7, 11, 15], target = 9,
    Because nums[0] + nums[1] = 2 + 7 = 9,
    return [0, 1].
   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 20-Jun-2016
 */
package leetcode;

import java.util.HashMap;
import java.util.Map;

public class twoSum {

    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }

        int[] results = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) { // i is index
            //check the HashMap first, then add the new num    
            if (map.containsKey(target - nums[i])) {
                results[1] = i;
                results[0] = map.get(target - nums[i]);
                return results;
            }

            int num = nums[i];
            map.put(num, i); //<K,V>           
        }
        return null;
    }

    //if the quetion allows use the mumber more than one time
    // e.g. [1, 2, 5] target= 4 return[1, 1]
    public int[] twoSum2(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        
        int[] results = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            // put the number to the hashMap first, then check the result
            int num = nums[i];
            map.put(num, i); //<K,V>    

            if (map.containsKey(target - nums[i])) {
                results[1] = i;
                results[0] = map.get(target - nums[i]);
                return results;
            }

        }
        return null;
    }

}

// notes the index of nums start from 0!
// The basic idea is using hashTable

