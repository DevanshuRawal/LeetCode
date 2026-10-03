import java.util.*;

class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);

        int n = spells.length;
        int m = potions.length;
        int[] answer = new int[n];

        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = m;

            // Find first potion where spell * potion >= success
            while (left < right) {
                int mid = left + (right - left) / 2;

                if ((long) spells[i] * potions[mid] >= success) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            answer[i] = m - left;
        }

        return answer;
    }
}