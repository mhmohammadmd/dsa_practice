// Given an array arr[] with non-negative integers representing the height of blocks. If the width of each block is 1, compute how much water can be trapped between the blocks during the rainy season. 

// Examples:

// Input: arr[] = [3, 0, 1, 0, 4, 0 2]
// Output: 10
// Explanation: Total water trapped = 0 + 3 + 2 + 3 + 0 + 2 + 0 = 10 units.

class Solution {
    public int maxWater(int arr[]) {
        // code here
        int n=arr.length;
        int l=1,r=n-2;
        int lmax=arr[l-1];
        int rmax=arr[r+1];
        int rest=0;
        while(l<=r){
            if(rmax<=lmax){
                rest+=Math.max(0,rmax-arr[r]);
                rmax=Math.max(rmax,arr[r]);
                r-=1;
            }
            else{
                rest+=Math.max(0,lmax-arr[l]);
                lmax=Math.max(lmax,arr[l]);
                l+=1;
            }
        }
        return rest;
    }
}
