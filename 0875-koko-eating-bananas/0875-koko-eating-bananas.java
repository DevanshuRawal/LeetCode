class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = 0;
        
        // Find the maximum pile size to define the upper bound
        for (int pile : piles) {
            if (pile > high) {
                high = pile;
            }
        }
        
        int ans = high;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            // Use 'long' to prevent integer overflow when accumulating hours
            long totalHours = 0;
            for (int pile : piles) {
                // Integer arithmetic for ceil(pile / mid): (pile + mid - 1) / mid
                totalHours += (long) (pile + mid - 1) / mid;
            }
            
            if (totalHours <= h) {
                ans = mid;           // mid is valid; try finding a smaller speed
                high = mid - 1;
            } else {
                low = mid + 1;       // mid is too slow; increase speed
            }
        }
        
        return ans;
    }
}