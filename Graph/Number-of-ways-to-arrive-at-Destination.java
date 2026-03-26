You are given an undirected weighted graph with V vertices numbered from 0 to V-1 and E edges, represented as a 2D array edges[][], where edges[i] = [ui, vi, timei] means that there is an undirected edge between nodes ui and vi that takes timei minutes to reach.
Your task is to return in how many ways you can travel from node 0 to node V - 1 in the shortest amount of time.

Examples:
Input: V = 4, edges[][] = [[0, 1, 2], [1, 2, 3], [0, 3, 5], [1, 3, 3], [2, 3, 4]] 
Output: 2
Explanation: The shortest path from 0 to 3 is 5.
Two ways to reach 3 in 5 minutes are:
0 -> 3
0 -> 1 -> 3
  
Input: V = 6, edges[][] = [[0, 2, 3], [0, 4, 2], [0, 5, 7], [2, 3, 1], [2, 5, 5], [5, 3, 3], [5, 1, 4], [1, 4, 1], [4, 5, 5]] 
Output: 4
Explanation: The shortest path from 0 to 5 is 7.
Four ways to reach 5 in 7 minutes are:
0 -> 5
0 -> 4 -> 5
0 -> 4 -> 1 -> 5
0 -> 2 -> 3 -> 5
  
Constraints:
1 ≤ V ≤ 200
V - 1 ≤ edges.size() ≤ V * (V - 1) / 2
0 ≤ ui, vi ≤ V - 1
1 ≤ timei ≤ 105
ui != vi

Approach:
Use Dijkstra’s Algorithm to find the shortest time from node 0 to all nodes.
Along with distance, maintain a ways[] array:
      ways[i] = number of shortest ways to reach node i.
  
Key Logic:
  While running Dijkstra:
      For each neighbor:
        1. If new shorter path found:
             > Update distance
             > Copy ways
                  > ways[neighbor] = ways[current]
        2. If same shortest path found:
             > Add ways
                > ways[neighbor] += ways[current]

  Solution:
          class Solution {
    public int countPaths(int V, int[][] edges) {
        // code here
         // Adjacency list
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        
        for (int[] e : edges) {
            int u = e[0], v = e[1], t = e[2];
            adj.get(u).add(new int[]{v, t});
            adj.get(v).add(new int[]{u, t});
        }
        
        // Min heap -> {distance, node}
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        
        long[] dist = new long[V];
        Arrays.fill(dist, Long.MAX_VALUE);
        
        int[] ways = new int[V];
        
        dist[0] = 0;
        ways[0] = 1;
        
        pq.offer(new long[]{0, 0});
        
        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long d = curr[0];
            int node = (int) curr[1];
            
            if (d > dist[node]) continue;
            
            for (int[] nei : adj.get(node)) {
                int next = nei[0];
                long wt = nei[1];
                
                // Found shorter path
                if (d + wt < dist[next]) {
                    dist[next] = d + wt;
                    ways[next] = ways[node];
                    pq.offer(new long[]{dist[next], next});
                }
                // Found another shortest path
                else if (d + wt == dist[next]) {
                    ways[next] += ways[node];
                }
            }
        }
        
        return ways[V - 1];
    }
}
