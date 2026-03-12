// Given a binary array arr[], Find minimum number of operations to convert all 0s to 1s. In one operation, we can select a subarray (window) of length k and flip all its bits. If it is impossible, return -1.
// Examples:
// Input: arr[] = [1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1], k = 2
// Output: 4 
// Explanation: 4 operations are required to convert all 0s to 1s:
// Flip arr[2...3], so arr[] becomes [1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1]
// Flip arr[4...5], so arr[] becomes [1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1]
// Flip arr[5...6], so  arr[] becomes [1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1]
// Flip arr[6...7], so arr[] becomes [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
// Input: arr[] = [0, 0, 1, 1, 1, 0, 0], k = 3
// Output: -1
// Explanation: It is impossible to convert all elements to 1s by performing any number of operations.
//   Approach:
// We scan the array from left to right, and whenever we see a 0, we flip the next k elements so that this 0 becomes 1. 
// We keep doing this until we reach the last possible starting position.
// If at the end any 0 remains, it means it is impossible to make all elements 1; otherwise, the number of flips we made is the answer.
// tart iterating from index 0.
// Whenever you find arr[i] == 0, flip the next k elements by XOR with 1.
// -> This ensures the current 0 becomes 1.
// Keep a counter (res) to track the number of flips performed.
// At the end, check the last (k - 1) elements. If any of them is 0, return -1 (not possible).Otherwise, return the total flips.
class Solution {
    public int kBitFlips(int[] arr, int k) {
        // code here
        int n = arr.length; 
        int res = 0, flag = 0;
        Queue<Integer> q = new LinkedList<>(); 

        for (int i = 0; i < n; i++) {
            
            if(i >= k)
                flag ^= q.poll();
            
            // If flag = 1, then flip the current index
            if(flag == 1)
                arr[i] ^= 1;
            
            // Finally, if arr[i] = 0, then we need to flip
            if(arr[i] == 0) {
                
                // Check if k elements are left
                if(i + k > n) 
                    return -1;
                
                res += 1;
                
                // Flip flag so that upcoming elements are also flipped
                flag ^= 1;
                
                // If we flip, push 1 to the queue
                q.offer(1);
            }
            else {
                
                // If we don't flip, push 0 to queue
                q.offer(0);
            }
        }

        return res; 
    }
}
