There are n 1-indexed robots, each having a position on a line, health, and movement direction.
You are given 0-indexed integer arrays positions, healths, and a string directions (directions[i] is either 'L' for left or 'R' for right). All integers in positions are unique.
All robots start moving on the line simultaneously at the same speed in their given directions. If two robots ever share the same position while moving, they will collide.
If two robots collide, the robot with lower health is removed from the line, and the health of the other robot decreases by one. The surviving robot continues in the same direction it was going.
If both robots have the same health, they are both removed from the line.
Your task is to determine the health of the robots that survive the collisions, in the same order that the robots were given,
i.e. final health of robot 1 (if survived), final health of robot 2 (if survived), and so on. If there are no survivors, return an empty array.
Return an array containing the health of the remaining robots (in the order they were given in the input), after no further collisions can occur.

Note: The positions may be unsorted.
Example 1:
Input: positions = [5,4,3,2,1], healths = [2,17,9,15,10], directions = "RRRRR"
Output: [2,17,9,15,10]
Explanation: No collision occurs in this example, since all robots are moving in the same direction. So, the health of the robots in order from the first robot is returned, [2, 17, 9, 15, 10].
Example 2:
Input: positions = [3,5,2,6], healths = [10,10,15,12], directions = "RLRL"
Output: [14]
Explanation: There are 2 collisions in this example. Firstly, robot 1 and robot 2 will collide, and since both have the same health, they will be removed from the line. Next, robot 3 and robot 4 will collide and since robot 4's health is smaller, it gets removed, and robot 3's health becomes 15 - 1 = 14. Only robot 3 remains, so we return [14].
Example 3:
Input: positions = [1,2,5,6], healths = [10,10,11,11], directions = "RLRL"
Output: []
Explanation: Robot 1 and robot 2 will collide and since both have the same health, they are both removed. Robot 3 and 4 will collide and since both have the same health, they are both removed. So, we return an empty array, [].
 

Constraints:
1 <= positions.length == healths.length == directions.length == n <= 105
1 <= positions[i], healths[i] <= 109
directions[i] == 'L' or directions[i] == 'R'
All values in positions are distinct

Approach:
Key Observations
1. Robots only collide when:
     One is moving Right ('R')
     Another is moving Left ('L')
     AND the 'R' robot is to the left of 'L'
2. Since positions are unsorted, first step is:
     Sort robots by position
3. After sorting:
     We process from left → right
     Maintain a stack of robots moving right ('R')
Core Idea (Stack Simulation)
     Traverse sorted robots:
        Case 1: direction = 'R'
                  Push into stack
        Case 2: direction = 'L'
                  This robot may collide with previous 'R' robots
                  While stack not empty AND top is 'R':

        Collision rules:
            If R.health < L.health
                R dies
                L.health -= 1
                Continue checking
            If R.health > L.health
                L dies
                R.health -= 1
                STOP
            If equal:
                Both die
                STOP
Important Trick:
     We must preserve original indices because final answer should be in original order.

Implementation:
   class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        
        // store: position, health, direction, original index
        int[][] robots = new int[n][4];
        
        for (int i = 0; i < n; i++) {
            robots[i][0] = positions[i];
            robots[i][1] = healths[i];
            robots[i][2] = directions.charAt(i);
            robots[i][3] = i;
        }
        
        // sort by position
        Arrays.sort(robots, (a, b) -> a[0] - b[0]);
        
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            if (robots[i][2] == 'R') {
                stack.push(i);
            } else {
                // direction = 'L'
                while (!stack.isEmpty() && robots[i][1] > 0) {
                    int j = stack.peek();
                    
                    if (robots[j][1] < robots[i][1]) {
                        // R dies
                        stack.pop();
                        robots[i][1]--;
                        robots[j][1] = 0;
                    } else if (robots[j][1] > robots[i][1]) {
                        // L dies
                        robots[j][1]--;
                        robots[i][1] = 0;
                        break;
                    } else {
                        // equal
                        robots[j][1] = 0;
                        robots[i][1] = 0;
                        stack.pop();
                        break;
                    }
                }
            }
        }
        
        // collect survivors
        int[] result = new int[n];
        Arrays.fill(result, -1);
        
        for (int i = 0; i < n; i++) {
            if (robots[i][1] > 0) {
                int idx = robots[i][3];
                result[idx] = robots[i][1];
            }
        }
        
        List<Integer> ans = new ArrayList<>();
        for (int x : result) {
            if (x != -1) ans.add(x);
        }
        
        return ans;
    }
}

We must preserve original indices because final answer should be in original order.

✅ Java Implementation
class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        
        // store: position, health, direction, original index
        int[][] robots = new int[n][4];
        
        for (int i = 0; i < n; i++) {
            robots[i][0] = positions[i];
            robots[i][1] = healths[i];
            robots[i][2] = directions.charAt(i);
            robots[i][3] = i;
        }
        
        // sort by position
        Arrays.sort(robots, (a, b) -> a[0] - b[0]);
        
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            if (robots[i][2] == 'R') {
                stack.push(i);
            } else {
                // direction = 'L'
                while (!stack.isEmpty() && robots[i][1] > 0) {
                    int j = stack.peek();
                    
                    if (robots[j][1] < robots[i][1]) {
                        // R dies
                        stack.pop();
                        robots[i][1]--;
                        robots[j][1] = 0;
                    } else if (robots[j][1] > robots[i][1]) {
                        // L dies
                        robots[j][1]--;
                        robots[i][1] = 0;
                        break;
                    } else {
                        // equal
                        robots[j][1] = 0;
                        robots[i][1] = 0;
                        stack.pop();
                        break;
                    }
                }
            }
        }
        
        // collect survivors
        int[] result = new int[n];
        Arrays.fill(result, -1);
        
        for (int i = 0; i < n; i++) {
            if (robots[i][1] > 0) {
                int idx = robots[i][3];
                result[idx] = robots[i][1];
            }
        }
        
        List<Integer> ans = new ArrayList<>();
        for (int x : result) {
            if (x != -1) ans.add(x);
        }
        
        return ans;
    }
}
⏱ Complexity
Sorting: O(n log n)
Stack simulation: O(n) (each robot processed once)
Total: O(n log n)
🧠 Intuition Summary (Important for Interviews)
Sort by position → makes collisions predictable
Only R vs L matters → use stack
Simulate collisions greedily
Track health updates dynamically
