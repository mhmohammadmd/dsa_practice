Given a string s, replace all the spaces in the string with '%20'.
Examples:
Input: s = "i love programming"
Output: "i%20love%20programming"
Explanation: The 2 spaces are replaced by '%20'
  
Input: s = "Mr Benedict Cumberbatch"
Output: "Mr%20Benedict%20Cumberbatch"
Explanation: The 2 spaces are replaced by '%20'
Constraints:
1 ≤ s.size() ≤ 104

Implementation:
class Solution {
    String URLify(String s) {
        // code here
        return s.replace(" ", "%20");
    }
}
