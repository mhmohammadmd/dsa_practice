LeetCode : 3212
Given a 2D character matrix grid, where grid[i][j] is either 'X', 'Y', or '.', return the number of submatrices that contain:
grid[0][0] an equal frequency of 'X' and 'Y'. at least one 'X'.
 
Example 1:
Input: grid = [["X","Y","."],["Y",".","."]]
Output: 3

Example 2:
Input: grid = [["X","X"],["X","Y"]]
Output: 0

Explanation:
No submatrix has an equal frequency of 'X' and 'Y'.

Example 3:
Input: grid = [[".","."],[".","."]]
Output: 0
Explanation:
No submatrix has at least one 'X'.

Constraints:
1 <= grid.length, grid[i].length <= 1000
grid[i][j] is either 'X', 'Y', or '.'.

This is a prefix-sum + hashmap (balance technique) problem, very similar to subarray problems where we track equal counts.
Key Observations
We only consider submatrices that:
include (0,0) → so top-left is fixed vary only in bottom-right corner (i, j)

So every valid submatrix is from (0,0) to (i,j)
Goal
For each (i, j):
count of 'X' == count of 'Y'
count of 'X' ≥ 1
Idea
Convert problem:
Treat 'X' = +1
Treat 'Y' = -1
Treat '.' = 0
Now:
We want sum = 0 (equal X and Y)
AND at least one 'X'
Approach
We compute prefix sums:
px[i][j] → count of 'X'
py[i][j] → count of 'Y'
For each (i, j):
X = px[i][j]
Y = py[i][j]
Check:
if (X == Y && X > 0)
    count++

Solution:
class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        
        int[][] px = new int[m][n]; // count of X
        int[][] py = new int[m][n]; // count of Y
        
        int count = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                
                // current cell contribution
                int x = (grid[i][j] == 'X') ? 1 : 0;
                int y = (grid[i][j] == 'Y') ? 1 : 0;
                
                // build prefix sum
                px[i][j] = x;
                py[i][j] = y;
                
                if (i > 0) {
                    px[i][j] += px[i - 1][j];
                    py[i][j] += py[i - 1][j];
                }
                if (j > 0) {
                    px[i][j] += px[i][j - 1];
                    py[i][j] += py[i][j - 1];
                }
                if (i > 0 && j > 0) {
                    px[i][j] -= px[i - 1][j - 1];
                    py[i][j] -= py[i - 1][j - 1];
                }
                
                // check condition for submatrix (0,0) → (i,j)
                if (px[i][j] == py[i][j] && px[i][j] > 0) {
                    count++;
                }
            }
        }
        
        return count;
    }
}
