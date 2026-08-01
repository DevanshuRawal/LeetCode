class Solution {
    public boolean predictTheWinner(int[] nums) {

        int n = nums.length;
        int[][] dp = new int[n][n];

        // Base case: only one number
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }

        // Length of subarray
        for (int len = 2; len <= n; len++) {

            for (int i = 0; i + len - 1 < n; i++) {

                int j = i + len - 1;

                // Choose left or right
                int takeLeft = nums[i] - dp[i + 1][j];
                int takeRight = nums[j] - dp[i][j - 1];

                dp[i][j] = Math.max(takeLeft, takeRight);
            }
        }

        // If Player 1's score difference >= 0,
        // Player 1 can win or tie.
        return dp[0][n - 1] >= 0;
    }
}