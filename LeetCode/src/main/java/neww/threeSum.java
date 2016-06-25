/* ----------------------------------------------------------------------------
15. 3Sum 
Total Accepted: 124963 Total Submissions: 650797 Difficulty: Medium
Given an array S of n integers, are there elements a, b, c in S such that 
a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note: The solution set must not contain duplicate triplets.

For example, given array S = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 24-Jun-2016
 */
package neww;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class threeSum {

    public List<List<Integer>> threeSum(int[] nums) { // O(N^3)
        Arrays.sort(nums);
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int i = 0; i < nums.length - 2; i++) {
            //  skip duplicated
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // Calculate 2 sum
            ArrayList<List<Integer>> curr = twoSum(nums, i, 0 - nums[i]);
            res.addAll(curr);
        }
        return res;
    }

    private ArrayList<List<Integer>> twoSum(int[] nums, int i, int target) {
        int left = i + 1;
        int right = nums.length - 1;
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();

        while (left < right) {
            if (nums[left] + nums[right] == target) {
                ArrayList<Integer> curr = new ArrayList<Integer>();
                curr.add(nums[i]);
                curr.add(nums[left]);
                curr.add(nums[right]);
                res.add(curr);

                do {
                    left++;
                } while (left < nums.length && nums[left] == nums[left + 1]);
                do {
                    right--;
                } while (right >= 0 && nums[right] == nums[right + 1]);
            } else if (nums[left] + nums[right] > target) {
                right--;
            } else {
                left++;
            }
        }
        return res;
    }
}
