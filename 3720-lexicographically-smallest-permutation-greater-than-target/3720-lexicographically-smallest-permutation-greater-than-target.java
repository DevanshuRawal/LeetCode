class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        int i = 0;

        while (i < n) {
            int c = target.charAt(i) - 'a';

            if (count[c] == 0) {
                break;
            }

            count[c]--;
            i++;
        }

        while (true) {
            if (i < n) {
                int t = target.charAt(i) - 'a';

                for (int c = t + 1; c < 26; c++) {
                    if (count[c] > 0) {
                        count[c]--;

                        StringBuilder ans = new StringBuilder();
                        ans.append(target, 0, i);
                        ans.append((char) ('a' + c));

                        for (int x = 0; x < 26; x++) {
                            while (count[x] > 0) {
                                ans.append((char) ('a' + x));
                                count[x]--;
                            }
                        }

                        return ans.toString();
                    }
                }
            }

            if (i == 0) {
                break;
            }

            i--;
            count[target.charAt(i) - 'a']++;
        }

        return "";
    }
}