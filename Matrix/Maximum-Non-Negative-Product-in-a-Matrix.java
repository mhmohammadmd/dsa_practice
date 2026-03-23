LeetCode #1594
You are given a m x n matrix grid. Initially, you are located at the top-left corner (0, 0), and in each step, you can only move right or down in the matrix.
Among all possible paths starting from the top-left corner (0, 0) and ending in the bottom-right corner (m - 1, n - 1),
find the path with the maximum non-negative product. The product of a path is the product of all integers in the grid cells visited along the path.
Return the maximum non-negative product modulo 109 + 7. If the maximum product is negative, return -1.

Notice that the modulo is performed after getting the maximum product.

Example 1:
Input: grid = [[-1,-2,-3],[-2,-3,-3],[-3,-3,-2]]
Output: -1
Explanation: It is not possible to get non-negative product in the path from (0, 0) to (2, 2), so return -1.

Example 2:
Input: grid = [[1,-2,1],[1,-2,1],[3,-4,1]]
Output: 8
Explanation: Maximum non-negative product is shown (1 * 1 * -2 * -4 * 1 = 8).

Example 3:
Input: grid = [[1,3],[0,-4]]
Output: 0
Explanation: Maximum non-negative product is shown (1 * 0 * -4 = 0).
 
Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 15
-4 <= grid[i][j] <= 4
 
Approach:
At each cell (i, j):
You must track two values:
Maximum product till here
Minimum product till here
Why?
Because:
   Negative × Negative = Positive
   So a minimum (negative) can become the maximum later
DP State:
  let
     maxDp[i][j] = maximum product reaching (i,j)
     minDp[i][j] = minimum product reaching (i,j)
TransitionL:
   From top (i-1, j) and left (i, j-1):
          If current value = grid[i][j]
              maxDp[i][j] = max(
                        grid[i][j] * maxDp from top,
                        grid[i][j] * minDp from top,
                        grid[i][j] * maxDp from left,
                        grid[i][j] * minDp from left
)
minDp[i][j] = min(same 4 values)


Solution

  class Solution {
    public int maxProductPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        
        long[][] maxDp = new long[m][n];
        long[][] minDp = new long[m][n];
        
        // Base case
        maxDp[0][0] = grid[0][0];
        minDp[0][0] = grid[0][0];
        
        // First column
        for (int i = 1; i < m; i++) {
            maxDp[i][0] = maxDp[i-1][0] * grid[i][0];
            minDp[i][0] = maxDp[i][0];
        }
        
        // First row
        for (int j = 1; j < n; j++) {
            maxDp[0][j] = maxDp[0][j-1] * grid[0][j];
            minDp[0][j] = maxDp[0][j];
        }
        
        // Fill DP
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                long val = grid[i][j];
                
                long a = maxDp[i-1][j] * val;
                long b = minDp[i-1][j] * val;
                long c = maxDp[i][j-1] * val;
                long d = minDp[i][j-1] * val;
                
                maxDp[i][j] = Math.max(Math.max(a, b), Math.max(c, d));
                minDp[i][j] = Math.min(Math.min(a, b), Math.min(c, d));
            }
        }
        
        long res = maxDp[m-1][n-1];
        int mod = (int)1e9 + 7;
        
        if (res < 0) return -1;
        return (int)(res % mod);
    }
}
