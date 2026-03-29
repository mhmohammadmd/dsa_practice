LeetCode #2839
You are given two strings s1 and s2, both of length 4, consisting of lowercase English letters.
You can apply the following operation on any of the two strings any number of times:
   Choose any two indices i and j such that j - i = 2, then swap the two characters at those indices in the string.
   Return true if you can make the strings s1 and s2 equal, and false otherwise.

 

Example 1:
Input: s1 = "abcd", s2 = "cdab"
Output: true
Explanation: We can do the following operations on s1:
- Choose the indices i = 0, j = 2. The resulting string is s1 = "cbad".
- Choose the indices i = 1, j = 3. The resulting string is s1 = "cdab" = s2.

Example 2:
Input: s1 = "abcd", s2 = "dacb"
Output: false
Explanation: It is not possible to make the two strings equal.
 
Constraints:
s1.length == s2.length == 4
s1 and s2 consist only of lowercase English letters.

Approach:
You are allowed to swap characters at positions where j - i = 2.
For a string of length 4, the valid swaps are:
    Index 0 ↔ 2
    Index 1 ↔ 3

This means:
  Characters at even indices (0, 2) can only move among themselves
  Characters at odd indices (1, 3) can only move among themselves
So effectively, you cannot mix even and odd positions.

Condition to make s1 == s2
To make both strings equal:
   The characters at even positions in both strings must match (after rearranging)
   The characters at odd positions in both strings must match (after rearranging)
Approach
 1. Extract:
    Even-index characters from both strings
    Odd-index characters from both strings
 2. Sort them separately
 3. Compare

Implementation:
 class Solution {
    public boolean canBeEqual(String s1, String s2) {
        char[] s1_even = {s1.charAt(0), s1.charAt(2)};
        char[] s1_odd  = {s1.charAt(1), s1.charAt(3)};
        
        char[] s2_even = {s2.charAt(0), s2.charAt(2)};
        char[] s2_odd  = {s2.charAt(1), s2.charAt(3)};
        
        Arrays.sort(s1_even);
        Arrays.sort(s1_odd);
        Arrays.sort(s2_even);
        Arrays.sort(s2_odd);
        
        return Arrays.equals(s1_even, s2_even) && Arrays.equals(s1_odd, s2_odd);
    }
}
