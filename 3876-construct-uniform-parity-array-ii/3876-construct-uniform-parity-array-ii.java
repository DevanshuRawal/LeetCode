class Solution {
    public boolean uniformArray(int[] nums1) {
        int min = Integer.MAX_VALUE;

        for (int x : nums1) {
            min = Math.min(min, x);
        }

        // Minimum element odd hai -> possible
        if (min % 2 == 1) {
            return true;
        }

        // Minimum even hai, to saare elements even hone chahiye
        for (int x : nums1) {
            if (x % 2 == 1) {
                return false;
            }
        }

        return true;
    }
}