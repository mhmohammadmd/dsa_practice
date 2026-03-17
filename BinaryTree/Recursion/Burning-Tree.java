Given the root of a binary tree and a target node, determine the minimum time required to burn the entire tree if the target node is set on fire.
In one second, the fire spreads from a node to its left child, right child, and parent.
Note: The tree contains unique values.
Examples : 
Input: root = [1, 2, 3, 4, 5, 6, 7], target = 2
Output: 3
Explanation: Initially 2 is set to fire at 0 sec 
At 1 sec: Nodes 4, 5, 1 catches fire.
At 2 sec: Node 3 catches fire.
At 3 sec: Nodes 6, 7 catches fire.
It takes 3s to burn the complete tree.
Input: root = [1, 2, 3, 4, 5, N, 7, 8, N, N, 10], target = 10

Output: 5
Explanation: Initially 10 is set to fire at 0 sec 
At 1 sec: Node 5 catches fire.
At 2 sec: Node 2 catches fire.
At 3 sec: Nodes 1 and 4 catches fire.
At 4 sec: Node 3 and 8 catches fire.
At 5 sec: Node 7 catches fire.
It takes 5s to burn the complete tree.
Constraints:
1 ≤ number of nodes ≤ 105
1 ≤ node->data ≤ 105

Approach:
Recursion
  The fire spreads level by level from the target node. The total time to burn the entire tree is determined by the farthest node reached by the fire.
  In other words, the minimum time to burn the whole tree is simply the distance from the target node to the farthest node.
  This reduces the problem to finding the farthest node from the target.
  The idea is to find the parent of each node and recursively explore all paths from the target node to find the farthest node.
  The distance to this node gives the minimum time required to burn the entire tree.

  Solution:
  /*
class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}
*/

class Solution {
    static void setParent(Node root, Map<Integer, Node> parent) {
        if (root == null) return;
        if (root.left != null) {
            parent.put(root.left.data, root);
            setParent(root.left, parent);
        }
        if (root.right != null) {
            parent.put(root.right.data, root);
            setParent(root.right, parent);
        }
    }

    // Recursively find the target node
    static Node findTarget(Node root, int target) {
        if (root == null) return null;
        if (root.data == target) return root;
        Node left = findTarget(root.left, target);
        if (left != null) return left;
        return findTarget(root.right, target);
    }

    // DFS to find minimum time to burn tree from target
    static int dfs(Node node, Map<Integer, Boolean> visited, Map<Integer, Node> parent) {
        if (node == null || visited.getOrDefault(node.data, false)) return 0;
        visited.put(node.data, true);

        // dfs call for left and right child
        int left = dfs(node.left, visited, parent);
        int right = dfs(node.right, visited, parent);
        
        // dfs call for parent if parent is not visited
        int parTime = parent.containsKey(node.data) ? dfs(parent.get(node.data), visited, parent) : 0;

        return Math.max(Math.max(left, right), parTime) + 1;
    }

    public int minTime(Node root, int target) {
        // code here
         Map<Integer, Node> parent = new HashMap<>();
        setParent(root, parent);

        Node targetNode = findTarget(root, target);
        Map<Integer, Boolean> visited = new HashMap<>();

        return dfs(targetNode, visited, parent) - 1; 
    }
}




