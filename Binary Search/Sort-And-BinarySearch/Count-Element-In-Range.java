Given:
array and 2D array :
arr[],queries[][]
we have to count total element in that range such that
  q[[a,b]] --> a<=x<=b
  eg:
   arr[1,4,2,8,5] and q=[[1,4],[3,6],[0,10]]
   output:
     sorted array=[1,2,4,5,8]
     for 1 query [1,4]
     count=3-->[1,2,4]
     2--> query[3,6]
     count=2 -->[4,5]
     3--> query[0,10]
     count=5 -->[1,2,4,5,8]
therefore overall output is as 
   [3,2,5]

  Approach: Sort + BinarySearch

  class Solution {
    public ArrayList<Integer> cntInRange(int[] arr, int[][] queries) {
        // code here
        ArrayList<Integer> count=new ArrayList<>();
        Arrays.sort(arr);
        for(int []q:queries){
            int a=q[0];
            int b=q[1];
            int left=LowerBond(arr,a);
            int right=UpperBond(arr,b);
            count.add(right-left);
        }
        return count;
    }
    private static int LowerBond(int[] arr, int target){
        int l=0,h=arr.length;
        while(l<h){
            int mid=l+(h-l)/2;
            if(arr[mid]<target){
                l=mid+1;
            }
            else{
                h=mid;
            }
        }
        return l;
    }
    private static int UpperBond(int[] arr, int target){
        int l=0,h=arr.length;
        while(l<h){
            int mid=l + (h-l)/2;
            if(arr[mid]<=target){
                l=mid+1;
            }
            else{
                h=mid;
            }
        }
        return l;
    }
}
