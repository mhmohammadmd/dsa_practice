exmple
  input:[
        [0,1,0,1,1],
        [0,0,0,1,0],
        [1,0,1,1,1],
        [0,1,1,1,1]
  output:
     2
    As Index or row 2 and 3 contains same number of 1's that is 4 but we have to return small index.

class Solution {
    public int totalElements(int[] arr) {
        // code here
        Map<Integer,Integer> mp=new HashMap<>();
        int l=0,r=0,n=arr.length;
        int size=0;
        while(r<n){
            mp.put(arr[r],mp.getOrDefault(arr[r],0)+1);
            while(mp.size()>2){
                mp.put(arr[l],mp.get(arr[l])-1);
                if(mp.get(arr[l])==0){
                    mp.remove(arr[l]);
                    
                }
                l++;
            }
            size=Math.max(size,r-l+1);
            r++;
        }
        return size;
    }
}
