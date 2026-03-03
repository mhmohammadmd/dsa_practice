// Given an array arr[] consisting of positive integers, your task is to find the length of the longest subarray that contains at most two distinct integers.

// Examples:

// Input: arr[] = [2, 1, 2]
// Output: 3
// Explanation: The entire array [2, 1, 2] contains at most two distinct integers (2 and 1). Hence, the length of the longest subarray is 3.
// Input: arr[] = [3, 1, 2, 2, 2, 2]
// Output: 5
// Explanation: The longest subarray containing at most two distinct integers is [1, 2, 2, 2, 2], which has a length of 5.
// Constraints:
// 1 ≤ arr.size() ≤ 105
// 1 ≤ arr[i] ≤ 105

Approach:
TwoPointer,SlidingWindow,HashMap

class Solution {
    public int totalElements(int[] arr) {
        // code here
        Map<Integer,Integer> mp=new HashMap<>();
        int l=0,r=0,n=arr.length;
        int size=0;
        while(r<n){
            mp.put(arr[r],mp.getOrDefault(arr[r],0)+1);
            while(mp.size()>2){
                mp.put(arr[l],mp.get(arr[l])-1);
                if(mp.get(arr[l])==0){
                    mp.remove(arr[l]);
                    
                }
                l++;
            }
            size=Math.max(size,r-l+1);
            r++;
        }
        return size;
    }
}
