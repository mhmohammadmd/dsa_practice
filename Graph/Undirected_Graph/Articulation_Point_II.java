You are given an undirected graph with V vertices and E edges. The graph is represented as a 2D array edges[][], where each element edges[i] = [u, v] indicates an undirected edge between vertices u and v.
Your task is to return all the articulation points (or cut vertices) in the graph.
An articulation point is a vertex whose removal, along with all its connected edges, increases the number of connected components in the graph.

Note: The graph may be disconnected, i.e., it may consist of more than one connected component.
If no such point exists, return {-1}.

Examples :
Input: V = 5, edges[][] = [[0, 1], [1, 4], [4, 3], [4, 2], [2, 3]]
Output: [1, 4]
Explanation: Removing the vertex 1 or 4 will disconnects the graph as-
   
Input: V = 4, edges[][] = [[0, 1], [0, 2]]
Output: [0]
Explanation: Removing the vertex 0 will increase the number of disconnected components to 3.  
Constraints:
1 ≤ V, E ≤ 104

Key Idea:
  We track for each node:
  tin[u] → time of insertion (DFS discovery time)
  low[u] → lowest reachable ancestor
Articulation Point Conditions:
For a node u:
   1. Root node (parent = -1)
      If it has more than 1 child → it is an articulation point
   2. Non-root node
      If there exists a child v such that:
        low[v] >= tin[u]
        u is an articulation point
Steps:
 1. Build adjacency list
 2. Run DFS for all components (graph may be disconnected)
 3. Maintain:
      visited[]
      tin[]
      low[]
 4. Track articulation points using a boolean array
Implementation:
  class Solution {
    
     static int timer;
       
    static ArrayList<Integer> articulationPoints(int V, int[][] edges) {
        // code here
       ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < V; i++) adj.add(new ArrayList<>());
        
        for(int[] e : edges){
            int u = e[0], v = e[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        
        // Step 2: Initialize arrays
        boolean[] visited = new boolean[V];
        int[] tin = new int[V];
        int[] low = new int[V];
        boolean[] isArticulation = new boolean[V];
        
        timer = 0;
        
        // Step 3: Run DFS for all components
        for(int i = 0; i < V; i++){
            if(!visited[i]){
                dfs(i, -1, visited, tin, low, isArticulation, adj);
            }
        }
        
        // Step 4: Collect result
        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0; i < V; i++){
            if(isArticulation[i]) result.add(i);
        }
        
        if(result.size() == 0){
            result.add(-1);
        }
        
        return result;
    }
    
    static void dfs(int u, int parent, boolean[] visited, int[] tin, int[] low,
                    boolean[] isArticulation, ArrayList<ArrayList<Integer>> adj){
        
        visited[u] = true;
        tin[u] = low[u] = timer++;
        
        int children = 0;
        
        for(int v : adj.get(u)){
            
            if(v == parent) continue;
            
            if(!visited[v]){
                dfs(v, u, visited, tin, low, isArticulation, adj);
                
                low[u] = Math.min(low[u], low[v]);
                
                // Condition for articulation (non-root)
                if(parent != -1 && low[v] >= tin[u]){
                    isArticulation[u] = true;
                }
                
                children++;
            }
            else{
                // Back edge
                low[u] = Math.min(low[u], tin[v]);
            }
        }
        
        // Root node condition
        if(parent == -1 && children > 1){
            isArticulation[u] = true;
        } 
    }
}
