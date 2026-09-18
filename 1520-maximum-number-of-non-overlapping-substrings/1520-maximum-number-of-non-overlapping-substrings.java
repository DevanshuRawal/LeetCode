import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            if (i != first[c])
                continue;

            int l = i;
            int r = last[c];
            boolean ok = true;

            for (int j = l; j <= r; j++) {
                int x = s.charAt(j) - 'a';

                if (first[x] < l) {
                    ok = false;
                    break;
                }

                r = Math.max(r, last[x]);
            }

            if (ok)
                intervals.add(new int[]{l, r});
        }

        intervals.sort((a, b) -> a[1] - b[1]);

        List<String> ans = new ArrayList<>();
        int prevEnd = -1;

        for (int[] in : intervals) {
            if (in[0] > prevEnd) {
                ans.add(s.substring(in[0], in[1] + 1));
                prevEnd = in[1];
            }
        }

        return ans;
    }
}