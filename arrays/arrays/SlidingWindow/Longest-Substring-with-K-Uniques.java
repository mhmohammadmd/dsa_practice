// You are given a string s consisting only lowercase alphabets and an integer k. Your task is to find the length of the longest substring that contains exactly k distinct characters.

// Note : If no such substring exists, return -1. 

// Examples:

// Input: s = "aabacbebebe", k = 3
// Output: 7
// Explanation: The longest substring with exactly 3 distinct characters is "cbebebe", which includes 'c', 'b', and 'e'.
// Input: s = "aaaa", k = 2
// Output: -1
// Explanation: There's no substring with 2 distinct characters.
// Input: s = "aabaaab", k = 2
// Output: 7
// Explanation: The entire string "aabaaab" has exactly 2 unique characters 'a' and 'b', making it the longest valid substring.
// Constraints:
// 1 ≤ s.size() ≤ 105
// 1 ≤ k ≤ 26

// Approach:
// Sliding Window

  class Solution {
    public int longestKSubstr(String s, int k) {
        // code here
         int left=0,right=0;
         int n=s.length();
         int maxi=-1;
         int count=0;
         int[] freq=new int[26];
         while(right<n){
             freq[s.charAt(right) - 'a']++;
             if(freq[s.charAt(right) - 'a']==1)count++;
             while(count>k){
                 freq[s.charAt(left) - 'a']--;
                 if(freq[s.charAt(left) - 'a']==0)count--;
                 left++;
             }
             if(count==k){
                 maxi=Math.max(maxi,right-left+1);
             }
             right++;
         }
         return maxi;
    }
}
