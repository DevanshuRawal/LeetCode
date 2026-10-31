class Solution {
public int uniquePaths(int[][] grid) {
int m = grid.length;
int n = grid[0].length;


    int MOD = 1_000_000_007;

    int[][] right = new int[m][n];
    int[][] down = new int[m][n];

    // Starting position
    right[0][0] = 1;

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {

            int r = right[i][j];
            int d = down[i][j];

            if (i == 0 && j == 0) {
                r = 0;
                d = 0;
            }

            if (grid[i][j] == 1) {

                // Mirror: right direction turns down
                if (i + 1 < m) {
                    down[i + 1][j] =
                        (int) ((down[i + 1][j] + (long) r) % MOD);
                }

                // Mirror: down direction turns right
                if (j + 1 < n) {
                    right[i][j + 1] =
                        (int) ((right[i][j + 1] + (long) d) % MOD);
                }

            } else {

                int ways = (int) ((r + (long) d) % MOD);

                if (i == 0 && j == 0) {
                    ways = 1;
                }

                // Move right
                if (j + 1 < n) {
                    right[i][j + 1] =
                        (int) ((right[i][j + 1] + (long) ways) % MOD);
                }

                // Move down
                if (i + 1 < m) {
                    down[i + 1][j] =
                        (int) ((down[i + 1][j] + (long) ways) % MOD);
                }
            }
        }
    }

    return (int) ((right[m - 1][n - 1] +
                   (long) down[m - 1][n - 1]) % MOD);
}


}
