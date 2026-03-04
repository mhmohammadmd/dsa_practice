// Input: mat = [[1,0,0],[0,0,1],[1,0,0]]
// Output: 1
// Explanation: (1, 2) is a special position because mat[1][2] == 1 and all other elements in row 1 and column 2 are 0.

// Problem Understanding

// A position (i, j) is special if:

// mat[i][j] == 1

// All other elements in row i are 0

// All other elements in column j are 0

// So basically:
// The row must contain exactly one 1
// The column must contain exactly one 1
// And that position must be 1

// Efficient Approach (O(m × n))

// Instead of checking every row and column again and again:

// Step 1:

// Count number of 1s in each row.

// Step 2:

// Count number of 1s in each column.

// Step 3:

// Traverse the matrix again and count positions where:

// mat[i][j] == 1 
// AND rowCount[i] == 1 
// AND colCount[j] == 1


class Solution {
    public int numSpecial(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        
        int[] rowCount = new int[m];
        int[] colCount = new int[n];
        
        // Count 1s in rows and columns
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    rowCount[i]++;
                    colCount[j]++;
                }
            }
        }
        
        int count = 0;
        
        // Find special positions
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1 && rowCount[i] == 1 && colCount[j] == 1) {
                    count++;
                }
            }
        }
        
        return count;
    }
}
