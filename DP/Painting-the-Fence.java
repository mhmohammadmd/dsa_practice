Given a fence with n posts and k colours, find out the number of ways of painting the fence so that not more than two consecutive posts have the same colours.
Answers are guaranteed to be fit into a 32 bit integer.

Examples:
Input: n = 3, k = 2 
Output: 6
Explanation: Let the 2 colours be 'R' and 'B'. We have following possible combinations:
1. RRB
2. RBR
3. RBB
4. BRR
5. BRB
6. BBR
Input: n = 2, k = 4 
Output: 16
Explanation: After coloring first post with 4 possible combinations, you can still color next posts with all 4 colors. Total possible combinations will be 4x4=16
Constraints:
1 ≤ n ≤ 300
1 ≤ k ≤ 105

Approach:
  Using Space Optimized DP – O(n) Time and O(1) Space
  The idea is to store only the previous two computed values. We can observe that for a given fence,
  only the result of last three fences are needed. So only store these three values and update them after each step.
Implementation:
   class Solution {
    int countWays(int n, int k) {
        // code here.
         // base cases
        if (n == 1) return k;
        if (n == 2) return k * k;
        
        // Fill value for 1 and 2 fences
        int prev2 = k;
        int prev1 = k * k;
        
        for (int i = 3; i <= n; i++) {
            int curr = prev1 * (k - 1) + prev2 * (k - 1);
            
            // update the values
            prev2 = prev1;
            prev1 = curr;
        }
        
        return prev1;
    }
}
