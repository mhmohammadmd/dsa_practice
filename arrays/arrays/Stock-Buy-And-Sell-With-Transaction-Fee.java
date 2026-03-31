You are given an array arr[], in which arr[i] is the price of a given stock on the ith day and an integer k represents a transaction fee.
Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

Examples:
Input: arr[] = [6, 1, 7, 2, 8, 4], k = 2
Output: 8
Explanation:
Buy the stock on day 2 and sell it on day 3 => 7 – 1 -2 = 4
Buy the stock on day 4 and sell it on day 5 => 8 – 2 - 2 = 4
Maximum Profit  = 4 + 4 = 8
  
Input: arr[] = [7, 1, 5, 3, 6, 4], k = 1
Output: 5
Explanation: 
Buy the stock on day 2 and sell it on day 3 => 5 – 1 - 1 = 3
Buy the stock on day 4 and sell it on day 5 => 6 – 3 - 1 = 2
Maximum Profit  = 3 + 2 = 5
  
Constraint:
1 ≤ arr.size() ≤ 106
1 ≤ arr[i] ≤ 106
0 ≤ k ≤ 106

Approach:
Space Optimized - O(n) Time and O(1) Space
Instead of using a DP table, we observe that each day’s profit depends only on the next day’s states.
So, we keep two variables:
     noStock – profit when not holding a stock
     inHand – profit when holding a stock
For each day (starting from the end):
    To buy, choose between skipping or buying today → newNoStock = max(noStock, inHand - arr[i])
    To sell, choose between skipping or selling today (paying fee k) → newInHand = max(inHand, arr[i] - k + noStock)
Update both states for the next iteration. Finally, noStock holds the maximum achievable profit.

Implementation:
  class Solution {
    public int maxProfit(int arr[], int k) {
        //Code here
        int n = arr.length;

        int noStock = 0, inHand = 0;

        for (int i = n - 1; i >= 0; i--) {

            //Choose to buy or skip
            int newNoStock = Math.max(noStock, inHand - arr[i]);

            //Choose to sell or skip
            int newInHand = Math.max(inHand, arr[i] - k + noStock);

            //Update states
            noStock = newNoStock;
            inHand = newInHand;
        }

        return noStock;
    }
}
