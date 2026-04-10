
class Solution {
    public ArrayList<Integer> find3Numbers(int[] arr) {
        int n = arr.length;
        ArrayList<Integer> res = new ArrayList<>();
        if (n < 3) return res;

        int[] leftMin = new int[n];
        int[] rightMax = new int[n];

        // fill leftMin
        leftMin[0] = arr[0];
        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], arr[i]);
        }

        // fill rightMax
        rightMax[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], arr[i]);
        }

        // find triplet
        for (int i = 1; i < n - 1; i++) {
            if (leftMin[i] < arr[i] && arr[i] < rightMax[i]) {
                res.add(leftMin[i]);
                res.add(arr[i]);
                res.add(rightMax[i]);
                return res;
            }
        }

        return res;
    }
}
