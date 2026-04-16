Given a string s, convert it into a 32-bit signed integer (similar to the atoi() function) without using any built-in conversion functions.
The conversion follows these rules:
  Ignore Leading Whitespaces: Skip all leading whitespace characters.
  Check Sign: If the next character is either '+' or '-', take it as the sign of the number. If no sign is present, assume the number is positive.
  Read Digits: Read the digits and ignore any leading zeros. Stop reading when a non-digit character is encountered or the end of the string is reached. If no digits are found, return 0.
  Handle Overflow: If the number exceeds the range of a 32-bit signed integer:
  Return 2³¹ − 1 (i.e., 2147483647) if it is greater than the maximum value.
  Return −2³¹ (i.e., -2147483648) if it is smaller than the minimum value.
Return the final integer value.

Examples:
Input: s = "-123"
Output: -123
Explanation: It is possible to convert -123 into an integer so we returned in the form of an integer
Input: s = " -"
Output: 0
Explanation: No digits are present, therefore the returned answer is 0.
Input: s = " 1231231231311133"
Output: 2147483647
Explanation: The converted number will be greater than 231 – 1, therefore print 231 – 1 = 2147483647.
Input: s = "-999999999999"
Output: -2147483648
Explanation: The converted number is smaller than -231, therefore print -231 = -2147483648.
Input: s = "  -0012gfg4"
Output: -12
Explanation: Nothing is read after -12 as a non-digit character ‘g’ was encountered.
Constraints:
1 ≤ |s| ≤ 15

Approach:
  Iterative Approach - O(n) Time and O(1) Space
    Traverse the string from left to right, skipping whitespaces, handling the optional sign, and building the number digit by digit. At each step, ensure the value stays within 32-bit integer limits to avoid overflow.
    How to check if the number is greater than 231 - 1 or smaller than -231 ?

    The naive way is to use a data type larger than 32 bits like long or BigInteger to store the number. However, we can also use a 32-bit integer by appending the digits one-by-one and, for each digit, checking whether appending it will cause overflow.
   Since we construct the number as a positive value and apply the sign at the end, we only need to check against 231 - 1. While appending a digit to the current number, we can have 3 cases:
           Case 1: current number < (231 - 1)/10 then simply append the digit to the current number as it will not cause overflow.
           Case 2: current number > (231 - 1)/10 then return 231 - 1 in case of overflow.
           Case 3: current number = (231 - 1)/10 then in this case, only digits from 0 to 7 can be appended safely. If the next digit is greater than 7, return 231 - 1.

Implementation:
  class Solution {
    public int myAtoi(String s) {
        // code here
        int sign = 1, res = 0, idx = 0;
        
        // Ignore leading whitespaces
        while (idx < s.length() && s.charAt(idx) == ' ') {
            idx++;
        }
        
        // Store the sign of number
        if (idx < s.length() && (s.charAt(idx) == '-' 
                                 || s.charAt(idx) == '+')) {
            if (s.charAt(idx++) == '-') {
                sign = -1;
            }
        }
        
        // Construct the number digit by digit
        while (idx < s.length() && s.charAt(idx) >= '0' 
               						&& s.charAt(idx) <= '9') {
            
            // Handling overflow/underflow test case
            if (res > Integer.MAX_VALUE / 10 || 
                   (res == Integer.MAX_VALUE / 10 && s.charAt(idx) - '0' > 7)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
          
            // Append current digit to the result
            res = 10 * res + (s.charAt(idx++) - '0');
        }
        return res * sign;
    }
}
