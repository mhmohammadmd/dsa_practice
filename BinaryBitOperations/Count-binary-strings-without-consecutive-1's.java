Given a positive integer n, count all possible distinct binary strings of length n such that there are no consecutive 1’s.
Examples :
Input: n = 3
Output: 5
Explanation: 5 strings are ("000", "001", "010", "100", "101").
Input: n = 2
Output: 3
Explanation: 3 strings are ("00", "01", "10").
Input: n = 1
Output: 2
Constraints:
1 ≤ n ≤ 44
Approach:
  Using Matrix Exponentiation - O(logn) Time and O(logn) Space
  The recurrence relation to count binary strings of length i is:
     F(i) = F(i-1) + F(i-2)
  with bases cases F(1) = 2 and F(2) = 3. This is similar to Fibonacci Numbers.
  Matrix exponentiation can be used to solve this problem in O(logn) time. Refer to Matrix Exponentiation for detailed approach.
Implementation:
  class Solution {
    // Function to multiply two matrices
    static int[][] multiply(int[][] v1, int[][] v2) {
        int[][] ans = new int[2][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    ans[i][j] += v1[i][k] * v2[k][j];
                }
            }
        }

        return ans;
    }

    // Function to find matrix to the power n
    static int[][] power(int[][] v, int n) {
        if (n == 0) {
            return new int[][] { { 1, 0 }, { 0, 1 } };
        }

        int[][] res = power(v, n / 2);
        res = multiply(res, res);

        if (n % 2 == 1) {
            res = multiply(res, v);
        }

        return res;
    }
    int countStrings(int n) {
        // code here
        if (n == 2)
            return 3;
        if (n == 1)
            return 2;

        int[][] v = { { 1, 1 }, { 1, 0 } };
        int[][] p = power(v, n - 2);

        int ans = p[0][0] * 3 + p[0][1] * 2;
        return ans;
    }
}
