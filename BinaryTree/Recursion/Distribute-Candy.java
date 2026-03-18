You are given the root of a binary tree with n nodes, where each node contains a certain number of candies, and the total number of candies across all nodes is n.
In one move, you can select any two adjacent nodes and transfer one candy from one node to the other. The transfer can occur between a parent and child in either direction.
The task is to determine the minimum number of moves required to ensure that every node in the tree has exactly one candy.
Note: The testcases are framed such that it is always possible to achieve a configuration in which every node has exactly one candy, after some moves.

Examples:
Input: root = [5, 0, 0, N, N, 0, 0]  
Output: 6
Explanation:
Move 1 candy from root to left child
Move 1 candy from root to right child
Move 1 candy from right child to root->right->left node
Move 1 candy from root to right child
Move 1 candy from right child to root->right->right node
Move 1 candy from root to right child
so, total 6 moves required.
  
Input: root = [2, 0, 0, N, N, 3, 0] 
Output: 4
Explanation:
Move 1 candy from root to left child
Move 1 candy from root->right->left node to root->right node
Move 1 candy from root->right node to root->right->right node
Move 1 candy from root->right->left node to root->right node
so, total 4 moves required.
Constraints:
1 ≤ n ≤ 3*103
0 ≤ Node->data ≤ n
The sum of all Node->data = n
Expected Complexities
Time Complexity: O(n)
Auxiliary Space: O(h)

Approach:
The idea is to use recursion to traverse the tree from leaf to root and consecutively balance all of the nodes. To balance a node, the number of candy at that node must be 1. 
There can be two cases:  
If a node needs candies, if the node of the tree has 0 candies then we should push a candy from its parent onto the node.
If the node has more than 1 candy. ex. 4 candies (an excess of 3), then we should push 3 candies off the node to its parent.
So, the total number of moves from that leaf to or from its parent is excess = abs(numCandies - 1). Once a node is balanced, we never have to consider this node again in the rest of our calculation.

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
    
    static int distCandyUtil(Node root, int[] ans) {

        if (root == null)
            return 0;

        // Traverse left subtree
        int l = distCandyUtil(root.left, ans);

        // Traverse right subtree
        int r = distCandyUtil(root.right, ans);

        ans[0] += Math.abs(l) + Math.abs(r);

        // Return number of moves to balance
        // current node
        return root.data + l + r - 1;
    }
    public int distCandy(Node root) {
        // code here
        int[] ans = new int[1];

        distCandyUtil(root, ans);

        return ans[0];
    }
}
