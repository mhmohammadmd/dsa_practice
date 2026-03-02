// Given an n x n binary grid, in one step you can choose two adjacent rows of the grid and swap them.

// A grid is said to be valid if all the cells above the main diagonal are zeros.

// Return the minimum number of steps needed to make the grid valid, or -1 if the grid cannot be valid.

// The main diagonal of a grid is the diagonal that starts at cell (1, 1) and ends at cell (n, n).

// Input: grid = [[0,0,1],[1,1,0],[1,0,0]]
// Output: 3

// Input: grid = [[0,1,1,0],[0,1,1,0],[0,1,1,0],[0,1,1,0]]
// Output: -1
// Explanation: All rows are similar, swaps have no effect on the grid.

class Solution {
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] trailingZeros = new int[n];
        
        // Step 1: Count trailing zeros for each row
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 0) {
                    count++;
                } else {
                    break;
                }
            }
            trailingZeros[i] = count;
        }
        
        int swaps = 0;
        
        // Step 2: Try to place correct row at each position
        for (int i = 0; i < n; i++) {
            int requiredZeros = n - i - 1;
            int j = i;
            
            // Find row with enough trailing zeros
            while (j < n && trailingZeros[j] < requiredZeros) {
                j++;
            }
            
            // If not found → impossible
            if (j == n) {
                return -1;
            }
            
            // Bring row j to position i using adjacent swaps
            while (j > i) {
                int temp = trailingZeros[j];
                trailingZeros[j] = trailingZeros[j - 1];
                trailingZeros[j - 1] = temp;
                
                swaps++;
                j--;
            }
        }
        
        return swaps;
    }
}
