// Given the root of a Binary Tree, find the vertical traversal of the tree starting from the leftmost level to the rightmost level.
// Note: If there are multiple nodes passing through a vertical line, then they should be printed as they appear in level order traversal of the tree.

// Examples:

// Input: root = [1, 2, 3, 4, 5, 6, 7, N, N, N, 8, N, 9, N, 10, 11, N]                 
// Output: [[4], [2], [1, 5, 6, 11], [3, 8, 9], [7], [10]]

// Approach:
// For Vertical Order Traversal, the idea is very similar to the Top View problem you solved earlier.
// Key Idea:
// We assign a Horizontal Distance (HD) to every node:
// Root → HD = 0
// Left child → HD − 1
// Right child → HD + 1
// We perform BFS (level order traversal) so that nodes in the same vertical line appear in level order, as required.
// We store nodes in a TreeMap<Integer, ArrayList<Integer>>:
// Key → horizontal distance
// Value → list of nodes in that vertical line
// TreeMap keeps the vertical lines sorted from leftmost to rightmost.

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
    
    static class Pair {
        Node node;
        int hd;

        Pair(Node node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }
    public ArrayList<ArrayList<Integer>> verticalOrder(Node root) {
        // code here
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        
        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        
        q.add(new Pair(root, 0));
        
        while(!q.isEmpty()){
            Pair p = q.poll();
            Node node = p.node;
            int hd = p.hd;
            
            map.putIfAbsent(hd, new ArrayList<>());
            map.get(hd).add(node.data);
            
            if(node.left != null)
                q.add(new Pair(node.left, hd - 1));
            
            if(node.right != null)
                q.add(new Pair(node.right, hd + 1));
        }
        
        for(ArrayList<Integer> list : map.values()){
            result.add(list);
        }
        
        return result;
    }
}
