LeetCode #3546
You are given an m x n matrix grid of positive integers. Your task is to determine if it is possible to make either one horizontal or one vertical cut on the grid such that:
Each of the two resulting sections formed by the cut is non-empty.
The sum of the elements in both sections is equal.
Return true if such a partition exists; otherwise return false.

Example 1:
Input: grid = [[1,4],[2,3]]
Output: true
Explanation:
A horizontal cut between row 0 and row 1 results in two non-empty sections, each with a sum of 5. Thus, the answer is true.

Example 2:
Input: grid = [[1,3],[2,4]]
Output: false
Explanation:
No horizontal or vertical cut results in two non-empty sections with equal sums. Thus, the answer is false.

Constraints:
1 <= m == grid.length <= 105
1 <= n == grid[i].length <= 105
2 <= m * n <= 105
1 <= grid[i][j] <= 105

Key Idea:
  First compute total sum of the grid.
  If total sum is odd → impossible (return false).
  Otherwise, target = total / 2.

Now check:
   Horizontal cut → sum row by row
   Vertical cut → sum column by column
If at any point cumulative sum == target → return true

Approach:
1. Total sum:
       total = sum of all elements
       if (total % 2 != 0) return false;
2. Try Horizontal Cuts
       Keep adding row sums
       Stop before last row (both parts must be non-empty)
3. Try Vertical Cuts
       Same logic, but column-wise

Solution:
    class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        long total = 0;

        // Step 1: Compute total sum
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                total += grid[i][j];
            }
        }

        // If total is odd, cannot split equally
        if (total % 2 != 0) return false;

        long target = total / 2;

        // Step 2: Check horizontal cuts
        long rowSum = 0;
        for (int i = 0; i < m - 1; i++) { // ensure non-empty bottom
            for (int j = 0; j < n; j++) {
                rowSum += grid[i][j];
            }
            if (rowSum == target) return true;
        }

        // Step 3: Check vertical cuts
        long colSum = 0;
        for (int j = 0; j < n - 1; j++) { // ensure non-empty right
            for (int i = 0; i < m; i++) {
                colSum += grid[i][j];
            }
            if (colSum == target) return true;
        }

        return false;
    }
}
