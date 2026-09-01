class Solution {
    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int[][] litter = new int[m][n];

        int startX = 0;
        int startY = 0;
        int count = 0;

        // Find starting point and number each litter
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startX = i;
                    startY = j;
                } 
                else if (ch == 'L') {
                    litter[i][j] = count;
                    count++;
                }
            }
        }

        // No litter
        if (count == 0) {
            return 0;
        }

        int totalMasks = 1 << count;

        /*
         * visited[row][col][energy][mask]
         *
         * mask:
         * 1 = litter collected
         * 0 = litter not collected
         */
        boolean[][][][] visited =
            new boolean[m][n][energy + 1][totalMasks];

        // Queue: row, col, remainingEnergy, mask
        java.util.Queue<int[]> queue =
            new java.util.ArrayDeque<>();

        int startMask = 0;

        queue.offer(new int[] {
            startX,
            startY,
            energy,
            startMask
        });

        visited[startX][startY][energy][startMask] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int moves = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            // Process one BFS level
            while (size-- > 0) {

                int[] state = queue.poll();

                int r = state[0];
                int c = state[1];
                int e = state[2];
                int mask = state[3];

                // All litter collected
                if (mask == totalMasks - 1) {
                    return moves;
                }

                // If no energy, cannot move
                if (e == 0) {
                    continue;
                }

                for (int k = 0; k < 4; k++) {

                    int nr = r + dr[k];
                    int nc = c + dc[k];

                    // Outside grid
                    if (nr < 0 || nr >= m ||
                        nc < 0 || nc >= n) {
                        continue;
                    }

                    // Obstacle
                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    int newEnergy = e - 1;

                    /*
                     * If we reach reset cell,
                     * restore energy to maximum.
                     */
                    if (classroom[nr].charAt(nc) == 'R') {
                        newEnergy = energy;
                    }

                    int newMask = mask;

                    // Collect litter
                    if (classroom[nr].charAt(nc) == 'L') {

                        int id = litter[nr][nc];

                        newMask |= (1 << id);
                    }

                    if (!visited[nr][nc][newEnergy][newMask]) {

                        visited[nr][nc][newEnergy][newMask] = true;

                        queue.offer(new int[] {
                            nr,
                            nc,
                            newEnergy,
                            newMask
                        });
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}