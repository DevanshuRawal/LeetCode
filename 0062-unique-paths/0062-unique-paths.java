import java.util.Arrays;

class Solution {
    public int uniquePaths(int m, int n) {
        // dp[j] stores the number of unique paths to the current cell in column j
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // 1st row has exactly 1 way to reach any cell
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }
        
        return dp[n - 1];
    }
}