// Given an array arr[] of positive integers, find the total sum of the minimum elements of every possible subarrays.
// Note: It is guaranteed that the total sum will fit within a 32-bit unsigned integer.
// Examples:
// Input: arr[] = [10, 20]
// Output: 40
// Explanation: Subarrays are [10], [20], [10, 20]. Minimums are 10, 20, 10.
// Sum of all these is 40.
// Input: arr[] = [1, 2, 3, 4]
// Output: 20
// Explanation: Subarrays are [1], [2], [3], [4], [1, 2], [1, 2, 3], [1, 2, 3, 4], [2, 3], [2, 3, 4], [3, 4]. Minimums are 1, 2, 3, 4, 1, 1, 1, 2, 2, 3.
// Sum of all these is 20.
// Constraints:
// 1 ≤ arr.size() ≤ 3*104
// 1 ≤ arr[i] ≤ 103

//   Idea:
//    Stack
//      The idea is to compute the index of the next smaller element to the right for each element using a monotonic stack (increasing stack).
//       This helps us determine how far the current element remains the minimum in subarrays starting from its index.

class Solution {
    public int sumSubMins(int[] arr) {
        // code here
        int n = arr.length;
        int[] dp = new int[n];
        int[] right = new int[n];
        Stack<Integer> st = new Stack<>();

        // Initialize right[] to self indices
        for (int i = 0; i < n; i++) right[i] = i;

        // Find index of next smaller
        // element on the right
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[i] < arr[st.peek()]) {
                right[st.pop()] = i;
            }
            st.push(i);
        }

        // Fill dp[] from right to left
        dp[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            int r = right[i];
            if (r == i) {
                dp[i] = (n - i) * arr[i];
            } else {
                dp[i] = (r - i) * arr[i] + dp[r];
            }
        }

        int sum = 0;
        for (int val : dp) sum += val;
        return sum;
    }
}
