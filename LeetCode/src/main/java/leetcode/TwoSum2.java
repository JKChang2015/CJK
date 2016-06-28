/* ----------------------------------------------------------------------------
Two Sum II - Input array is sorted 

Given an array of integers that is already sorted in ascending order, find two 
numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they
add up to the target, where index1 must be less than index2. Please note that 
your returned answers (both index1 and index2) are not zero-based.
Y
ou may assume that each input would have exactly one solution.
Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 23-Jun-2016
 */
package leetcode;

public class TwoSum2 {

    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            if(numbers[i] + numbers[j] == target){
                break;
            }
            else if (numbers[i] + numbers[j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return new int[] {i+1,j+1};
    }
}
