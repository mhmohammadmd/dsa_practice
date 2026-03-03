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
    public int findMax1sRow(int[][] mat) {
        // code here
        int rows=mat.length;
        int cols=mat[0].length;
        int row=0,col=cols-1;
        int index=0;
        while(row<rows && col>=0){
            if(mat[row][col]==1){
                index=row;
                col--;
            }
            else{
                row++;
            }
        }
        return index;
    }
}
