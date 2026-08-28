class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];

        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        int odd = -1;

        for (int i = 0; i < 26; i++) {
            if ((cnt[i] & 1) == 1) {
                if (odd != -1) return "";
                odd = i;
            }
            cnt[i] /= 2;
        }

        int half = n / 2;

        char[] h = new char[half];

        for (int i = 0; i < half; i++) {
            h[i] = target.charAt(i);
        }

        int[] rem = cnt.clone();

        boolean possible = true;

        for (int i = 0; i < half; i++) {
            int x = h[i] - 'a';

            if (rem[x] == 0) {
                possible = false;
                break;
            }

            rem[x]--;
        }

        if (possible) {
            String ans = makePalindrome(h, odd);

            if (ans.compareTo(target) > 0) {
                return ans;
            }
        }

        for (int pos = half - 1; pos >= 0; pos--) {
            rem = cnt.clone();

            boolean prefixValid = true;

            for (int i = 0; i < pos; i++) {
                int x = target.charAt(i) - 'a';

                if (rem[x] == 0) {
                    prefixValid = false;
                    break;
                }

                rem[x]--;
            }

            if (!prefixValid) continue;

            int current = target.charAt(pos) - 'a';

            for (int c = current + 1; c < 26; c++) {
                if (rem[c] > 0) {
                    char[] next = new char[half];

                    for (int i = 0; i < pos; i++) {
                        next[i] = target.charAt(i);
                    }

                    next[pos] = (char) ('a' + c);
                    rem[c]--;

                    int k = pos + 1;

                    for (int x = 0; x < 26; x++) {
                        while (rem[x] > 0) {
                            next[k++] = (char) ('a' + x);
                            rem[x]--;
                        }
                    }

                    String ans = makePalindrome(next, odd);

                    if (ans.compareTo(target) > 0) {
                        return ans;
                    }
                }
            }
        }

        return "";
    }

    private String makePalindrome(char[] half, int odd) {
        int n = half.length * 2 + (odd != -1 ? 1 : 0);

        char[] result = new char[n];

        for (int i = 0; i < half.length; i++) {
            result[i] = half[i];
        }

        if (odd != -1) {
            result[half.length] = (char) ('a' + odd);
        }

        for (int i = 0; i < half.length; i++) {
            result[n - 1 - i] = half[i];
        }

        return new String(result);
    }
}