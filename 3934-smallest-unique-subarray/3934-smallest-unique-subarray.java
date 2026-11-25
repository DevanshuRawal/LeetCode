import java.util.*;

class Solution {

    static final long MOD1 = 1000000007L;
    static final long MOD2 = 1000000009L;
    static final long BASE = 911382323L;

    long[] pow1, pow2;
    long[] hash1, hash2;

    public int smallestUniqueSubarray(int[] nums) {

        int n = nums.length;

        pow1 = new long[n + 1];
        pow2 = new long[n + 1];

        hash1 = new long[n + 1];
        hash2 = new long[n + 1];

        pow1[0] = 1;
        pow2[0] = 1;

        for (int i = 0; i < n; i++) {

            pow1[i + 1] = (pow1[i] * BASE) % MOD1;
            pow2[i + 1] = (pow2[i] * BASE) % MOD2;

            hash1[i + 1] =
                (hash1[i] * BASE + nums[i]) % MOD1;

            hash2[i + 1] =
                (hash2[i] * BASE + nums[i]) % MOD2;
        }

        int left = 1;
        int right = n;
        int ans = n;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (hasUnique(nums, mid)) {

                ans = mid;
                right = mid - 1;

            } else {

                left = mid + 1;
            }
        }

        return ans;
    }

    private boolean hasUnique(int[] nums, int len) {

        Map<Long, Integer> map = new HashMap<>();

        int n = nums.length;

        for (int i = 0; i + len <= n; i++) {

            int j = i + len;

            long h1 = (hash1[j] -
                hash1[i] * pow1[len] % MOD1 + MOD1) % MOD1;

            long h2 = (hash2[j] -
                hash2[i] * pow2[len] % MOD2 + MOD2) % MOD2;

            long key = (h1 << 32) | h2;

            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        for (int freq : map.values()) {

            if (freq == 1) {
                return true;
            }
        }

        return false;
    }
}