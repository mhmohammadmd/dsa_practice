You are given an undirected graph, which has tree characteristics with V vertices numbered from 0 to V-1 and E edges, represented as a 2D array edges[][], where each element edges[i] = [u, v] represents an edge from vertex u to v.
You can choose any vertex as the root of the tree. Your task is to find all the vertices that, when chosen as the root, result in the minimum possible height of the tree.
Note: The height of a rooted tree is defined as the maximum number of edges on the path from the root to any leaf node.

Examples: 
Input: V = 5, E = 4, edges[][] = [[0, 2], [1, 2], [2, 3], [3, 4]]  
Output: [2, 3]
Explanation: If we choose vertices 2 or 3 as the root, the resulting tree has the minimum possible height, which is 2.
  
Input: V = 4, E = 3, edges[][] = [[0, 1], [0, 2], [0, 3]]  
Output: [0]
Explanation: Only vertex 0 as root gives the minimum possible height, which is 1.
  
Constraints:
1 ≤ V ≤ 105
0 ≤ E ≤ V-1
0 ≤ edges[i][0], edges[i][1] < V

Approach:
  Instead of trying every node as root (which is costly), we use a topological trimming approach (like peeling an onion):
  A tree’s center(s) give minimum height.
  Remove all leaf nodes (degree = 1) level by level.
  Continue until ≤ 2 nodes remain → these are the answers.
Algo:
  Build adjacency list.
  Track degree of each node.
  Add all leaf nodes (degree = 1) to queue.
  Remove them layer by layer.
  Stop when remaining nodes ≤ 2.
Solution:
   class Solution {
    public ArrayList<Integer> minHeightRoot(int V, int[][] edges) {
        // Code here
            ArrayList<Integer> result = new ArrayList<>();
        
        // Edge case
        if (V == 1) {
            result.add(0);
            return result;
        }

        // Step 1: Build graph
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int[] degree = new int[V];
        
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
            degree[u]++;
            degree[v]++;
        }

        // Step 2: Add initial leaves
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (degree[i] == 1) {
                queue.offer(i);
            }
        }

        // Step 3: Trim leaves
        int remainingNodes = V;

        while (remainingNodes > 2) {
            int size = queue.size();
            remainingNodes -= size;

            for (int i = 0; i < size; i++) {
                int leaf = queue.poll();

                for (int neighbor : adj.get(leaf)) {
                    degree[neighbor]--;
                    if (degree[neighbor] == 1) {
                        queue.offer(neighbor);
                    }
                }
            }
        }

        // Step 4: Remaining nodes are answer
        result.addAll(queue);
        return result;
    }
}
