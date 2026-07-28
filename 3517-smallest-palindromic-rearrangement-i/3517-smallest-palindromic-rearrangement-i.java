import java.util.*;

class Solution {
    public String smallestPalindrome(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder firstHalf = new StringBuilder();
        char middleChar = 0;

        // Build first half in lexicographic order
        for (int i = 0; i < 26; i++) {
            int count = freq[i];
            if (count % 2 == 1) {
                middleChar = (char) (i + 'a'); // odd frequency char goes in the middle
            }
            for (int j = 0; j < count / 2; j++) {
                firstHalf.append((char) (i + 'a'));
            }
        }

        StringBuilder secondHalf = new StringBuilder(firstHalf).reverse();
        if (middleChar != 0) {
            return firstHalf.toString() + middleChar + secondHalf.toString();
        } else {
            return firstHalf.toString() + secondHalf.toString();
        }
    }
}
