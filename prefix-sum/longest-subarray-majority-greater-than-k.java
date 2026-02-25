/*
Problem: Longest Subarray with Majority Greater than K
Platform: GFG
Topic: Prefix Sum / Hashing / Array Transformation

Problem Summary:
Find the length of the longest subarray where the count of 
elements greater than K is strictly more than the count 
of elements less than or equal to K.

Approach:
1. Transform the array:
   - Replace elements > K with +1
   - Replace elements <= K with -1
2. The problem reduces to:
   Find the longest subarray with positive prefix sum.
3. Use prefix sum and hashmap to track earliest occurrences
   for efficient lookup.

Time Complexity: O(n)
Space Complexity: O(n)

Author: Mohammad
*/

class Solution {
    public int longestSubarray(int[] arr, int k) {
        // Code Here
        int ans=0,sum=0;
        int n=arr.length;
        Map<Integer,Integer> mp=new HashMap<>();
        for(int i=0;i<n;i++){
            if(arr[i]<=k)sum--;
            else sum++;
            if(sum>0)ans=i+1;
            else{
                if(mp.containsKey(sum-1)){
                    ans=Math.max(ans,i-mp.get(sum-1));
                }
            }
            if(!mp.containsKey(sum)){
                mp.put(sum,i);
            }
        }
        return ans;
    }
}
// Added Longest Subarray with Majority > K using prefix sum transformation (O(n))
