// Given the root of a binary tree and an integer k, determine the number of downward-only paths where the sum of the node values in the path equals k.

// Note: A path can start and end at any node within the tree but must always move downward (from parent to child).

// Examples:
// Input: root = [8, 4, 5, 3, 2, N, 2, 3, -2, N, 1], k = 7
// Output: 3
// Explanation: The following paths sum to k
 
// Input: root = [1, 2, 3], k = 3

// Output: 2 
// Explanation: The following paths sum to k

// Constraints:
// 1 ≤ number of nodes ≤ 104
// -100 ≤ node value ≤ 100
// -109 ≤ k ≤ 109
// Approach:
//   Using Prefix Sum Technique - O(n) Time and O(n) Space
//   We can use prefix sums with a hashmap to efficiently track the sum of paths in the binary tree.
//   The prefix sum up to a node is the sum of all node values from the root to that node. 
//   We traverse the tree using recursion and by storing the prefix sums of current path from root in a hashmap,
//   we can quickly find if there are any sub-paths that sum to the target value k by checking the difference between the current prefix sum and k. 
//   If the difference (current prefix sum - k) exists in the hashmap, it means there exists one or more paths,
//   ending at the current node, that sums to k so we increment our count accordingly.

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
    
        static int countPathsUtil(Node node, int k, int currSum, 
                              	HashMap<Integer, Integer> prefSums) {
        if (node == null)
            return 0;

        int pathCount = 0;
        currSum += node.data;

        if (currSum == k)
            pathCount++;

        // The count of (curr_sum − k) gives the number 
        // of paths with sum k up to the current node
        pathCount += prefSums.getOrDefault(currSum - k, 0);

        // Add the current sum into the hashmap
        prefSums.put(currSum, prefSums.getOrDefault(currSum, 0) + 1);

        pathCount += countPathsUtil(node.left, k, currSum, prefSums);
        pathCount += countPathsUtil(node.right, k, currSum, prefSums);

        // Remove the current sum from the hashmap
        prefSums.put(currSum, prefSums.get(currSum) - 1);

        return pathCount;
    }
    public int countAllPaths(Node root, int k) {
        // code here
        HashMap<Integer, Integer> prefSums = new HashMap<>();
      
        return countPathsUtil(root, k, 0, prefSums);
    }
}
