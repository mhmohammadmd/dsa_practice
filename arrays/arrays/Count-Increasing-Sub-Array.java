Given an array arr[] of integers, count the number of subarrays in arr[] which are strictly increasing with size greater or equal to 2. A subarray is a contiguous part of array. A subarray is strictly increasing if each element is greater then it's previous element if it exists.

Examples:
Input: arr[] = [1, 4, 5, 3, 7, 9]
Output: 6
Explanation: The strictly increasing subarrays are: [1, 4], [1, 4, 5], [4, 5], [3, 7], [3, 7, 9], [7, 9]

Input: arr[] = [1, 3, 3, 2, 3, 5]
Output: 4
Explanation: Increasing Subarrays of size greater or equal to 2 are : {1, 3}, {2, 3}, {2, 3, 5}, {3, 5}. So answer for this test case is 4.

Input: arr[] = [2, 2, 2, 2]
Output: 0
Explanation: No strictly increasing subarray exists.
  
Constraints:
1 ≤ arr.size() ≤ 105
1 ≤ arr[i] ≤ 107
Approach:
  Using Subarray Count Formula - O(n) Time and O(1) Space
  We can do only with a single pass. Instead of checking every subarray explicitly, we track the length of increasing segments using len.
  When a decreasing element is encountered, we use the formula (len * (len - 1)) / 2 to count subarrays formed by the segment and then reset len.
  Finally, we add the remaining subarrays after the loop ends.

Steps to implement the above idea:
  >> Initialize count to store the number of strictly increasing subarrays and len to track the length of increasing sequences.
  >> Iterate through the array starting from index 1, comparing each element with its previous element to check for increasing order.
  >> If the current element is greater than the previous, increment len as it extends the increasing subarray.
  >> If the current element breaks the increasing sequence, update count using the formula (len*(len-1))/2 and reset len to 1.
  >> Continue iterating until the end of the array, applying the same logic for each increasing and non-increasing sequence.
  >> After the loop, add the remaining subarrays count using (len * (len - 1)) / 2 to include the last segment.
  >> Finally, return count, which holds the total number of strictly increasing subarrays in the given array.

Implementation:
  class Solution {
    public int countIncreasing(int[] arr) {
        // code here
         int n = arr.length;
        int count = 0;
        int len = 1;

        // Iterate through the array
        for (int i = 1; i < n; i++) {
            
            // If current element is greater than 
            // previous, increase len
            if (arr[i] > arr[i - 1]) {
                len++;
            } 
            
            else {
                
                // Add subarrays count and reset len
                count += (len * (len - 1)) / 2;
                len = 1;
            }
        }

        // Add remaining subarrays count
        count += (len * (len - 1)) / 2;

        return count;
    }
}
