class Solution {


class BIT {
    int n;
    long[] tree;

    BIT(int n) {
        this.n = n;
        tree = new long[n + 1];
    }

    void add(int i, long val) {
        for (; i <= n; i += i & -i) {
            tree[i] += val;
        }
    }

    long query(int i) {
        long sum = 0;
        for (; i > 0; i -= i & -i) {
            sum += tree[i];
        }
        return sum;
    }

    int kth(long k) {
        int idx = 0;

        for (int step = Integer.highestOneBit(n);
             step > 0; step >>= 1) {

            int next = idx + step;

            if (next <= n && tree[next] < k) {
                idx = next;
                k -= tree[next];
            }
        }

        return idx + 1;
    }
}

public long minOperations(int[] nums, int x, int k) {

    int n = nums.length;

    int[] sorted = nums.clone();
    java.util.Arrays.sort(sorted);

    int m = 0;

    for (int v : sorted) {
        if (m == 0 || sorted[m - 1] != v) {
            sorted[m++] = v;
        }
    }

    int[] compressed = new int[n];

    for (int i = 0; i < n; i++) {
        compressed[i] =
            java.util.Arrays.binarySearch(sorted, 0, m, nums[i]) + 1;
    }

    BIT countBIT = new BIT(m);
    BIT sumBIT = new BIT(m);

    long[] cost = new long[n - x + 1];
    long total = 0;

    for (int i = 0; i < x; i++) {
        countBIT.add(compressed[i], 1);
        sumBIT.add(compressed[i], nums[i]);
        total += nums[i];
    }

    for (int i = 0; i <= n - x; i++) {

        int medianIndex = countBIT.kth((x + 1L) / 2);
        long median = sorted[medianIndex - 1];

        long leftCount = countBIT.query(medianIndex);
        long leftSum = sumBIT.query(medianIndex);

        long rightCount = x - leftCount;
        long rightSum = total - leftSum;

        cost[i] =
            median * leftCount - leftSum
            + rightSum - median * rightCount;

        if (i < n - x) {
            countBIT.add(compressed[i], -1);
            sumBIT.add(compressed[i], -nums[i]);
            total -= nums[i];

            countBIT.add(compressed[i + x], 1);
            sumBIT.add(compressed[i + x], nums[i + x]);
            total += nums[i + x];
        }
    }

    long INF = Long.MAX_VALUE / 4;

    long[] prev = new long[n + 1];
    long[] curr = new long[n + 1];

    for (int j = 1; j <= k; j++) {

        java.util.Arrays.fill(curr, INF);
        curr[0] = INF;

        for (int i = 1; i <= n; i++) {

            curr[i] = curr[i - 1];

            if (i >= x && prev[i - x] != INF) {
                curr[i] = Math.min(
                    curr[i],
                    prev[i - x] + cost[i - x]
                );
            }
        }

        long[] temp = prev;
        prev = curr;
        curr = temp;
    }

    return prev[n];
}


}
