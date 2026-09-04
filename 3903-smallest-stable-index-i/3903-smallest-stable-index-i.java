class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;

        // right[i] = minimum element from i to n-1
        int[] right = new int[n];
        right[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.min(right[i + 1], nums[i]);
        }

        // Maximum from 0 to i
        int leftMax = 0;

        for (int i = 0; i < n; i++) {
            leftMax = Math.max(leftMax, nums[i]);

            if (leftMax - right[i] <= k) {
                return i;
            }
        }

        return -1;
    }
}