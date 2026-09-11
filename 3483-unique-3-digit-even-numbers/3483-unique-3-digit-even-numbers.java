class Solution {
    public int totalNumbers(int[] digits) {
        boolean[] used = new boolean[1000];
        int[] cnt = new int[10];

        for (int d : digits)
            cnt[d]++;

        for (int a = 1; a <= 9; a++) {
            for (int b = 0; b <= 9; b++) {
                for (int c = 0; c <= 8; c += 2) {
                    if (cnt[a] > 0 && cnt[b] > 0 && cnt[c] > 0) {
                        cnt[a]--;
                        cnt[b]--;
                        cnt[c]--;

                        if (cnt[a] >= 0 && cnt[b] >= 0 && cnt[c] >= 0)
                            used[a * 100 + b * 10 + c] = true;

                        cnt[a]++;
                        cnt[b]++;
                        cnt[c]++;
                    }
                }
            }
        }

        int ans = 0;
        for (boolean x : used)
            if (x) ans++;

        return ans;
    }
}