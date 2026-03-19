You're given a binary tree. Your task is to find the size of the largest subtree within this binary tree that also satisfies the properties of a Binary Search Tree (BST).
The size of a subtree is defined as the number of nodes it contains.

Note: A subtree of the binary tree is considered a BST if for every node in that subtree, the left child is less than the node,
      and the right child is greater than the node, without any duplicate values in the subtree.

Examples :
Input: root = [5, 2, 4, 1, 3]
Root-to-leaf-path-sum-equal-to-a-given-number-copy
Output: 3
Explanation:The following sub-tree is a BST of size 3
Balance-a-Binary-Search-Tree-3-copy
  
Input: root = [6, 7, 3, N, 2, 2, 4]
Output: 3
Explanation: The following sub-tree is a BST of size 3:

Constraints:
1 ≤ number of nodes ≤ 105
1 ≤ node->data ≤ 105

Approach:
Using Binary Search Tree Property - O(n) Time and O(n) Space
Intiution:
To determine if a subtree is a valid BST, we know that all values in the left subtree must be smaller than the root,
and all values in the right subtree must be greater than the root. If we track the minimum and maximum values of each subtree, we can easily validate a Binary tree.
To keep track of this information and size of valid BST, we create a custom class with the following attributes:
mini = Minimum value in the subtree
maxi = Maximum value in the subtree
mxSz = Size of the largest BST in the subtree
This allows us to efficiently compute the largest BST while recursively traversing the binary tree.

Pseudo-code Idea:
If the root is NULL, then return (mini = + Infinity , maxi = - Infinity , size = 0) because an empty tree is a valid BST of size 0.
Otherwise, make a recursive call for the left and right subtrees to get their (mini, maxi, size) values.
If (left -> maxi) < root -> data (< right -> mini), then the current subtree is a valid BST.
return min = min(left -> mini, root -> data), max = max(right -> maxi, root -> data) and size = 1 + left -> size + right -> size .
if this condition fails, it means the current subtree is not a valid BST. So, return (mini = - Infinity, maxi = + Infinity, size = max(left -> size, right -> size)).

Solution:
// class Node
// {
//     int data;
//     Node left, right;

//     public Node(int d)
//     {
//         data = d;
//         left = right = null;
//     }
// }
class BSTInfo {
    int mini;
    int maxi;
    int mxSz;

    BSTInfo(int mn, int mx, int sz) {
        mini = mn;
        maxi = mx;
        mxSz = sz;
    }
}


class Solution {

    // Return the size of the largest sub-tree which is also a BST
    static BSTInfo largestBSTBT(Node root) {
        if (root == null)
            return new BSTInfo(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);

        BSTInfo left = largestBSTBT(root.left);
        BSTInfo right = largestBSTBT(root.right);

        // Check if the current subtree is a BST
        if (left.maxi < root.data && right.mini > root.data) {
            return new BSTInfo(Math.min(left.mini, root.data),
                               Math.max(right.maxi, root.data),
                               1 + left.mxSz + right.mxSz);
        }

        return new BSTInfo(Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left.mxSz, right.mxSz));
    }

    static int largestBst(Node root) {
        // Write your code here
         return largestBSTBT(root).mxSz;
    }
}
