LeetCode #1886
Given two n x n binary matrices mat and target, return true if it is possible to make mat equal to target by rotating mat in 90-degree increments, or false otherwise.
Example 1:
Input: mat = [[0,1],[1,0]], target = [[1,0],[0,1]]
Output: true
Explanation: We can rotate mat 90 degrees clockwise to make mat equal target.

Example 2:
Input: mat = [[0,1],[1,1]], target = [[1,0],[0,1]]
Output: false
Explanation: It is impossible to make mat equal to target by rotating mat.

Example 3:
Input: mat = [[0,0,0],[0,1,0],[1,1,1]], target = [[1,1,1],[0,1,0],[0,0,0]]
Output: true
Explanation: We can rotate mat 90 degrees clockwise two times to make mat equal target.

Constraints:
n == mat.length == target.length
n == mat[i].length == target[i].length
1 <= n <= 10
mat[i][j] and target[i][j] are either 0 or 1.

Approach:
  Since rotation is only allowed in 90° increments, there are only 4 possible states:
   0° (original matrix)
   90°
   180°
   270°

We just need to:
Rotate the matrix step by step
After each rotation, check if it matches target
Idea:
 Create a helper function to rotate matrix 90° clockwise.
 Create a helper to compare two matrices.
 Try all 4 rotations.
  For 90° clockwise:
    new[i][j] = mat[n - j - 1][i]

Solution:
 class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
         for (int i = 0; i < 4; i++) {
            if (isEqual(mat, target)) return true;
            mat = rotate(mat);
        }
        return false;
    }
     private int[][] rotate(int[][] mat) {
        int n = mat.length;
        int[][] rotated = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotated[j][n - i - 1] = mat[i][j];
            }
        }
        return rotated;
    }

    private boolean isEqual(int[][] a, int[][] b) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] != b[i][j]) return false;
            }
        }
        return true;
    }
}
