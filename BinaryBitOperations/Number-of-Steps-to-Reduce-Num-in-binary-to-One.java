// Number of Steps to Reduce a Binary Number to 1
//Problem Summary

// Given a binary string s, return the number of steps required to reduce it to "1" using:

// If the number is even → divide by 2

// If the number is odd → add 1

// Constraints:

// 1 <= s.length <= 500

// s[0] == '1'

// Key Insight

// Instead of converting the binary string into an integer (which may overflow for 500 bits), we simulate the process bit by bit from right to left.

// Observations:

// If the last bit is 0 → number is even → 1 step (divide by 2)

// If the last bit is 1 → number is odd →

// Add 1 (1 step)

// Divide by 2 (1 step)
// → Total 2 steps

// We also track a carry because adding 1 in binary can propagate to higher bits.

//Optimized Approach (O(n))

// Instead of modifying the string repeatedly, we:

// Traverse from right → left

// Maintain a carry

// Count operations logically
class Solution {
    public int numSteps(String s) {
        int steps = 0;
        int carry = 0;
        
        for (int i = s.length() - 1; i > 0; i--) {
            int bit = (s.charAt(i) - '0') + carry;
            
            if (bit % 2 == 0) {
                // Even → divide
                steps += 1;
            } else {
                // Odd → add 1 then divide
                steps += 2;
                carry = 1;
            }
        }
        
        return steps + carry;
    }
}
