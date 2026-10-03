class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int prev2 = 0; // Max profit up to house i - 2
        int prev1 = 0; // Max profit up to house i - 1
        
        for (int num : nums) {
            // Either skip current house (prev1) OR rob it (prev2 + num)
            int current = Math.max(prev1, prev2 + num);
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
}