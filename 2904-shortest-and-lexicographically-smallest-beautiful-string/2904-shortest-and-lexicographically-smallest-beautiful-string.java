class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int left = 0;
        int ones = 0;
        int bestLen = Integer.MAX_VALUE;
        String ans = "";

        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') {
                ones++;
            }

            while (ones > k) {
                if (s.charAt(left) == '1') {
                    ones--;
                }
                left++;
            }

            while (ones == k) {
                int len = right - left + 1;

                if (len < bestLen) {
                    bestLen = len;
                    ans = s.substring(left, right + 1);
                } else if (len == bestLen) {
                    String cur = s.substring(left, right + 1);
                    if (cur.compareTo(ans) < 0) {
                        ans = cur;
                    }
                }

                if (s.charAt(left) == '1') {
                    ones--;
                }
                left++;
            }
        }

        return ans;
    }
}