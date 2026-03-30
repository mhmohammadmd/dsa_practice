LeetCode#2840
You are given two strings s1 and s2, both of length n, consisting of lowercase English letters.
You can apply the following operation on any of the two strings any number of times:
   Choose any two indices i and j such that i < j and the difference j - i is even, then swap the two characters at those indices in the string.
   Return true if you can make the strings s1 and s2 equal, and false otherwise.

 

Example 1:
Input: s1 = "abcdba", s2 = "cabdab"
Output: true
Explanation: We can apply the following operations on s1:
- Choose the indices i = 0, j = 2. The resulting string is s1 = "cbadba".
- Choose the indices i = 2, j = 4. The resulting string is s1 = "cbbdaa".
- Choose the indices i = 1, j = 5. The resulting string is s1 = "cabdab" = s2.

Example 2:
Input: s1 = "abe", s2 = "bea"
Output: false
Explanation: It is not possible to make the two strings equal.
 

Constraints:
n == s1.length == s2.length
1 <= n <= 105
s1 and s2 consist only of lowercase English letters.

Approach:
Key Insight
  You are allowed to swap characters at indices i and j such that (j - i) is even.
That means:
  Even index ↔ Even index (0, 2, 4, …)
  Odd index ↔ Odd index (1, 3, 5, …)
  >> You cannot mix even and odd positions.

Conclusion
The string is effectively split into two independent groups:
  1. Characters at even indices
  2. Characters at odd indices
You can freely rearrange characters within each group.

So condition becomes:
  For both strings:
    The multiset (frequency) of characters at even indices must match
    The multiset (frequency) of characters at odd indices must match
Approach:
  1. Traverse both strings
  2. Count frequency separately:
        > even indices
        > odd indices
  3. Compare both frequency map

Implementation:
  class Solution {
    public boolean checkStrings(String s1, String s2) {
        int n = s1.length();
        
        int[] even1 = new int[26];
        int[] odd1 = new int[26];
        int[] even2 = new int[26];
        int[] odd2 = new int[26];
        
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                even1[s1.charAt(i) - 'a']++;
                even2[s2.charAt(i) - 'a']++;
            } else {
                odd1[s1.charAt(i) - 'a']++;
                odd2[s2.charAt(i) - 'a']++;
            }
        }
        
        for (int i = 0; i < 26; i++) {
            if (even1[i] != even2[i] || odd1[i] != odd2[i]) {
                return false;
            }
        }
        
        return true;
    }
}
