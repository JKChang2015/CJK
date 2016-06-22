/* ----------------------------------------------------------------------------
344. Reverse String 
Write a function that takes a string as input and returns the string reversed.

Example:
Given s = "hello", return "olleh".
   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 22-Jun-2016
 */
package leetcode;

public class reverseString {
    
    public String reverseString(String s) {
        char[] str = s.toCharArray();
        int left = 0;
        int right = str.length - 1;
        
        while (left < right) {
            char temp = str[left];
            str[left] = str[right];
            str[right] = temp;
            left++;
            right--;
        }
        
        return new String(str);
    }
}
