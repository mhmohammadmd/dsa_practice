Given a number n, generate bit patterns from 0 to 2n-1 such that successive patterns differ by one bit. 
A Gray code sequence must begin with 0. 
Examples:
Input: n = 2
Output: ["00", "01", "11", "10"]
Explanation: 
00 and 01 differ by one bit.
01 and 11 differ by one bit.
11 and 10 also differ by one bit.
Input: n = 3
Output: ["000", "001", "011", "010", "110", "111", "101", "100"]
Explanation:
000 and 001 differ by one bit.
001 and 011 differ by one bit.
011 and 010 differ by one bit.
Similarly, every successive pattern 
differs by one bit.
Constraints :
1 ≤ n ≤ 16

Approch:
Using Bit Manipulation
We directly generate the i'th Gray Code using the formula Gray(i) = i XOR (i >> 1), we iterate from 0 to 2^(n - 1),
apply this formula for each value, convert the result into an n-bit binary string, store it, and finally print the sequence. 

Why does this formula work?
If we take a closer look at the formula,
   When we do (i >> 1), all bits shift one position to the right, least significant bit is dropped and a 0 is inserted at the left.
   When we do XOR of i with (i >> 1), all trailing flips cancel out in XOR
   Only the first changing position survives.
Implementation:
  class Solution {
    public ArrayList<String> graycode(int n) {
        // code here
        ArrayList<String> res = new ArrayList<>();

        for (int i = 0; i < (1 << n); i++) {
            
            // generate i-th Gray code
            int g = i ^ (i >> 1);

            // Convert the code to string
            StringBuilder sb = new StringBuilder();
            for (int j = n - 1; j >= 0; j--) {
                if ((g & (1 << j)) != 0)
                    sb.append('1');
                else
                    sb.append('0');
            }

            res.add(sb.toString());
        }

        return res;
    }
}
