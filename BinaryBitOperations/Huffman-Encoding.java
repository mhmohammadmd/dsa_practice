Given a string s of distinct characters and their corresponding frequency f[ ] i.e. character s[i] has f[i] frequency.
You need to build the Huffman tree and return all the huffman codes in preorder traversal of the tree.
Note: While merging if two nodes have the same value, then the node which occurs at first will be taken on the left of Binary Tree and the other one to the right,
      otherwise Node with less value will be taken on the left of the subtree and other one to the right.

Examples:
Input: s = "abcdef", f[] = {5, 9, 12, 13, 16, 45}
Output: [0, 100, 101, 1100, 1101, 111]
Explanation:
HuffmanCodes will be:
f : 0
c : 100
d : 101
a : 1100
b : 1101
e : 111
Constraints:
1 ≤ s.size() = f.size() ≤ 26

Approach:
Given:
  String s (distinct characters)
  Frequency array f[]

Build a Huffman Tree and return codes using preorder traversal
 Step 1: Create Nodes
         Each character becomes a node with:
             freq → frequency
             minIndex → its index in string (VERY IMPORTANT)
             left, right → children

Why minIndex?
To handle tie-breaking correctly (GFG requirement)

Step 2: Use Min Heap (Priority Queue)
       We always pick 2 smallest frequency nodes
       Comparator:
         1. Smaller frequency first
         2. If equal → smaller minIndex first
This ensures:
   Stable ordering
   Correct left/right placement
Step 3: Build Huffman Tree
     Repeat until one node remains:
     Remove 2 smallest nodes:
         left = pq.poll()
         right = pq.poll()
    Create new node:
       freq = sum
       minIndex = min(left, right)
   Attach:
      left → 0
      right → 1
   Push back into heap
Why left = first node?
  Because:
     Heap gives correct order
     Problem explicitly requires:
     smaller / earlier → left
Step 4: Tree Ready
         Now heap contains only:
             Root of Huffman Tree
Step 5: Generate Codes (DFS)
           Traverse tree:
               Direction	Code
               Left	"0"
               Right	"1"
   When reaching a leaf node:
          Store the generated string
          Special Case (VERY IMPORTANT)
If only one character:
      Code must be "0" (not empty string)
Example (Understanding Flow)
s = "abcdef"
f = [5, 9, 12, 13, 16, 45]
Process:
Pick smallest: 5 + 9 → 14
Then: 12 + 13 → 25
Then: 14 + 16 → 30
Then: 25 + 30 → 55
Then: 45 + 55 → 100

Final tree built
   Output Generation
Traverse:
Left → 0
Right → 1
We get:
f : 0
c : 100
d : 101
a : 1100
b : 1101
e : 111
Key Insights (Must Remember)
1. Greedy Nature
    Always combine two smallest frequencies
2. Why Huffman Works?
        Minimizes total weighted path length
        Used in compression (ZIP, JPEG, etc.)
3. Most Common Mistakes:
     Mistake	Fix
     Wrong tie-breaking	Use minIndex
     Using creation order	Wrong
     Missing single node case	Return "0"
     Wrong traversal	Use DFS
Complexity:
  Time	O(n log n)
  Space	O(n)

Implementation:
    import java.util.*;

class Solution {
    
    class Node {
        int freq;
        char ch;
        Node left, right;
        int minIndex;
        
        Node(int freq, char ch, int minIndex) {
            this.freq = freq;
            this.ch = ch;
            this.minIndex = minIndex;
        }
    }
    
    public ArrayList<String> huffmanCodes(String s, int f[]) {
        
        int n = s.length();
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
            if (a.freq == b.freq)
                return a.minIndex - b.minIndex;
            return a.freq - b.freq;
        });
        
        for (int i = 0; i < n; i++) {
            pq.add(new Node(f[i], s.charAt(i), i));
        }
        
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            
            Node parent = new Node(
                left.freq + right.freq,
                '$',
                Math.min(left.minIndex, right.minIndex)
            );
            
            parent.left = left;
            parent.right = right;
            
            pq.add(parent);
        }
        
        Node root = pq.poll();
        
        ArrayList<String> result = new ArrayList<>();
        
        generate(root, "", result);
        
        return result;
    }
    
    void generate(Node root, String code, ArrayList<String> res) {
        if (root == null) return;
        
        // leaf node
        if (root.left == null && root.right == null) {
            // FIX: handle single character case
            res.add(code.length() > 0 ? code : "0");
            return;
        }
        
        generate(root.left, code + "0", res);
        generate(root.right, code + "1", res);
    }
}
