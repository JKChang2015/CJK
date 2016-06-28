/* ----------------------------------------------------------------------------
169. Majority Element
Given an array of size n, find the majority element. The majority element is the
element that appears more than ⌊ n/2 ⌋ times.
You may assume that the array is non-empty and the majority element always exist
in the array.
   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 22-Jun-2016
 */
package leetcode;

import java.util.Arrays;

public class MajorityElement {

    public int majorityElement(int[] nums) {
        if (nums.length == 1) {
            return nums[1];
        }
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    public int majorityElement2(int[] nums) {
        int candidate = nums[0], cnt = 0;
        for (int i = 1; i < nums.length; i++) {
            if (candidate == nums[i]) {
                cnt++;
            } else if (cnt == 0) {
                candidate = nums[i];
            } else {
                cnt--;
            }

            System.out.println("n[i] is " + nums[i] + "  Candidate is  " + candidate + "  count is " + cnt);
        }
        return candidate;
    }
}
