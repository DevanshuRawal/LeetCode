class Solution {

    private int[][] factor = {
        {0, 0, 0, 0}, // 0
        {0, 0, 0, 0}, // 1
        {1, 0, 0, 0}, // 2
        {0, 1, 0, 0}, // 3
        {2, 0, 0, 0}, // 4
        {0, 0, 1, 0}, // 5
        {1, 1, 0, 0}, // 6
        {0, 0, 0, 1}, // 7
        {3, 0, 0, 0}, // 8
        {0, 2, 0, 0}  // 9
    };

    private int A, B, C, D;
    private int[][][][] dp;

    public String smallestNumber(String num, long t) {

        long x = t;

        A = B = C = D = 0;

        while (x % 2 == 0) {
            A++;
            x /= 2;
        }

        while (x % 3 == 0) {
            B++;
            x /= 3;
        }

        while (x % 5 == 0) {
            C++;
            x /= 5;
        }

        while (x % 7 == 0) {
            D++;
            x /= 7;
        }

        // t has a prime factor other than 2,3,5,7
        if (x != 1) {
            return "-1";
        }

        // DP: minimum digits needed for each factor state
        dp = new int[A + 1][B + 1][C + 1][D + 1];

        for (int a = 0; a <= A; a++) {
            for (int b = 0; b <= B; b++) {
                for (int c = 0; c <= C; c++) {
                    for (int d = 0; d <= D; d++) {

                        if (a == 0 && b == 0 && c == 0 && d == 0) {
                            dp[a][b][c][d] = 0;
                            continue;
                        }

                        int best = 1000000;

                        for (int digit = 2; digit <= 9; digit++) {

                            int na = Math.max(0, a - factor[digit][0]);
                            int nb = Math.max(0, b - factor[digit][1]);
                            int nc = Math.max(0, c - factor[digit][2]);
                            int nd = Math.max(0, d - factor[digit][3]);

                            if (na == a && nb == b &&
                                nc == c && nd == d) {
                                continue;
                            }

                            best = Math.min(
                                best,
                                1 + dp[na][nb][nc][nd]
                            );
                        }

                        dp[a][b][c][d] = best;
                    }
                }
            }
        }

        int minLength = dp[A][B][C][D];
        int n = num.length();

        // If a valid number cannot have n digits
        if (minLength > n) {
            return buildSmallest(minLength, A, B, C, D);
        }

        // Check num itself
        int a = A;
        int b = B;
        int c = C;
        int d = D;

        boolean valid = true;

        for (int i = 0; i < n; i++) {

            int digit = num.charAt(i) - '0';

            if (digit == 0) {
                valid = false;
                break;
            }

            a = Math.max(0, a - factor[digit][0]);
            b = Math.max(0, b - factor[digit][1]);
            c = Math.max(0, c - factor[digit][2]);
            d = Math.max(0, d - factor[digit][3]);
        }

        if (valid && a == 0 && b == 0 && c == 0 && d == 0) {
            return num;
        }

        /*
         * Prefix factor counts.
         */
        int[] p2 = new int[n + 1];
        int[] p3 = new int[n + 1];
        int[] p5 = new int[n + 1];
        int[] p7 = new int[n + 1];

        int firstZero = n;

        for (int i = 0; i < n; i++) {

            int digit = num.charAt(i) - '0';

            p2[i + 1] = p2[i];
            p3[i + 1] = p3[i];
            p5[i + 1] = p5[i];
            p7[i + 1] = p7[i];

            if (digit == 0) {
                firstZero = Math.min(firstZero, i);
            } else {
                p2[i + 1] += factor[digit][0];
                p3[i + 1] += factor[digit][1];
                p5[i + 1] += factor[digit][2];
                p7[i + 1] += factor[digit][3];
            }
        }

        /*
         * Change one digit, starting from the right.
         */
        for (int pos = n - 1; pos >= 0; pos--) {

            // Prefix [0 ... pos-1] must be zero-free.
            if (firstZero < pos) {
                continue;
            }

            int original = num.charAt(pos) - '0';

            for (int digit = original + 1; digit <= 9; digit++) {

                int used2 = p2[pos] + factor[digit][0];
                int used3 = p3[pos] + factor[digit][1];
                int used5 = p5[pos] + factor[digit][2];
                int used7 = p7[pos] + factor[digit][3];

                int need2 = Math.max(0, A - used2);
                int need3 = Math.max(0, B - used3);
                int need5 = Math.max(0, C - used5);
                int need7 = Math.max(0, D - used7);

                int remaining = n - pos - 1;

                if (dp[need2][need3][need5][need7] <= remaining) {

                    StringBuilder ans = new StringBuilder();

                    for (int i = 0; i < pos; i++) {
                        ans.append(num.charAt(i));
                    }

                    ans.append((char) ('0' + digit));

                    appendSmallest(
                        ans,
                        remaining,
                        need2,
                        need3,
                        need5,
                        need7
                    );

                    return ans.toString();
                }
            }
        }

        // Same length impossible -> use n+1 digits
        int length = Math.max(n + 1, minLength);

        return buildSmallest(length, A, B, C, D);
    }

    private String buildSmallest(
        int length,
        int a,
        int b,
        int c,
        int d
    ) {
        StringBuilder ans = new StringBuilder();

        appendSmallest(ans, length, a, b, c, d);

        return ans.toString();
    }

    private void appendSmallest(
        StringBuilder ans,
        int length,
        int a,
        int b,
        int c,
        int d
    ) {

        for (int pos = 0; pos < length; pos++) {

            int remaining = length - pos - 1;

            for (int digit = 1; digit <= 9; digit++) {

                int na = Math.max(0, a - factor[digit][0]);
                int nb = Math.max(0, b - factor[digit][1]);
                int nc = Math.max(0, c - factor[digit][2]);
                int nd = Math.max(0, d - factor[digit][3]);

                if (dp[na][nb][nc][nd] <= remaining) {

                    ans.append((char) ('0' + digit));

                    a = na;
                    b = nb;
                    c = nc;
                    d = nd;

                    break;
                }
            }
        }
    }
}