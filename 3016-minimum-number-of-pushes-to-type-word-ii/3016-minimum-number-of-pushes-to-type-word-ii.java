import java.util.*;

class Solution {
    public int minimumPushes(String word) {

        int[] freq = new int[26];

        // Frequency count
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        // Sort frequencies
        Arrays.sort(freq);

        int ans = 0;
        int push = 1;
        int count = 0;

        // Start from highest frequency
        for (int i = 25; i >= 0; i--) {

            if (freq[i] == 0) {
                continue;
            }

            ans += freq[i] * push;

            count++;

            // Every 8 characters, push count increases
            if (count % 8 == 0) {
                push++;
            }
        }

        return ans;
    }
}