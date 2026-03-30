Given a 2D array houses[][], consisting of n 2D coordinates {x, y} where each coordinate represents the location of each house, the task is to find the minimum cost to connect all the houses of the city.
The cost of connecting two houses is the Manhattan Distance between the two points (xi, yi) and (xj, yj) i.e., |xi – xj| + |yi – yj|, where |p| denotes the absolute value of p.

Examples :
Input: n = 5 houses[][] = [[0, 7], [0, 9], [20, 7], [30, 7], [40, 70]]
Output: 105
Explanation:
Connect house 1 (0, 7) and house 2 (0, 9) with cost = 2
Connect house 1 (0, 7) and house 3 (20, 7) with cost = 20
Connect house 3 (20, 7) with house 4 (30, 7) with cost = 10 
At last, connect house 4 (30, 7) with house 5 (40, 70) with cost 73.
All the houses are connected now.
The overall minimum cost is 2 + 10 + 20 + 73 = 105.

Input: n = 4 houses[][] = [[0, 0], [1, 1], [1, 3], [3, 0]]
Output: 7
Explanation: 
Connect house 1 (0, 0) with house 2 (1, 1) with cost = 2
Connect house 2 (1, 1) with house 3 (1, 3) with cost = 2 
Connect house 1 (0, 0) with house 4 (3, 0) with cost = 3 
The overall minimum cost is 3 + 2 + 2 = 7.

Constraint:
1 ≤ n ≤ 103
0 ≤ houses[i][j] ≤ 103

Approach:
Using Kruskal's Algorithm - Time O(n2 × log(n)) and Space O(n2)
The idea is to treat each house as a node in a complete weighted graph, where the weight between any two houses is given by their Manhattan distance, representing the connection cost.
After generating all such edges, we sort them in non-decreasing order and apply Kruskal’s algorithm to build the Minimum Spanning Tree (MST). 
During this process, a Disjoint Set Union (DSU) structure with path compression and union by rank is used to efficiently track connected components and ensure that no cycles are formed while selecting edges.


Impplementation:
  class DSU {
    private int[] parent;
    private int[] rank;

    public DSU(int n) {
        parent = new int[n];
        rank = new int[n];
        Arrays.fill(parent, -1);
        Arrays.fill(rank, 1);
    }

    // Find function
    public int find(int i) {
        if (parent[i] == -1)
            return i;
        return parent[i] = find(parent[i]);
    }

    // Union function
    public void unite(int x, int y) {
        int s1 = find(x);
        int s2 = find(y);

        if (s1 != s2) {
            if (rank[s1] < rank[s2]) {
                parent[s1] = s2;
            } else if (rank[s1] > rank[s2]) {
                parent[s2] = s1;
            } else {
                parent[s2] = s1;
                rank[s1] += 1;
            }
        }
    }
}

class Solution {

    public int minCost(int[][] houses) {
        // code here
        int n = houses.length;

        // Create edge list with n nodes
        List<int[]> edgeList = new ArrayList<>();

        // Add all possible edges
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int cost = Math.abs(houses[i][0] - houses[j][0]) +
                           Math.abs(houses[i][1] - houses[j][1]);
                edgeList.add(new int[]{cost, i, j});
            }
        }

        // Sort all edges
        edgeList.sort(Comparator.comparingInt(a -> a[0]));

        // Initialize the DSU
        DSU s = new DSU(n);

        int ans = 0, count = 0;

        for (int[] edge : edgeList) {
            int w = edge[0];
            int x = edge[1];
            int y = edge[2];

            // Take this edge in MST if it does
            // not form a cycle
            if (s.find(x) != s.find(y)) {
                s.unite(x, y);
                ans += w;
                count++;
            }

            if (count == n - 1) {
                break;
            }
        }

        return ans;
    }
}
