// Given an integer n, return the decimal value of the binary string formed by concatenating the binary representations of 1 to n in order, modulo 109 + 7.
  
// Example 1:
// Input: n = 1
// Output: 1
// Explanation: "1" in binary corresponds to the decimal value 1. 
// Example 2:
// Input: n = 3
// Output: 27
// Explanation: In binary, 1, 2, and 3 corresponds to "1", "10", and "11".
// After concatenating them, we have "11011", which corresponds to the decimal value 27.
//Apprch:
// Instead of actually building the huge binary string (which would overflow), we:

// Start with result = 0

// For each number i from 1 to n:

// Find how many bits i has.

// Left shift result by that many bits.

// Add i.

// Take modulo 10^9 + 7.


class Solution {
    public int concatenatedBinary(int n) {
        int mod = 1000000007;
        long result = 0;
        int length = 0;   // number of bits
        
        for (int i = 1; i <= n; i++) {
            
            // If i is power of 2, increase bit length
            if ((i & (i - 1)) == 0) {
                length++;
            }
            
            // Shift result left by length bits and add i
            result = ((result << length) + i) % mod;
        }
        
        return (int) result;
    }
}
