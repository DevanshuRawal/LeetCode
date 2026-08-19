import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            map.put(row, map.getOrDefault(row, 0) | (1 << (10 - col)));
        }

        int ans = (n - map.size()) * 2;

        int left = 0b0111100000;   // seats 2-5
        int middle = 0b0001111000; // seats 4-7
        int right = 0b0000011110;  // seats 6-9

        for (int mask : map.values()) {
            if ((mask & left) == 0 && (mask & right) == 0) {
                ans += 2;
            } else if ((mask & left) == 0) {
                ans++;
            } else if ((mask & right) == 0) {
                ans++;
            } else if ((mask & middle) == 0) {
                ans++;
            }
        }

        return ans;
    }
}