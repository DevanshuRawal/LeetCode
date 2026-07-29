import java.math.BigInteger;

class Solution {

    static final long LIMIT = 1_000_001L;

    public String smallestPalindrome(String s, int k) {

        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        int[] half = new int[26];
        int halfLen = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {

            half[i] = freq[i] / 2;
            halfLen += half[i];

            if ((freq[i] & 1) == 1) {
                middle = (char) ('a' + i);
            }
        }

        if (countWays(half, halfLen) < k) {
            return "";
        }

        StringBuilder left = new StringBuilder();

        int remaining = halfLen;

        while (remaining > 0) {

            for (int c = 0; c < 26; c++) {

                if (half[c] == 0) {
                    continue;
                }

                half[c]--;

                long ways = countWays(half, remaining - 1);

                if (ways >= k) {

                    left.append((char) ('a' + c));
                    remaining--;
                    break;

                } else {

                    k -= ways;
                    half[c]++;
                }
            }
        }

        String leftPart = left.toString();

        String rightPart =
                new StringBuilder(leftPart)
                        .reverse()
                        .toString();

        if (middle != 0) {
            return leftPart + middle + rightPart;
        }

        return leftPart + rightPart;
    }

    private long countWays(int[] cnt, int total) {

        long result = 1;
        int remaining = total;

        for (int i = 0; i < 26; i++) {

            if (cnt[i] == 0) {
                continue;
            }

            long ways = combination(
                    remaining,
                    cnt[i]
            );

            if (ways >= LIMIT) {
                return LIMIT;
            }

            result *= ways;

            if (result >= LIMIT) {
                return LIMIT;
            }

            remaining -= cnt[i];
        }

        return result;
    }

    private long combination(int n, int r) {

        r = Math.min(r, n - r);

        if (r == 0) {
            return 1;
        }

        BigInteger result = BigInteger.ONE;

        for (int i = 1; i <= r; i++) {

            result = result
                    .multiply(BigInteger.valueOf(n - r + i))
                    .divide(BigInteger.valueOf(i));

            if (result.compareTo(
                    BigInteger.valueOf(LIMIT)) >= 0) {
                return LIMIT;
            }
        }

        return result.longValue();
    }
}