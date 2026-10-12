class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int MOD = 1_000_000_007;
        int[] bitCounts = new int[31];
        
        for (int num : nums) {
            for (int b = 0; b < 31; b++) {
                if (((num >> b) & 1) == 1) {
                    bitCounts[b]++;
                }
            }
        }
        
        long totalSum = 0;
        for (int i = 0; i < k; i++) {
            long val = 0;
            for (int b = 0; b < 31; b++) {
                if (bitCounts[b] > 0) {
                    val |= (1L << b);
                    bitCounts[b]--;
                }
            }
            totalSum = (totalSum + val * val) % MOD;
        }
        
        return (int) totalSum;
    }
};