class Solution {
    public char findKthBit(int n, int k) {
        return helper(n, k);
    }

    private char helper(int n, int k) {
        if (n == 1) return '0';
        int mid = 1 << (n - 1); // 2^(n-1)
        if (k == mid) return '1';
        if (k < mid) {
            return helper(n - 1, k);
        } else {
            // mirrored position in S_{n-1}
            int mirror = (1 << n) - k;
            char bit = helper(n - 1, mirror);
            return bit == '0' ? '1' : '0'; // invert
        }
    }
}
