/* ----------------------------------------------------------------------------
015. 3Sum 
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
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) { // O(N^3)
        List<List<Integer>> resList = new ArrayList<List<Integer>>();
        if (nums.length < 3) {
            return resList;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; ++i) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int start = i + 1, end = nums.length - 1;
            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];

                if (sum < 0) {
                    start++;
                } else if (sum > 0) {
                    end--;
                } else {  // add to list
                    List<Integer> res = new ArrayList<Integer>(3);
                    res.add(nums[i]);
                    res.add(nums[start]);
                    res.add(nums[end]);
                    resList.add(res);

                    // remove duplicated
                    do {
                        start++;
                    } while (start < end && nums[start] == nums[start - 1]);
                    do {
                        end--;
                    } while (start < end && nums[end] == nums[end + 1]);

                }
            }

        }

        return resList;
    }

}
