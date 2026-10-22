class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;

        // Case 1: Cyclic increasing order
        boolean increasing = true;

        for (int i = 0; i < n; i++) {
            if (nums[i] != (nums[0] + i) % n) {
                increasing = false;
                break;
            }
        }

        if (increasing) {
            int k = nums[0];
            ans = Math.min(ans, Math.min((n - k) % n, k + 2));
        }

        // Case 2: Cyclic decreasing order
        boolean decreasing = true;

        for (int i = 0; i < n; i++) {
            if (nums[i] != (nums[0] - i + n) % n) {
                decreasing = false;
                break;
            }
        }

        if (decreasing) {
            int k = (n - 1 - nums[0] + n) % n;
            ans = Math.min(ans, 1 + Math.min(k, (n - k) % n));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}