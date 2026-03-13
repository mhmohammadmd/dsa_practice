// Algorithm
// Binary search on time
// Range:
// low = 1
// high = max(workerTimes) * mountainHeight * (mountainHeight + 1) / 2
// For each mid time:
// compute total height workers can reduce
// If ≥ mountainHeight → try smaller time.
// Complexity:
// Binary search: log(1e16)
// Worker loop: 1e4
// Total ≈ O(n log answer)

class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {

        long left = 1;
        long right = (long)1e18;
        long ans = right;

        while (left <= right) {
            long mid = left + (right - left) / 2;

            if (canReduce(mid, mountainHeight, workerTimes)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    private boolean canReduce(long time, int mountainHeight, int[] workerTimes) {

        long total = 0;

        for (int t : workerTimes) {

            long val = (2 * time) / t;

            long x = (long)((Math.sqrt(1 + 4 * val) - 1) / 2);

            total += x;

            if (total >= mountainHeight)
                return true;
        }

        return total >= mountainHeight;
    }
}
