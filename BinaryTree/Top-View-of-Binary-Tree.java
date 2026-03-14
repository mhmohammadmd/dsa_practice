// ou are given the root of a binary tree, and your task is to return its top view. The top view of a binary tree is the set of nodes visible when the tree is viewed from the top.

// Note:

// Return the nodes from the leftmost node to the rightmost node.
// If multiple nodes overlap at the same horizontal position, only the topmost (closest to the root) node is included in the view. 
// Examples:
// Input: root = [1, 2, 3]
// Output: [2, 1, 3]
// Explanation: The Green colored nodes represents the top view in the below Binary tree.
 
// Input: root = [10, 20, 30, 40, 60, 90, 100]
// Output: [40, 20, 10, 30, 100]
// Explanation: The Green colored nodes represents the top view in the below Binary tree.
// Constraints:
// 1 ≤ number of nodes ≤ 105
// 1 ≤ node->data ≤ 105

//   Approach:

// Given the root of a binary tree, find the top view of the tree.
// The top view of a binary tree represents the set of nodes visible when the tree is viewed from above.

// Each node in the tree has a horizontal distance (HD) from the root, which helps determine its position when viewed from the top:

// The root node has an HD of 0.
// The left child of a node has an HD equal to parent’s HD - 1.
// The right child of a node has an HD equal to parent’s HD + 1.
// Note:

// The result should contain the nodes visible from leftmost to rightmost and if two nodes have the same horizontal distance, only the uppermost (first encountered) node should be considered in the top view.

/*
class Node {
    int data;
    Node left, right;

    Node(int val) {
        this.data = val;
        this.left = null;
        this.right = null;
    }
}
*/
class Solution {
    public ArrayList<Integer> topView(Node root) {
        // code here
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null) return ans;

        // Queue for level order traversal storing node and horizontal distance
        Queue<NodeHd> q = new LinkedList<>();
        q.add(new NodeHd(root, 0));

        // Map to store first node at each horizontal distance
        Map<Integer, Integer> mp = new HashMap<>();
        int minHd = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            NodeHd temp = q.poll();
            Node node = temp.node;
            int hd = temp.hd;
            minHd = Math.min(minHd, hd);

            // store the first node encountered at this horizontal distance
            if (!mp.containsKey(hd)) {
                mp.put(hd, node.data);
            }

            if (node.left != null) q.add(new NodeHd(node.left, hd - 1));
            if (node.right != null) q.add(new NodeHd(node.right, hd + 1));
        }

        // convert map to list in order from leftmost to rightmost
        int maxHd = Integer.MIN_VALUE;
        for (int key : mp.keySet()) {
            maxHd = Math.max(maxHd, key);
        }

        for (int i = minHd; i <= maxHd; i++) {
            ans.add(mp.get(i));
        }

        return ans;
    }
}
