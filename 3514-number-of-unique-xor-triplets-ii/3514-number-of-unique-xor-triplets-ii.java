class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int MAX = 2048;

        boolean[] present = new boolean[MAX];

        for (int num : nums) {
            present[num] = true;
        }

        boolean[] pairXor = new boolean[MAX];

        for (int a = 0; a < MAX; a++) {
            if (!present[a]) continue;

            for (int b = 0; b < MAX; b++) {
                if (!present[b]) continue;

                pairXor[a ^ b] = true;
            }
        }

        boolean[] result = new boolean[MAX];

        for (int x = 0; x < MAX; x++) {
            if (!pairXor[x]) continue;

            for (int c = 0; c < MAX; c++) {
                if (!present[c]) continue;

                result[x ^ c] = true;
            }
        }

        int answer = 0;

        for (boolean value : result) {
            if (value) answer++;
        }

        return answer;
    }
}