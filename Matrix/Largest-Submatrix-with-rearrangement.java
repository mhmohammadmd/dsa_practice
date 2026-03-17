Leetcode #1727:
You are given a binary matrix matrix of size m x n, and you are allowed to rearrange the columns of the matrix in any order.
Return the area of the largest submatrix within matrix where every element of the submatrix is 1 after reordering the columns optimally.

 
Example 1:
Input: matrix = [[0,0,1],[1,1,1],[1,0,1]]
Output: 4
Explanation: You can rearrange the columns as shown above.
The largest submatrix of 1s, in bold, has an area of 4.

Example 2:
Input: matrix = [[1,0,1,0,1]]
Output: 3
Explanation: You can rearrange the columns as shown above.
The largest submatrix of 1s, in bold, has an area of 3.

Example 3:
Input: matrix = [[1,1,0],[1,0,1]]
Output: 2
Explanation: Notice that you must rearrange entire columns, and there is no way to make a submatrix of 1s larger than an area of 2.
 
Constraints:
m == matrix.length
n == matrix[i].length
1 <= m * n <= 105
matrix[i][j] is either 0 or 1.

Approach:
  Rearrange columns independently for each row, we don’t care about column positions  only about how many consecutive 1s we can stack vertically.
  
Core Idea
Convert each row into a height array:
If matrix[i][j] == 1 → increase height
Else → reset to 0
For each row:
Sort the heights in descending order
Try forming rectangles:
width = number of columns considered
height = minimum height among them (since sorted → current value)
Compute:
area = height[j] * (j + 1)
Take maximum over all rows.

Solution:
  class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        // Step 1: Build heights
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] += matrix[i - 1][j];
                }
            }
        }
        
        int maxArea = 0;
        
        // Step 2: Process each row
        for (int i = 0; i < m; i++) {
            int[] row = matrix[i].clone();
            
            // Sort descending
            Arrays.sort(row);
            
            for (int j = 0; j < n; j++) {
                int height = row[n - 1 - j]; // largest first
                int width = j + 1;
                maxArea = Math.max(maxArea, height * width);
            }
        }
        
        return maxArea;
    }
}
