/* ----------------------------------------------------------------------------
338. Counting Bits
Given a non negative integer number num. For every numbers i in the range 
0 ≤ i ≤ num calculate the number of 1's in their binary representation and 
return them as an array.

Example:
For num = 5 you should return [0,1,1,2,1,2].

Follow up:

It is very easy to come up with a solution with run time O(n*sizeof(integer)). 
But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).

Hint:

1. You should make use of what you have produced already.
2. Divide the numbers in ranges like [2-3], [4-7], [8-15] and so on. And try to 
generate new range from previous.
3. Or does the odd/even status of the number help you in calculating the number 
of 1s?

 ------------------------------------------------------------------------------ 
 @author jkchang
 @date 22-Jun-2016
 */

package neww;

public class countBits {

    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        return res;
    }
}
