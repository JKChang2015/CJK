/* -----------------------------------------------------------------------------
35. Search Insert Position 
Given a sorted array and a target value, return the index if the target is found.
If not, return the index where it would be if it were inserted in order.
You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0
--------------------------------------------------------------------------------
@author JKChang
@data 2016-6-26
 */
package leetcode;

public class searchInsert {

    public int searchInsert(int[] nums, int target) {
        //Binary Search
        if (nums.length == 0 || nums == null || target <= nums[0]) {
            return 0;
        }

        int left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = (right + left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (target < nums[mid]) { //in the left part
                right = mid - 1;
            } else { // in the right part
                left = mid + 1;
            }
        }
        return left;
    }
}
