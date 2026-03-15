// Write an API that generates fancy sequences using the append, addAll, and multAll operations.
// Implement the Fancy class:
// Fancy() Initializes the object with an empty sequence.
// void append(val) Appends an integer val to the end of the sequence.
// void addAll(inc) Increments all existing values in the sequence by an integer inc.
// void multAll(m) Multiplies all existing values in the sequence by an integer m.
// int getIndex(idx) Gets the current value at index idx (0-indexed) of the sequence modulo 109 + 7. If the index is greater or equal than the length of the sequence, return -1.
 
// Example 1:

// Input
// ["Fancy", "append", "addAll", "append", "multAll", "getIndex", "addAll", "append", "multAll", "getIndex", "getIndex", "getIndex"]
// [[], [2], [3], [7], [2], [0], [3], [10], [2], [0], [1], [2]]
// Output
// [null, null, null, null, null, 10, null, null, null, 26, 34, 20]

// Explanation
// Fancy fancy = new Fancy();
// fancy.append(2);   // fancy sequence: [2]
// fancy.addAll(3);   // fancy sequence: [2+3] -> [5]
// fancy.append(7);   // fancy sequence: [5, 7]
// fancy.multAll(2);  // fancy sequence: [5*2, 7*2] -> [10, 14]
// fancy.getIndex(0); // return 10
// fancy.addAll(3);   // fancy sequence: [10+3, 14+3] -> [13, 17]
// fancy.append(10);  // fancy sequence: [13, 17, 10]
// fancy.multAll(2);  // fancy sequence: [13*2, 17*2, 10*2] -> [26, 34, 20]
// fancy.getIndex(0); // return 26
// fancy.getIndex(1); // return 34
// fancy.getIndex(2); // return 20
 

// Constraints:

// 1 <= val, inc, m <= 100
// 0 <= idx <= 105
// At most 105 calls total will be made to append, addAll, multAll, and getIndex.


// This is a well-known hard problem (similar to Fancy Sequence).
// A naive approach would update every element for addAll and multAll, which would be O(n) per operation and too slow for up to 
// 10^5 calls.
// The trick is to use lazy linear transformation.
// Key Idea:
// All operations can be represented as a transformation:
//     value=value×mul+add
// Where:
// mul → global multiplication factor
// add → global addition factor
// Instead of modifying all elements, we store the normalized value when appending.
// When we append a value, we reverse the transformation so it fits the current state.
// To reverse multiplication we use modular inverse because results are modulo 
// 10^9 + 7.

class Fancy {

    long mod = 1000000007;
    ArrayList<Long> arr;
    long mul = 1;
    long add = 0;

    public Fancy() {
        arr = new ArrayList<>();
    }
    
    public void append(int val) {
        long normalized = ((val - add) % mod + mod) % mod;
        normalized = (normalized * modInverse(mul)) % mod;
        arr.add(normalized);
    }
    
    public void addAll(int inc) {
        add = (add + inc) % mod;
    }
    
    public void multAll(int m) {
        mul = (mul * m) % mod;
        add = (add * m) % mod;
    }
    
    public int getIndex(int idx) {
        if(idx >= arr.size()) return -1;
        
        long val = arr.get(idx);
        val = (val * mul % mod + add) % mod;
        
        return (int) val;
    }

    private long modInverse(long x){
        return power(x, mod - 2);
    }

    private long power(long x, long y){
        long res = 1;
        x %= mod;
        
        while(y > 0){
            if((y & 1) == 1) res = (res * x) % mod;
            x = (x * x) % mod;
            y >>= 1;
        }
        
        return res;
    }
}

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy obj = new Fancy();
 * obj.append(val);
 * obj.addAll(inc);
 * obj.multAll(m);
 * int param_4 = obj.getIndex(idx);
 */
