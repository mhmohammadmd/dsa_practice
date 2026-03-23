Given an directed graph with V vertices numbered from 0 to V-1 and E edges, represented as a 2D array edges[][],
where each entry edges[i] = [u, v] denotes an edge between vertices u and v. Each node has at most one outgoing edge.
Your task is to find the length of the longest cycle present in the graph. If no cycle exists, return -1.

Note: A cycle is a path that starts and ends at the same vertex.
Examples :
Input: V = 7, edges[][] = [[0, 5], [1, 0], [2, 4], [3, 1], [4, 6], [5, 6], [6, 3]]
Output: 5
Explanation: longest Cycle is 0->5->6->3->1->0

Input: V = 8, edges[][] = [[0, 1], [1, 2], [2, 3], [3, 0], [4, 1], [5, 4], [6, 2], [7, 6]]
Output: 4
Explanation: longest Cycle is 0->1->2->3->0
Constraints:
1 ≤ V, E ≤ 104
0 ≤ edges[i][0], edges[i][1] < V

Approach:
Each node has at most ONE outgoing edge
So the graph behaves like a collection of chains + cycles (similar to a functional graph).

We use DFS + visit tracking with timestamps:
visited[i] = 0 → not visited
visited[i] = 1 → currently in DFS path
visited[i] = 2 → fully processed

Additionally:
Keep a time[] array to record when a node was visited
If we revisit a node already in the current path → we found a cycle

For each node:
  Start DFS if not visited
Track:
  current path using visited
  visit time using time[]
If a cycle is found:
   cycle length = currentTime - time[that node]

Solution:

  class Solution {
    public int longestCycle(int V, int[][] edges) {
        // code here
        int[] adj = new int[V];
        
        // Initialize adjacency (since at most 1 outgoing edge)
        for (int i = 0; i < V; i++) adj[i] = -1;
        for (int[] e : edges) {
            adj[e[0]] = e[1];
        }
        
        int[] visited = new int[V]; // 0 = unvisited, 1 = visiting, 2 = visited
        int[] time = new int[V];
        
        int maxCycle = -1;
        int timer = 1;
        
        for (int i = 0; i < V; i++) {
            if (visited[i] == 0) {
                int curr = i;
                Map<Integer, Integer> map = new HashMap<>();
                
                while (curr != -1) {
                    if (visited[curr] == 1) {
                        // cycle found
                        int cycleLength = timer - map.get(curr);
                        maxCycle = Math.max(maxCycle, cycleLength);
                        break;
                    }
                    
                    if (visited[curr] == 2) {
                        break;
                    }
                    
                    visited[curr] = 1;
                    map.put(curr, timer++);
                    
                    curr = adj[curr];
                }
                
                // mark path as fully visited
                curr = i;
                while (curr != -1 && visited[curr] == 1) {
                    visited[curr] = 2;
                    curr = adj[curr];
                }
            }
        }
        
        return maxCycle;
    }
}
