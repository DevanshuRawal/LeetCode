class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[mid + 1]) {
                // Peak right side mein hai
                left = mid + 1;
            } else {
                // Peak left side ya mid par hai
                right = mid;
            }
        }

        return left;
    }
}