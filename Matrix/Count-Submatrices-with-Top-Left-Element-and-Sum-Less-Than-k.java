You are given a 0-indexed integer matrix grid and an integer k.
Return the number of submatrices that contain the top-left element of the grid, and have a sum less than or equal to k.
Example 1:
Input: grid = [[7,6,3],[6,6,1]], k = 18
Output: 4
Explanation: There are only 4 submatrices, shown in the image above, that contain the top-left element of grid, and have a sum less than or equal to 18.

Example 2:
Input: grid = [[7,2,9],[1,5,0],[2,6,6]], k = 20
Output: 6
Explanation: There are only 6 submatrices, shown in the image above, that contain the top-left element of grid, and have a sum less than or equal to 20.
 
Constraints:
m == grid.length 
n == grid[i].length
1 <= n, m <= 1000 
0 <= grid[i][j] <= 1000
1 <= k <= 109
  
This problem becomes much simpler because of one key constraint:
Every submatrix must include the top-left cell (0,0).
Any valid submatrix is uniquely defined by choosing a bottom-right corner (i, j).
So instead of checking all submatrices, we only check:
Submatrix from (0,0) → (i,j)
Approach (Prefix Sum)
We compute a 2D prefix sum, where:
prefix[i][j]=sum of submatrix from (0,0) to (i,j)
We don’t even need full 2D DP — we can compute it on the fly.
Optimized Idea
We iterate through the grid and maintain cumulative sums:
grid[i][j]=grid[i][j]+top+left−diagonal
Then:
If grid[i][j] <= k, count it.

class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        
        int count = 0;
        
        // Convert grid into prefix sum matrix in-place
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                
                int top = (i > 0) ? grid[i - 1][j] : 0;
                int left = (j > 0) ? grid[i][j - 1] : 0;
                int diag = (i > 0 && j > 0) ? grid[i - 1][j - 1] : 0;
                
                grid[i][j] = grid[i][j] + top + left - diag;
                
                // Check if sum from (0,0) to (i,j) <= k
                if (grid[i][j] <= k) {
                    count++;
                }
            }
        }
        
        return count;
    }
}
