class Solution {
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int cash = 0;
        int hold = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            // Can either keep holding or buy today using yesterday's cash
            int nextHold = Math.max(hold, cash - prices[i]);
            // Can either keep cash or sell today's holding minus the transaction fee
            int nextCash = Math.max(cash, hold + prices[i] - fee);

            hold = nextHold;
            cash = nextCash;
        }

        return cash;
    }
}