// You are given a binary string s. You are allowed to perform two types of operations on the string in any sequence:

// Type-1: Remove the character at the start of the string s and append it to the end of the string.
// Type-2: Pick any character in s and flip its value, i.e., if its value is '0' it becomes '1' and vice-versa.
// Return the minimum number of type-2 operations you need to perform such that s becomes alternating.

// The string is called alternating if no two adjacent characters are equal.

// For example, the strings "010" and "1010" are alternating, while the string "0100" is not.
 

// Example 1:

// Input: s = "111000"
// Output: 2
// Explanation: Use the first operation two times to make s = "100011".
// Then, use the second operation on the third and sixth elements to make s = "101010".
// Example 2:

// Input: s = "010"
// Output: 0
// Explanation: The string is already alternating.
// Example 3:

// Input: s = "1110"
// Output: 1
// Explanation: Use the second operation on the second element to make s = "1010".
 

// Constraints:

// 1 <= s.length <= 105
// s[i] is either '0' or '1'.

// Approach:
// To solve this problem, remember two key ideas:

// An alternating string can only be of two types:

// "010101..."

// "101010..."

// Type-1 operation (rotation) does not change the number of 0s and 1s, it only changes their positions.
// So we can check all rotations of s and compute how many flips are needed to convert it into one of the two alternating patterns.

// Because n can be up to 10^5, generating every rotation directly would be too slow.
// Instead we use a sliding window trick by doubling the string.

// Idea

// Create ss = s + s

// Build two target patterns:

// alt1 = "010101..."

// alt2 = "101010..."

// Slide a window of size n over ss

// Count mismatches with both patterns

// Track the minimum flips needed


class Solution {
    public int minFlips(String s) {
         int n = s.length();
        String ss = s + s;

        int diff1 = 0, diff2 = 0;
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < ss.length(); i++) {
            char c = ss.charAt(i);

            if (c != (i % 2 == 0 ? '0' : '1')) diff1++;
            if (c != (i % 2 == 0 ? '1' : '0')) diff2++;

            if (i >= n) {
                char prev = ss.charAt(i - n);

                if (prev != ((i - n) % 2 == 0 ? '0' : '1')) diff1--;
                if (prev != ((i - n) % 2 == 0 ? '1' : '0')) diff2--;
            }

            if (i >= n - 1) {
                ans = Math.min(ans, Math.min(diff1, diff2));
            }
        }

        return ans;
    }
}
