You are given two arrays men[] and women[], each of length n, where:

men[i] represents the preference list of the i-th man, ranking all women in order of preference.
women[i] represents the preference list of the i-th woman, ranking all men in order of preference.
The task is to form n pairs (marriages) such that:

Each man is matched with exactly one woman, and each woman is matched with exactly one man.
The matching is stable, meaning there is no pair (man, woman) who both prefer each other over their current partners.
It is guaranteed that a stable matching always exists. Return the stable matching from the men’s perspective, i.e., the one where men are considered for the final output.
Return an array result[] of size n, where result[i] denotes the index (0-based) of the woman matched with the i-th man.

Examples: 

Input: n = 2, men[] = [[0, 1], [0, 1], women[] = [[0, 1], [0, 1]]
Output: [0, 1]
Explanation:
Man 0 is married to Woman 0 (his first choice and her first choice).
Man 1 is married to Woman 1 (his second choice and her second choice).
Input: n = 3, men[] = [[0, 1, 2], [0, 1, 2], [0, 1, 2]], women[] = [[2, 1, 0], [2, 1, 0], [2, 1, 0]]
Output: [2, 1, 0]
Explanation:
Man 0 is married to Woman 2 (his third choice and her third choice).
Man 1 is married to Woman 1 (his second choice and her second choice).
Man 2 is married to Woman 0 (his first choice and her first choice).
Constraints: 
2 ≤ n ≤ 103
0 ≤ men[i] ≤ n - 1
0 ≤ women[i] ≤ n - 1
Approach:
Gale-Shapley Algorithm - O(n^2) Time and O(n^2) Space
It is always possible to form stable marriages from lists of preferences to find a stable matching. Iterate through all free men. Each free man proposes to women one by one according to his preference list. If a woman is free, she becomes engaged to him. If she is already engaged, she compares her current partner with the new proposer and chooses the one she prefers more. If she prefers the new proposer, the previous engagement is broken and the rejected man becomes free again. This process continues until all men are engaged. The algorithm guarantees a stable matching. 

Finding the Perfect Match

Initialize all men and women to free
while there exist a free man m who still has a woman w to propose to 
{
    w = highest-ranked woman on m's preference list to whom he has not yet proposed
 if w is free
       (m, w) become engaged
 else let m' be the man currently engaged to w
 if w prefers m to m'
          (m, w) become engaged
           m' becomes free
 else
          w remains engaged with m'
}.  

 Implementation:
      class Solution {
    
    // Check if woman prefers m over current partner m1
    public static boolean prefers(int[][] women, int w, int m, int m1) {
        for (int i = 0; i < women[w].length; i++) {
            if (women[w][i] == m)
                return true;
            if (women[w][i] == m1)
                return false;
        }
        return false;
    }

    public int[] stableMarriage(int[][] men, int[][] women) {
        // code here
        int n = men.length;

        int[] wPartner = new int[n];
        
        // women's current partners
        for (int i = 0; i < n; i++) wPartner[i] = -1; 
        int[] mPartner = new int[n];
        
        // man's current partners
        for (int i = 0; i < n; i++) mPartner[i] = -1; 
        
        // next proposal index
        int[] next = new int[n]; 
        boolean[] freeMan = new boolean[n];
        
        // Free Men
        for (int i = 0; i < n; i++) freeMan[i] = true; 

        int freeCount = n;

        while (freeCount > 0) {
            int m;
            for (m = 0; m < n; m++)
                if (freeMan[m])
                    break;

            int w = men[m][next[m]];
            next[m]++;

            // If w is free
            if (wPartner[w] == -1) {
                wPartner[w] = m;
                mPartner[m] = w;
                freeMan[m] = false;
                freeCount--;
            }
            else {
                int m1 = wPartner[w];

                // If w prefers m over current partner
                if (prefers(women, w, m, m1)) {
                    wPartner[w] = m;
                    mPartner[m] = w;

                    freeMan[m] = false;
                    freeMan[m1] = true;
                }
            }
        }

        return mPartner;
    }
}
