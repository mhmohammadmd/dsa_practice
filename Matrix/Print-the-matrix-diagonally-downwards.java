Give a n * n square matrix mat[][], return all the elements of its anti-diagonals from top to bottom.
Examples :
Input: n = 2, mat[][] = [[1, 2],
                        [3, 4]]
Output: [1, 2, 3, 4]
Explanation: 

Input: n = 3, mat[][] = [[1, 2, 3],
                       [4, 5, 6],
                       [7, 8, 9]]
Output: [1, 2, 4, 3, 5, 7, 6, 8, 9]
Explanation: 

Constraints:
1 ≤ n ≤ 103
0 ≤ mat[i][j] ≤ 106
Key Idea
For any element mat[i][j], all elements on the same anti-diagonal have:
        i+j=constant
So instead of thinking row-wise or column-wise, we group elements by the value of i + j.

Approach
Loop over all possible sums d = 0 → 2*(n-1)
For each d, collect elements where i + j = d
To maintain order:
Start row index i from max(0, d - (n-1))
End at min(n-1, d)
Compute j = d - i

class Solution {
    static ArrayList<Integer> diagView(int mat[][]) {
        // code here
        int n = mat.length;
        ArrayList<Integer> result = new ArrayList<>();
        
        // Traverse all diagonals
        for (int d = 0; d <= 2 * (n - 1); d++) {
            
            // Row index range
            int start = Math.max(0, d - (n - 1));
            int end = Math.min(n - 1, d);
            
            for (int i = start; i <= end; i++) {
                int j = d - i;
                result.add(mat[i][j]);
            }
        }
        
        return result;
    }
}
