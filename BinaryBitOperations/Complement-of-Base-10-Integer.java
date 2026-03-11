// #Leetcode 1009
// The complement of an integer is the integer you get when you flip all the 0's to 1's and all the 1's to 0's in its binary representation.
// For example, The integer 5 is "101" in binary and its complement is "010" which is the integer 2.
// Given an integer n, return its complement.
// Example 1:
// Input: n = 5
// Output: 2
// Explanation: 5 is "101" in binary, with complement "010" in binary, which is 2 in base-10.
// Example 2:
// Input: n = 7
// Output: 0
// Explanation: 7 is "111" in binary, with complement "000" in binary, which is 0 in base-10.
// Idea:
// The idea is to flip only the bits that exist in the binary representation of n (not all 32 bits).
// Steps:
// Find the binary length of n.
// Create a mask with all bits set to 1 of the same length.
// XOR the mask with n to flip the bits.
  
  class Solution {
    public int bitwiseComplement(int n) {
         int mask = 0;
        int temp = n;
        if(n==0)return 1;

        while (temp > 0) {
            mask = (mask << 1) | 1;
            temp >>= 1;
        }

        return n ^ mask;
    }
}
