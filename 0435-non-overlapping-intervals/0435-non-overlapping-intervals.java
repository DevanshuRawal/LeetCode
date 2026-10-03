import java.util.Arrays;

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // Sort intervals by their end times in ascending order
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int count = 0; // Number of intervals to remove
        int prevEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < prevEnd) {
                // Overlap detected: greedily remove the interval that ends later
                count++;
            } else {
                // No overlap: keep this interval and update the endpoint
                prevEnd = intervals[i][1];
            }
        }

        return count;
    }
}