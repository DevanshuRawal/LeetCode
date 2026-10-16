class Solution {
    private static final int MOD = 1_000_000_007;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        int[] lastSeen = new int[100001];
        java.util.Arrays.fill(lastSeen, -1);

        SegmentTree tree = new SegmentTree(n);
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int left = lastSeen[nums[i]] + 1;

            tree.update(1, 0, n - 1, left, i);

            ans = (ans + tree.getSquareSum()) % MOD;
            lastSeen[nums[i]] = i;
        }

        return (int) ans;
    }

    class SegmentTree {
        long[] sum;
        long[] squareSum;
        long[] lazy;
        int n;

        SegmentTree(int n) {
            this.n = n;
            sum = new long[4 * n];
            squareSum = new long[4 * n];
            lazy = new long[4 * n];
        }

        void apply(int node, int start, int end, long val) {
            long len = end - start + 1L;

            squareSum[node] = (
                squareSum[node]
                + 2 * val * sum[node]
                + val * val * len
            ) % MOD;

            sum[node] = (sum[node] + val * len) % MOD;
            lazy[node] = (lazy[node] + val) % MOD;
        }

        void push(int node, int start, int end) {
            if (lazy[node] == 0 || start == end) {
                return;
            }

            int mid = (start + end) / 2;

            apply(node * 2, start, mid, lazy[node]);
            apply(node * 2 + 1, mid + 1, end, lazy[node]);

            lazy[node] = 0;
        }

        void update(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return;
            }

            if (left <= start && end <= right) {
                apply(node, start, end, 1);
                return;
            }

            push(node, start, end);

            int mid = (start + end) / 2;

            update(node * 2, start, mid, left, right);
            update(node * 2 + 1, mid + 1, end, left, right);

            sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;

            squareSum[node] = (
                squareSum[node * 2] + squareSum[node * 2 + 1]
            ) % MOD;
        }

        long getSquareSum() {
            return squareSum[1];
        }
    }
}