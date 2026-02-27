// Approach:BFS
// We reduce the problem to:
// State = current number of zeros (m)
// Each operation transforms:
// newZero = m + k - 2x
// Where:
// x = number of zeros flipped
// Constraints:
// 0 ≤ x ≤ min(m, k)
// 0 ≤ k - x ≤ n - m
// Each possible zero count 0 → n is treated as a node.

// We perform BFS to find the minimum operations to reach 0.

// Maintain:

// dist[i] → minimum operations to reach zeroCount i

// Two ordered sets:

// Even zero counts

// Odd zero counts

// 🔹 BFS Steps

// Start from initial zero count.

// For each state m:

// Compute reachable interval [lnode, rnode].

// From the correct parity set, find all unvisited states in this range.

// Update:

// dist[next] = dist[m] + 1

// Add them to queue and remove from set.

// Continue until queue is empty.

// 🔹 Final Result

// If 0 is reachable → return dist[0]

// Otherwise → return -1

// ⏱ Complexity

// Time: O(n log n)

// Space: O(n)

class Solution {

    public int minOperations(String s, int k) {

        int n = s.length();
        int initialZeroCount = 0;

        // Count zeros
        for (char ch : s.toCharArray()) {
            if (ch == '0') {
                initialZeroCount++;
            }
        }

        // If already all 1s
        if (initialZeroCount == 0) {
            return 0;
        }

        // Distance array (minimum operations to reach zeroCount i)
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        // Separate unvisited states by parity
        List<TreeSet<Integer>> unvisited = new ArrayList<>();
        unvisited.add(new TreeSet<>()); // even zeroCounts
        unvisited.add(new TreeSet<>()); // odd zeroCounts

        for (int zeroCount = 0; zeroCount <= n; zeroCount++) {
            unvisited.get(zeroCount % 2).add(zeroCount);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(initialZeroCount);

        distance[initialZeroCount] = 0;
        unvisited.get(initialZeroCount % 2).remove(initialZeroCount);

        // BFS
        while (!queue.isEmpty()) {

            int currentZeroCount = queue.poll();
            int currentDistance = distance[currentZeroCount];

            // Compute valid zeros we can flip
            int minZerosFlipped =
                    Math.max(0, k - (n - currentZeroCount));

            int maxZerosFlipped =
                    Math.min(currentZeroCount, k);

            // Compute reachable zeroCount interval
            int leftReach =
                    currentZeroCount + k - 2 * maxZerosFlipped;

            int rightReach =
                    currentZeroCount + k - 2 * minZerosFlipped;

            // Only same parity states are reachable
            TreeSet<Integer> candidateStates =
                    unvisited.get(leftReach % 2);

            // Visit all reachable states in interval
            for (Integer nextZeroCount =
                    candidateStates.ceiling(leftReach);
                 nextZeroCount != null &&
                 nextZeroCount <= rightReach;
                 nextZeroCount =
                    candidateStates.ceiling(leftReach)) {

                distance[nextZeroCount] = currentDistance + 1;
                queue.offer(nextZeroCount);
                candidateStates.remove(nextZeroCount);
            }
        }

        return distance[0] == Integer.MAX_VALUE
                ? -1
                : distance[0];
    }
}
  
