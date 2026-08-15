class Solution {
    public int longestSubsequence(int[] nums) {
        int xor = 0;
        int n = nums.length;
        boolean hasNonZero = false;

        for (int num : nums) {
            xor ^= num;

            if (num != 0) {
                hasNonZero = true;
            }
        }

        if (xor != 0) {
            return n;
        }

        if (!hasNonZero) {
            return 0;
        }

        return n - 1;
    }
}