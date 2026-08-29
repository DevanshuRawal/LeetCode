import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        Integer[] idx = new Integer[n];

        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }

        // Sort indices according to nums values
        Arrays.sort(idx, (a, b) -> Integer.compare(nums[a], nums[b]));

        int[] ans = new int[n];

        int i = 0;

        while (i < n) {
            int j = i + 1;

            // Find one connected group
            while (j < n &&
                   (long) nums[idx[j]] - nums[idx[j - 1]] <= limit) {
                j++;
            }

            // Get original indices of this group
            Integer[] positions = Arrays.copyOfRange(idx, i, j);

            // Smallest original indices first
            Arrays.sort(positions);

            // Smallest values first
            for (int k = 0; k < positions.length; k++) {
                ans[positions[k]] = nums[idx[i + k]];
            }

            i = j;
        }

        return ans;
    }
}