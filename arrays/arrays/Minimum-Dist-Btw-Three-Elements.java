You are given an integer array nums.
A tuple (i, j, k) of 3 distinct indices is good if nums[i] == nums[j] == nums[k].
The distance of a good tuple is abs(i - j) + abs(j - k) + abs(k - i), where abs(x) denotes the absolute value of x.
Return an integer denoting the minimum possible distance of a good tuple. If no good tuples exist, return -1.

 

Example 1:
Input: nums = [1,2,1,1,3]
Output: 6
Explanation:
The minimum distance is achieved by the good tuple (0, 2, 3).
(0, 2, 3) is a good tuple because nums[0] == nums[2] == nums[3] == 1. Its distance is abs(0 - 2) + abs(2 - 3) + abs(3 - 0) = 2 + 1 + 3 = 6.

Example 2:
Input: nums = [1,1,2,3,2,1,2]
Output: 8
Explanation:
The minimum distance is achieved by the good tuple (2, 4, 6).
(2, 4, 6) is a good tuple because nums[2] == nums[4] == nums[6] == 2. Its distance is abs(2 - 4) + abs(4 - 6) + abs(6 - 2) = 2 + 2 + 4 = 8.

Example 3:
Input: nums = [1]
Output: -1
Explanation:
There are no good tuples. Therefore, the answer is -1.

Constraints:
1 <= n == nums.length <= 105
1 <= nums[i] <= n

Approach:
  Distance simplifies to:
     ∣i−j∣+∣j−k∣+∣k−i∣=2×(k−i)(for sorted i<j<k)
  So we just need:
     minimize 2 * (k - i)
Meaning:
    For same value, pick 3 closest indices
    Only need consecutive triples
Optimal Approach (O(n))

Instead of storing all indices, we:
    Track last two occurrences for each value
    When we see a third → compute distance instantly

Implementation:
   class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        
        // store last two indices for each value
        int[] last1 = new int[n + 1]; // most recent
        int[] last2 = new int[n + 1]; // second most recent
        
        Arrays.fill(last1, -1);
        Arrays.fill(last2, -1);
        
        int ans = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            
            if (last2[val] != -1) {
                // we have 3 indices: last2, last1, i
                int dist = 2 * (i - last2[val]);
                ans = Math.min(ans, dist);
            }
            
            // shift indices
            last2[val] = last1[val];
            last1[val] = i;
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
