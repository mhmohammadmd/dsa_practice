// Compute prefix sums row-wise to allow quick calculation of horizontal subarray sums.
// Then, for every pair of columns i and j, reduce the matrix to a 1D array by summing values in each row between columns i and j.
// For each such 1D array, maintain a running sum and use a hash map to count how many times a particular prefix sum has occurred.
// Whenever the difference between the current sum and the target is found in the map, increment the result by that frequency.
// Approach:
//    Prefix sum with hashing
class Solution {
    public int countSquare(int[][] mat, int x) {
        // code here
        int res = 0;
        int n = mat.length;
        int m = mat[0].length;

        // Compute row-wise prefix sum
        int[][] rowPrefix = new int[n][m];
        for (int i = 0; i < n; i++) {
            rowPrefix[i][0] = mat[i][0];
            for (int j = 1; j < m; j++) {
                rowPrefix[i][j] = rowPrefix[i][j - 1] + mat[i][j];
            }
        }

        int maxSize = Math.min(n, m);

        // Try each possible square size
        for (int size = 1; size <= maxSize; size++) {
            for (int i = 0; i <= m - size; i++) {
                int j = i + size - 1;
                int sum = 0;

                for (int row = 0; row < size - 1; row++) {
                    sum += rowPrefix[row][j] - 
                                (i > 0 ? rowPrefix[row][i - 1] : 0);
                }

                for (int row = size - 1; row < n; row++) {
                    sum += rowPrefix[row][j] - 
                                (i > 0 ? rowPrefix[row][i - 1] : 0);

                    if (sum == x) res++;

                    sum -= rowPrefix[row - size + 1][j] - 
                            (i > 0 ? rowPrefix[row - size + 1][i - 1] : 0);
                }
            }
        }

        return res;
    }
}
