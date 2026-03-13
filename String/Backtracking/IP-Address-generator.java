// Given a string s consisting only of digits, return all possible valid IP address combinations that can be formed from it. A valid IP address consists of four numbers (0–255) separated by dots.
// Note: A segment cannot contain leading zeros unless the number itself is 0. For example, 1.1.2.11 and 0.11.21.1 are valid IP addresses, while 01.1.2.11 and 00.11.21.1 are not valid because they contain leading zeros.
// Examples:
// Input: s = "255678166"
// Output: [“25.56.78.166”, “255.6.78.166”, "255.67.8.166", "255.67.81.66"]
// Explanation: These are the only valid possible IP addresses.
// Input: s = "25505011535"
// Output: []
// Explanation: We cannot generate a valid IP address with this string.
// Approach:
// Backttracking:
// We generate all possible ways to place 3 dots in the string to form 4 segments.
// During the process, we only continue if the current segment is valid (0–255 and no leading zeros).
// If a segment becomes invalid, we prune that branch to avoid unnecessary exploration.
  
  
//   Step by Step implementation
// Start backtracking from the beginning of the string and try forming segments.
// Choose segment lengths of 1, 2, or 3 digits, since a valid IP part cannot exceed 3 digits.
// Validate the segment: it must be between 0 and 255 and must not contain leading zeros.
// If the segment is valid, place a dot and recursively process the remaining string.
// Prune the recursion if a segment becomes invalid.
// Stop when 3 dots are placed and check whether the final segment is valid.
// If all 4 segments are valid, store the formed IP address.

class Solution {
    
        // Check if segment is valid
    static boolean isValid(String s) {
        if (s.length() > 1 && s.charAt(0) == '0')
            return false;

        int val = Integer.parseInt(s);
        return val <= 255;
    }

    static void generateIpRec(String s, int index, String curr, int cnt, List<String> res) {

        if (index >= s.length())
            return;

        // Validate last segment
        if (cnt == 3) {
            String last = s.substring(index);
            if (last.length() <= 3 && isValid(last))
                res.add(curr + last);
            return;
        }

        String segment = "";

        // Try segment length 1–3
        for (int i = index; i < Math.min(index + 3, s.length()); i++) {
            segment += s.charAt(i);

            if (isValid(segment))
                generateIpRec(s, i + 1, curr + segment + ".", cnt + 1, res);
        }
    }
    
    public ArrayList<String> generateIp(String s) {
        // code here
         ArrayList<String> res = new ArrayList<>();
        generateIpRec(s, 0, "", 0, res);
        return res;
    }
}
