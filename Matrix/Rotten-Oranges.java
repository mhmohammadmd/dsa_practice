Given a matrix mat[][], where each cell in the matrix can have values 0, 1 or 2 which has the following meaning:
0 : Empty cell
1 : Cell have fresh oranges
2 : Cell have rotten oranges
Your task is to determine the minimum time required so that all the oranges become rotten.
A rotten orange at index (i, j) can rot other fresh orange at indexes (i-1, j), (i+1, j), (i, j-1), (i, j+1) (up, down, left and right) in a unit time.

Note: If it is impossible to rot every orange then simply return -1.

Examples:
Input: mat[][] = [[2, 1, 0, 2, 1], [1, 0, 1, 2, 1], [1, 0, 0, 2, 1]]
Output: 2
Explanation: 
Oranges at positions (0,0), (0,3), (1,3), and (2,3) will rot adjacent fresh oranges in successive time frames.
All fresh oranges become rotten after 2 units of time.

Input: mat[][] = [[2, 1, 0, 2, 1], [0, 0, 1, 2, 1], [1, 0, 0, 2, 1]]
Output: -1
Explanation: Oranges at positions (0,0), (0,3), (1,3), and (2,3) rot some fresh oranges,
but the fresh orange at (2,0) can never be reached, so not all oranges can rot.

Constraints:
1 ≤ mat.size() ≤ 500
1 ≤ mat[0].size() ≤ 500
mat[i][j] = {0, 1, 2} 

Expected Complexities
Time Complexity: O(n * m)
Auxiliary Space: O(n * m)

Approach:
Using Breadth First Search - O(n x m) Time and O(n x m) Space
The idea is to use BFS (Breadth-First Search) because BFS explores the grid level by level.
In DFS, we can not say that a fresh orange getting rotten from another rotten orange represents its minimum time to rot because DFS explores deeply in one direction.
However, in BFS ensuring that when a fresh orange becomes rotten, it is always at the earliest possible time.

  To apply this idea, we first add all the initially rotten oranges to a queue.
  Then, we process them one by one from the queue. For each rotten orange, we check its four neighboring cells.
  If a neighbor is a fresh orange (1), it becomes rotten, and we push it into the queue.
  As BFS moves level by level, we also keep track of a variable to record the time taken — this value increases with each level processed.
  Once the queue becomes empty, it means all possible oranges have been processed.
  Finally, we check the grid again — if any fresh orange is still left, it means it couldn’t be reached, so we return -1.
  Otherwise, the maximum time recorded gives us the minimum time required to rot all the oranges.

  Solution:
  class Solution {
    static boolean isSafe(int i, int j, int n, int m) {
        return (i >= 0 && i < n && j >= 0 && j < m);
    }
    public int orangesRot(int[][] mat) {
        // code here
        int n = mat.length;
        int m = mat[0].length;

        // queue to store coordinates of rotten oranges
        Queue<int[]> q = new LinkedList<>();

        // counter of elapsed time
        int elapsedTime = 0;

        // push all initially rotten oranges into queue
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 2) {
                    q.add(new int[]{i, j});
                }
            }
        }

        // directions for all four adjacent cells
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        // perform BFS
        while (!q.isEmpty()) {
            int size = q.size();
            boolean flag = false; 

            // process all oranges at current time level
            for (int i = 0; i < size; i++) {
                int[] cell = q.poll();
                int x = cell[0];
                int y = cell[1];

                // check all four directions
                for (int[] dir : directions) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];

                    // if cell is safe and has fresh orange
                    if (isSafe(nx, ny, n, m) && mat[nx][ny] == 1) {
                        // rot the orange
                        mat[nx][ny] = 2;
                        q.add(new int[]{nx, ny});
                        flag = true;
                    }
                }
            }

            // if at least one orange got rotten, increase the time
            if (flag)
                elapsedTime++;
        }

        // check if any fresh orange still remains
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1)
                    return -1;
            }
        }

        return elapsedTime;
    }
}
