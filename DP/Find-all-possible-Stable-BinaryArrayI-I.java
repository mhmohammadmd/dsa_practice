// You are given 3 positive integers zero, one, and limit.

// A binary array arr is called stable if:

// The number of occurrences of 0 in arr is exactly zero.
// The number of occurrences of 1 in arr is exactly one.
// Each subarray of arr with a size greater than limit must contain both 0 and 1.
// Return the total number of stable binary arrays.

// Since the answer may be very large, return it modulo 109 + 7.
//  Example 1:
// Input: zero = 1, one = 1, limit = 2
// Output: 2
// Explanation:
// The two possible stable binary arrays are [1,0] and [0,1].
// Example 2:
// Input: zero = 1, one = 2, limit = 1
// Output: 1
// Explanation:
// The only possible stable binary array is [1,0,1].
// Example 3:
// Input: zero = 3, one = 3, limit = 2
// Output: 14
// Explanation:
// All the possible stable binary arrays are [0,0,1,0,1,1], [0,0,1,1,0,1], [0,1,0,0,1,1], [0,1,0,1,0,1], [0,1,0,1,1,0], [0,1,1,0,0,1], [0,1,1,0,1,0], [1,0,0,1,0,1], [1,0,0,1,1,0], [1,0,1,0,0,1], [1,0,1,0,1,0], [1,0,1,1,0,0], [1,1,0,0,1,0], and [1,1,0,1,0,0].
// Constraints:
// 1 <= zero, one, limit <= 1000
//   Approach:
// dp0[i][j] = arrays with i zeros, j ones ending with 0
// dp1[i][j] = arrays with i zeros, j ones ending with 1
// When adding 0:
// dp0[i][j] = dp0[i-1][j] + dp1[i-1][j]
// But if we exceed limit consecutive zeros, subtract the invalid case:
// dp1[i-limit-1][j]

class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {

        int MOD = 1_000_000_007;

        long[][] dp0 = new long[zero + 1][one + 1];
        long[][] dp1 = new long[zero + 1][one + 1];

        for (int i = 1; i <= Math.min(limit, zero); i++)
            dp0[i][0] = 1;

        for (int j = 1; j <= Math.min(limit, one); j++)
            dp1[0][j] = 1;

        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {

                dp0[i][j] = (dp0[i-1][j] + dp1[i-1][j]) % MOD;
                if (i - limit - 1 >= 0)
                    dp0[i][j] = (dp0[i][j] - dp1[i-limit-1][j] + MOD) % MOD;

                dp1[i][j] = (dp0[i][j-1] + dp1[i][j-1]) % MOD;
                if (j - limit - 1 >= 0)
                    dp1[i][j] = (dp1[i][j] - dp0[i][j-limit-1] + MOD) % MOD;
            }
        }

        return (int)((dp0[zero][one] + dp1[zero][one]) % MOD);
    }
}
