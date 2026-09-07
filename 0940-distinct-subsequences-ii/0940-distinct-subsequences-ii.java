class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;
        long[] dp = new long[26];
        long total = 0;

        for (char c : s.toCharArray()) {
            int i = c - 'a';
            long add = (total + 1) % MOD;

            total = (total - dp[i] + add + MOD) % MOD;
            dp[i] = add;
        }

        return (int) total;
    }
}