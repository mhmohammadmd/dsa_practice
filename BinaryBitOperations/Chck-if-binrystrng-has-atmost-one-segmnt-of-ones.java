// Given a binary string s ​​​​​without leading zeros, return true​​​ if s contains at most one contiguous segment of ones. Otherwise, return false.
// Example 1:

// Input: s = "1001"
// Output: false
// Explanation: The ones do not form a contiguous segment.
// Example 2:

// Input: s = "110"
// Output: true
 

// Constraints:

// 1 <= s.length <= 100
// s[i]​​​​ is either '0' or '1'.
// s[0] is '1'.

// We need to check if the binary string has only one continuous block of '1's.

// Key idea:
// If there is more than one segment of 1s, the pattern "01" followed later by "1" appears, which means the string contains "01" then later another '1'.
// An easier check is to see if the string contains "01" followed by "1", which forms "01...1", but the simplest trick is:

// If the string contains "01" followed by "1", it means two segments of 1s.

// But the cleanest solution is checking if "01" appears before the end of the string's 1s by verifying that "01" never appears followed by another '1', which simplifies to checking if "01" occurs and then another '1' exists.

// However, the standard trick is simply:

// If the string contains "01" more than once, it's invalid.

//   Explanation

// "01" means the sequence of 1s ended.

// If another 1 appears later, it would create another "01" pattern.

// Therefore if "01" appears once, the rest must be all 0s.

// Example

// Input: "1001"
// Pattern: 1 → 0 → 0 → 1
// Two segments of 1s → false

// Input: "110"
// Pattern: 11 → 0
// Only one segment of 1s → true

// Time Complexity

// O(n)

class Solution {
    public boolean checkOnesSegment(String s) {
        return !s.contains("01");
    }
}
