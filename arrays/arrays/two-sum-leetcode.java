/*
Added Two Sum solution using HashMap (O(n) time)
Problem: Two Sum
Platform: LeetCode
Topic: Arrays / Hashing

Approach:
Use a HashMap to store visited elements.
For each element, check whether (target - current element)
exists in the map.

Time Complexity: O(n)
Space Complexity: O(n)
*/

import java.util.HashMap;
import java.util.Map;
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }

            map.put(nums[i], i);
        }

        return new int[] {};
    }
}
