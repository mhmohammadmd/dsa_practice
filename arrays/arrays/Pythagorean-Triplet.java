// Given an array arr[], return true if there is a triplet (a, b, c) from the array (where a, b, and c are on different indexes) that satisfies a2 + b2 = c2, otherwise return false.

// Examples:

// Input: arr[] = [3, 2, 4, 6, 5]
// Output: true
// Explanation: a=3, b=4, and c=5 forms a pythagorean triplet.
// Input: arr[] = [3, 8, 5]
// Output: false
// Explanation: No such triplet possible.
// Input: arr[] = [1, 1, 1]
// Output: false
// Constraints:
// 1 <= arr.size() <= 105
// 1 <= arr[i] <= 103

// Approach:
// Iterating till max element - O(max(arr)^2) Time and O(max(arr)) Space
//   The idea is to generate all possible pairs (a, b) within the range of maximum element in the input array using nested loops.
//   For each pair, calculate the value of c required to form a Pythagorean Triplet and check if c exists in the input array.
//   We can check if c exists or not in constant time by marking all the elements of input array in a visited array. 
//   Here, we only need to mark the elements and not store their frequency because for all valid triplets of positive integers (a, b, c),
//   no two elements can be equal, that is a != b, a != c and b != c.

class Solution {
    boolean pythagoreanTriplet(int[] arr) {
        // code here
        int n=arr.length;
        int maxElemnt=0;
        for(int elemnt:arr)
            maxElemnt=Math.max(maxElemnt,elemnt);
        boolean[] visited=new boolean[maxElemnt + 1];
        
        for(int ele : arr){
            visited[ele]=true;
        }
        
        for(int a=0; a<maxElemnt + 1;a++){
            if(!visited[a])
                continue;
            for(int b=1;b<maxElemnt + 1; b++){
                if(!visited[b])
                   continue;
                int c = (int)Math.sqrt(a*a + b*b);
                if((c*c)!=(a*a + b*b) || c>maxElemnt)
                     continue;
                if(visited[c]){
                    return true;
                }
            }
        }
        return false;
        
    }
}
