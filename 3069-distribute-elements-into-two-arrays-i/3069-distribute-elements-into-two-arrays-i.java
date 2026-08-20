class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;

        int[] a = new int[n];
        int[] b = new int[n];

        int aSize = 1;
        int bSize = 1;

        a[0] = nums[0];
        b[0] = nums[1];

        for (int i = 2; i < n; i++) {
            if (a[aSize - 1] > b[bSize - 1]) {
                a[aSize++] = nums[i];
            } else {
                b[bSize++] = nums[i];
            }
        }

        int[] ans = new int[n];

        for (int i = 0; i < aSize; i++) {
            ans[i] = a[i];
        }

        for (int i = 0; i < bSize; i++) {
            ans[aSize + i] = b[i];
        }

        return ans;
    }
}