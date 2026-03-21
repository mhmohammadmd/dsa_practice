You are given an integer array arr[] containing distinct elements.
Your task is to return an array where the ith element denotes the number of unique BSTs formed when arr[i] is chosen as the root.
Examples :
Input: arr[] = [2, 1, 3]
Output: [1, 2, 2]
Explanation: 
4
Input: arr[] = [2, 1]
Ouput: [1, 1]
Constraints:
1 ≤ arr.size() ≤ 6
1 ≤ arr[i] ≤ 15
Using Catalan numbers - O(n log(n)) Time and O(n) Space
If we pick node k as the root:
All nodes with values less than k must go to the left subtree.
All nodes with values greater than k must go to the right subtree.
The left and right subtrees themselves each must form valid BSTs, and their counts depend on how many nodes they contain.
Thus,
No. of BSTs rooted at k = No. of BSTs with (k1) nodes on the left * No. of BSTs with (k2) nodes on the right = T(k1) * T(k2)
such that T(i) denotes the number of BSTs with i distinct values.
where:
k1​ = number of elements less than k,
k2​ = number of elements greater than k, and
k1+k2=n−1
For a sorted list of nodes a1,a2,…,an​, each element acts as the root.
Thus, the total number of BSTs is:
  T(n)=∑(i=1 to n) T(i−1)×T(n−i)
This is because for element at index i( 1 ≤ i ≤ n), there are i-1 elements on left and n-i elements on right.
and T(0) = 1, T(1) = 1, as there is only one BST with either 0 or 1 element.
This recurrence is the same as that of the Catalan numbers, so the number of BSTs with n distinct nodes is Cn​.
  Cn=∑(i=0 to n−1) Ci×C(n−1−i)
and, C0=1, C1 = 1.
Therefore, using this we can say that the number of BSTs with element k as the root node are:
C(i) * C(n-i-1) 
where i is the the number of nodes with values less than k.

class Solution {
    // Precompute factorials up to 2*n
    static ArrayList<Integer> computeFact(int num) {
        ArrayList<Integer> fact = new ArrayList<>(Collections.nCopies(num + 1, 1));
        for (int i = 1; i <= num; i++)
            fact.set(i, fact.get(i - 1) * i);
        return fact;
    }
    
     // Compute nth Catalan number using precomputed factorials
    static int catalan(int n, ArrayList<Integer> fact) {
        return fact.get(2 * n) / (fact.get(n) * fact.get(n + 1));
    }
    public ArrayList<Integer> countBSTs(int[] arr) {
        // Code here
        int n = arr.length;
        int[][] sorted = new int[n][2];

        // Pair each element with its index and sort by value
        for (int i = 0; i < n; i++) {
            sorted[i][0] = arr[i];
            sorted[i][1] = i;
        }
        Arrays.sort(sorted, Comparator.comparingInt(a -> a[0]));

        ArrayList<Integer> fact = computeFact(2 * n);

        ArrayList<Integer> numBSTs = new ArrayList<>(Collections.nCopies(n, 0));

        // Compute BST count for each element as root
        for (int i = 0; i < n; i++) {
            int j = sorted[i][1];
            numBSTs.set(j, catalan(i, fact) * catalan(n - i - 1, fact));
        }

        return numBSTs;
    }
}
