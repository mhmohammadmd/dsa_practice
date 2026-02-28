// Given two sorted arrays arr1[] and arr2[] of size n and m and a number x, find the pair whose sum is closest to x and the pair has an element from each array. In the case of multiple closest pairs return any one of them.

// Note : In the driver code, the absolute difference between the sum of the closest pair and x is printed.

// Examples:

// Input : arr1[] = [1, 4, 5, 7], arr2[] = [10, 20, 30, 40], x = 32
// Output : [1, 30]
// Explanation:The closest pair whose sum is closest to 32 is [1, 30] = 31.
// Input : arr1[] = [1, 4, 5, 7], arr2[] = [10, 20, 30, 40], x = 50 
// Output : [7, 40] 
// Explanation: The closest pair whose sum is closest to 50 is [7, 40] = 47.
// Constraints:
// 1 ≤ arr1.size(), arr2.size() ≤ 105
// 1 ≤ arr1[i], arr2[i] ≤ 109
// 1 ≤ x ≤ 109

class Solution {
    public static ArrayList<Integer> findClosestPair(int arr1[], int arr2[], int x) {
        // code here
        int n = arr1.length;
        int m = arr2.length;

        int l = 0, r = m - 1;
        int diff = Integer.MAX_VALUE;
        ArrayList<Integer> result = new ArrayList<>();

        // Traverse both arrays using two pointers
        while (l < n && r >= 0)
        {
            int sum = arr1[l] + arr2[r];
            int currDiff = Math.abs(sum - x);

            // Update result if closer pair is found
            if (currDiff < diff)
            {
                diff = currDiff;
                result.clear();
                result.add(arr1[l]);
                result.add(arr2[r]);
            }

            // Move pointers to bring sum closer to x
            if (sum > x)
                r--;
            else
                l++;
        }

        return result;
    }
}
