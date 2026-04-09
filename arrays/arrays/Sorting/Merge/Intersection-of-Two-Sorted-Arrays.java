Given two sorted arrays a[] and b[], where each array may contain duplicate elements, return the elements in the intersection of the two arrays in sorted order.
Note: Intersection of two arrays can be defined as the set containing distinct common elements that are present in both of the arrays.
Examples:
Input: a[] = [1, 1, 2, 2, 2, 4], b[] = [2, 2, 4, 4]
Output: [2, 4]
Explanation: Distinct common elements in both the arrays are: 2 and 4.
Input: a[] = [1, 2], b[] = [3, 4]
Output: []
Explanation: No common elements.
Input: a[] = [1, 2, 3], b[] = [1, 2, 3]
Output: [1, 2, 3]
Explanation: All elements are common.
Constraints:
1 ≤ a.size(), b.size() ≤ 105
-109 ≤ a[i], b[i] ≤ 109

Approach:
  Using Merge Step of Merge Sort - O(n+m) Time and O(1) Space
  The idea is based one merge function to merge two sorted arrays.
  We simultaneously traverse both a[] and b[] from the left side. While traversing, we avoid duplicates in a[]. We do not need to do it for b[] because once we have a match, we move ahead in a[] and b[] both.
  If current elements are not same, we skip the smaller of the two. If current element of a[] is smaller, we move ahead in a[] and if current of b[] is smaller, we move ahead in b[].
  Else (If same), we add one occurrence of the current element to the result and move ahead in both a[] and b[].

class Solution {
    ArrayList<Integer> intersection(int[] a, int[] b) {
        // code here
         ArrayList<Integer> res = new ArrayList<>();
        int m = a.length;
        int n = b.length;

        int i = 0, j = 0;
        while (i < m && j < n) {

            // Skip duplicate elements in the first array
            if (i > 0 && a[i - 1] == a[i]) {
                i++;
                continue;
            }

            // Skip the smaller
            if (a[i] < b[j]) {
                i++;
            } else if (a[i] > b[j]) {
                j++;
            }

            // If equal, add to result and move both
            else {
                res.add(a[i]);
                i++;
                j++;
            }
        }

        return res;
    }
}
