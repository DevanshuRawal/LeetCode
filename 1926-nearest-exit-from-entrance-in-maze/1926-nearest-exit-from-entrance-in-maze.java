import java.util.*;

class Solution {
    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length;
        int n = maze[0].length;

        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{entrance[0], entrance[1]});

        // Mark entrance as visited
        maze[entrance[0]][entrance[1]] = '+';

        int steps = 0;

        int[][] directions = {
            {-1, 0},  // up
            {1, 0},   // down
            {0, -1},  // left
            {0, 1}    // right
        };

        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();

                int row = current[0];
                int col = current[1];

                for (int[] dir : directions) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];

                    // Valid and empty cell
                    if (newRow >= 0 && newRow < m &&
                        newCol >= 0 && newCol < n &&
                        maze[newRow][newCol] == '.') {

                        // If it's a border cell, it's the nearest exit
                        if (newRow == 0 || newRow == m - 1 ||
                            newCol == 0 || newCol == n - 1) {
                            return steps;
                        }

                        maze[newRow][newCol] = '+';
                        queue.offer(new int[]{newRow, newCol});
                    }
                }
            }
        }

        return -1;
    }
}