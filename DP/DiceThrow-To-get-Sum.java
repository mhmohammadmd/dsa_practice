// Given n dice each with m faces. Find the number of ways to get sum x which is the summation of values on each face when all the dice are thrown.

// Example:

// Input: m = 6, n = 3, x = 12
// Output: 25
// Explanation: There are 25 total ways to get the Sum 12 using 3 dices with faces from 1 to 6.
// Input: m = 2, n = 3, x = 6
// Output: 1
// Explanation: There is only 1 way to get the Sum 6 using 3 dices with faces from 1 to 2. All the dices will have to land on 2.
// Constraints:
// 1 <= m,n,x <= 50
// Approach:
// dp[i][j] = sum(dp[i-1][j-k]) where k is in range [1, m] and j-k >= 0.
// If we observe that for calculating current dp[i][j] state we only need previous row. There is no need to store all the previous states just one previous state is used to compute result.


class Solution {
    static int noOfWays(int m, int n, int x) {
        // code here
        // Initialize a 1D dp array of size (x + 1), all
        // values initially 0 dp[j] will store the number of
        // ways to get a sum of 'j' using 'i' dice
        int[] dp = new int[x + 1];

        // Base case: There is 1 way to get sum 0 (using no
        // dice)
        dp[0] = 1;

        // Iterate through each dice (i) from 1 to n (total
        // number of dice)
        for (int i = 1; i <= n; i++) {

            // Iterate backwards through all possible sums
            // (j) from x to 1 to avoid overwriting the
            // results from the previous dice count
            for (int j = x; j >= 1; j--) {

                // Reset dp[j] before calculating its new
                // value for the current dice
                dp[j] = 0;

                // Loop through all possible outcomes of the
                // dice (k) from 1 to m (faces of the dice)
                // If j - k is a valid sum (non-negative),
                // update dp[j]
                for (int k = 1; k <= m && j - k >= 0; k++) {
                    dp[j] += dp[j - k];
                }
            }

            // After each dice iteration, set dp[0] to 0
            // since there are no ways to achieve sum 0
            dp[0] = 0;
        }

        // Return the number of ways to get sum 'x' using
        // 'n' dice
        return dp[x];
    }
};
