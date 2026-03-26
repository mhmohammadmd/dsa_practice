LeetCode #3548
You are given an m x n matrix grid of positive integers.
Your task is to determine if it is possible to make either one horizontal or one vertical cut on the grid such that:
    Each of the two resulting sections formed by the cut is non-empty.
    The sum of elements in both sections is equal, or can be made equal by discounting at most one single cell in total (from either section).
    If a cell is discounted, the rest of the section must remain connected.
Return true if such a partition exists; otherwise, return false.
Note: A section is connected if every cell in it can be reached from any other cell by moving up, down, left, or right through other cells in the section.

Example 1:
Input: grid = [[1,4],[2,3]]
Output: true
Explanation:
A horizontal cut after the first row gives sums 1 + 4 = 5 and 2 + 3 = 5, which are equal. Thus, the answer is true.
Example 2:
Input: grid = [[1,2],[3,4]]
Output: true
Explanation:
A vertical cut after the first column gives sums 1 + 3 = 4 and 2 + 4 = 6.
By discounting 2 from the right section (6 - 2 = 4), both sections have equal sums and remain connected. Thus, the answer is true.

Example 3:
Input: grid = [[1,2,4],[2,3,5]]
Output: false
Explanation:
A horizontal cut after the first row gives 1 + 2 + 4 = 7 and 2 + 3 + 5 = 10.
By discounting 3 from the bottom section (10 - 3 = 7), both sections have equal sums, but they do not remain connected as it splits the bottom section into two parts ([2] and [5]). Thus, the answer is false.

Example 4:
Input: grid = [[4,1,8],[3,2,6]]
Output: false
Explanation:
No valid cut exists, so the answer is false.

Constraints:
1 <= m == grid.length <= 105
1 <= n == grid[i].length <= 105
2 <= m * n <= 105
1 <= grid[i][j] <= 105

Approach: Rotation Matrix + Hash Table + Enumeration of the Upper Matrix Sum
Intuition
This problem is an enhanced version of Equal Sum Grid Partition I, with additional constraints:
    at most one cell can be deleted, and the remaining part must remain connected after deletion.

When deletion is allowed, we need to consider both the choice of the split line and which side of the split to delete from.
To simplify the reasoning, we assume that we only consider horizontal split lines and delete elements from the upper part of the grid.

By rotating the matrix three times by 90 degreeand applying the same logic each time,
we can cover all four possible orientations of the split line and deletion direction.

Next, we determine the condition for a valid partition:
  1. Let the sum of the upper part of the current grid be sum, and the total sum of the grid be total. Then the sum of the lower part is total−sum.
  2. Suppose we remove an element x. To make both parts equal, we must have
        sum−x=total−sum,
        which simplifies to
        x=2⋅sum−total.

  3. Therefore, after processing each row, we only need to check whether there exists an element grid[i][j] such that grid[i][j]=2⋅sum−total.

We use a set to store elements that have appeared so far, allowing efficient lookup.
We can pre-insert 0 into the set so that the "no deletion" case is handled naturally within the same logic.

Special cases:
  1. First row handling:
         While processing the first row, only the first and last elements can be removed.
         After computing the sum of the first row, we check whether grid[0][0], grid[0][n−1], or 0 satisfies the condition.

  2. Single-column grid:
         If the grid has only one column, the only removable elements are from the first or current row.
         After processing row i, we check whether grid[0][0], grid[i][0], or 0 satisfies the condition.

  3. Single-row grid:
         If the grid has only one row, horizontal splitting is not possible, so this case can be skipped.
 
In all other cases, any element in the upper part of the grid can be considered for deletion.
All scenarios are covered after performing three rotations.

Solution:
class Solution {
    public boolean canPartitionGrid(int[][] grid) {
       long totalSum = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        // calculate total sum
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                totalSum += grid[r][c];
            }
        }

        // try all 4 orientations
        for (int rot = 0; rot < 4; rot++) {

            Set<Long> seenValues = new HashSet<>();
            seenValues.add(0L);

            long prefixSum = 0;

            rows = grid.length;
            cols = grid[0].length;

            // if only 1 row → rotate
            if (rows < 2) {
                grid = rotateMatrix(grid);
                continue;
            }

            // special case: single column
            if (cols == 1) {
                for (int r = 0; r < rows - 1; r++) {
                    prefixSum += grid[r][0];

                    long diff = prefixSum * 2 - totalSum;

                    if (diff == 0 || diff == grid[0][0] || diff == grid[r][0]) {
                        return true;
                    }
                }
                grid = rotateMatrix(grid);
                continue;
            }

            // general case
            for (int r = 0; r < rows - 1; r++) {

                for (int c = 0; c < cols; c++) {
                    seenValues.add((long) grid[r][c]);
                    prefixSum += grid[r][c];
                }

                long diff = prefixSum * 2 - totalSum;

                // first row special handling
                if (r == 0) {
                    if (diff == 0 || diff == grid[0][0] || diff == grid[0][cols - 1]) {
                        return true;
                    }
                    continue;
                }

                if (seenValues.contains(diff)) {
                    return true;
                }
            }

            grid = rotateMatrix(grid);
        }

        return false;
    }

    // rotate 90 degree
    public int[][] rotateMatrix(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[][] rotated = new int[cols][rows];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                rotated[c][rows - 1 - r] = grid[r][c];
            }
        }

        return rotated;
    }
}
