LeetCode #2573
We define the lcp matrix of any 0-indexed string word of n lowercase English letters as an n x n grid such that:
    lcp[i][j] is equal to the length of the longest common prefix between the substrings word[i,n-1] and word[j,n-1].
    Given an n x n matrix lcp, return the alphabetically smallest string word that corresponds to lcp. If there is no such string, return an empty string.
    A string a is lexicographically smaller than a string b (of the same length) if in the first position where a and b differ, string a has a letter that appears earlier in the alphabet than the corresponding letter in b. For example, "aabd" is lexicographically smaller than "aaca" because the first position they differ is at the third letter, and 'b' comes before 'c'.

 
Example 1:
Input: lcp = [[4,0,2,0],[0,3,0,1],[2,0,2,0],[0,1,0,1]]
Output: "abab"
Explanation: lcp corresponds to any 4 letter string with two alternating letters. The lexicographically smallest of them is "abab".

Example 2:
Input: lcp = [[4,3,2,1],[3,3,2,1],[2,2,2,1],[1,1,1,1]]
Output: "aaaa"
Explanation: lcp corresponds to any 4 letter string with a single distinct letter. The lexicographically smallest of them is "aaaa". 

Example 3:
Input: lcp = [[4,3,2,1],[3,3,2,1],[2,2,2,1],[1,1,1,3]]
Output: ""
Explanation: lcp[3][3] cannot be equal to 3 since word[3,...,3] consists of only a single letter; Thus, no answer exists.
 
Constraints:
1 <= n == lcp.length == lcp[i].length <= 1000
0 <= lcp[i][j] <= n

Core Idea
 We need to construct a string such that:
   >> lcp[i][j] = length of common prefix of suffix(i) and suffix(j)
Observations:
  1. Diagonal check (VERY IMPORTANT)
     lcp[i][i] = n - i
    Because suffix starting at i matches itself fully.
    If this fails → return ""
  2. If lcp[i][j] > 0
     Then:
        word[i] == word[j]
     So we can group indices that must have the same character.
  3. Use Greedy + DSU (Union-Find)
      If lcp[i][j] > 0 → union(i, j)
         Assign smallest characters ('a', 'b', …) to components
  4. Final Validation (CRITICAL)
     After building string, recompute LCP and compare with given matrix.

Approach:
Step 1: Validate diagonal
Step 2: Union indices with lcp[i][j] > 0
Step 3: Assign characters to components
Step 4: Rebuild LCP and verify

Implementation:

  class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        
        // Step 1: Diagonal validation
        for (int i = 0; i < n; i++) {
            if (lcp[i][i] != n - i) return "";
        }
        
        // Step 2: DSU setup
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        
        // Find function
        java.util.function.IntUnaryOperator find = new java.util.function.IntUnaryOperator() {
            public int applyAsInt(int x) {
                if (parent[x] != x) parent[x] = applyAsInt(parent[x]);
                return parent[x];
            }
        };
        
        // Union function
        java.util.function.BiConsumer<Integer, Integer> union = (a, b) -> {
            int pa = find.applyAsInt(a);
            int pb = find.applyAsInt(b);
            if (pa != pb) parent[pb] = pa;
        };
        
        // Step 3: Union based on lcp
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (lcp[i][j] > 0) {
                    union.accept(i, j);
                }
            }
        }
        
        // Step 4: Assign characters
        char[] res = new char[n];
        Map<Integer, Character> map = new HashMap<>();
        char ch = 'a';
        
        for (int i = 0; i < n; i++) {
            int p = find.applyAsInt(i);
            if (!map.containsKey(p)) {
                if (ch > 'z') return ""; // not enough letters
                map.put(p, ch++);
            }
            res[i] = map.get(p);
        }
        
        // Step 5: Validate by recomputing LCP
        int[][] check = new int[n][n];
        
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (res[i] == res[j]) {
                    if (i + 1 < n && j + 1 < n)
                        check[i][j] = 1 + check[i + 1][j + 1];
                    else
                        check[i][j] = 1;
                }
            }
        }
        
        // Compare matrices
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (check[i][j] != lcp[i][j]) return "";
            }
        }
        
        return new String(res);
    }
}
