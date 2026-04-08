Given an array arr[] consisting of only 0's and 1's. Modify the array in-place to segregate 0s onto the left side and 1s onto the right side of the array.
Examples :
Input: arr[] = [0, 1, 0, 1, 0, 0, 1, 1, 1, 0]
Output: [0, 0, 0, 0, 0, 1, 1, 1, 1, 1]
Explanation:  After segregation, all the 0's are on the left and 1's are on the right. Modified array will be [0, 0, 0, 0, 0, 1, 1, 1, 1, 1].
Input: arr[] = [1, 1]
Output: [1, 1]
Explanation: There are no 0s in the given array, so the modified array is [1, 1]
Constraints:
1 ≤ arr.size() ≤ 105
0 ≤ arr[i] ≤ 1
Approach:
Using Hoare's Partition Algorithm - O(n) Time and O(1) Space
Use two pointers i and j, where i starts from the beginning (initialized to -1) and j starts from the end (initialized to n).
In each iteration, move i forward until you find an element that is not 0, and move j backward until you find an element that is not 1.
If i is still less than j, swap the elements at these positions. Repeat this process until i becomes greater than or equal to j. This way, all 0s move to the left side and all 1s move to the right side of the array.

Implementation:
class Solution {
    void segregate0and1(int[] arr) {
        // code here
        int n = arr.length;

        // Initialize lo and hi indexes
        int lo = -1, hi = n;

        while (true) {

            // Increment lo index while we see 0 at lo
            do {
                lo++;
            } while (lo < n && arr[lo] == 0);

            // Decrement hi index while we see 1 at hi
            do {
                hi--;
            } while (hi >= 0 && arr[hi] == 1);

            // Break when pointers cross
            if (lo >= hi)
                break;

            // Swap elements
            int temp = arr[lo];
            arr[lo] = arr[hi];
            arr[hi] = temp;
    }
    }
}
