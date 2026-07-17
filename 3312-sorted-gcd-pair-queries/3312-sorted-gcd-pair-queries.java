import java.util.Arrays;

class Solution {

    public int[] gcdValues(int[] nums, long[] queries) {
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }

        int[] freq = new int[mx + 1];
        for (int x : nums) {
            freq[x]++;
        }

        long[] cntG = new long[mx + 1];

        // Count pairs having gcd exactly i
        for (int i = mx; i >= 1; i--) {
            int count = 0;
            for (int j = i; j <= mx; j += i) {
                count += freq[j];
                cntG[i] -= cntG[j];
            }
            cntG[i] += 1L * count * (count - 1) / 2;
        }

        // Prefix sum
        for (int i = 1; i <= mx; i++) {
            cntG[i] += cntG[i - 1];
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            ans[i] = binarySearch(cntG, queries[i]);
        }

        return ans;
    }

    private int binarySearch(long[] prefix, long target) {
        int left = 1;
        int right = prefix.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (prefix[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}