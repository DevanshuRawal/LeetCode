import java.util.*;

class Solution {
    public int minReorder(int n, int[][] connections) {
        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : connections) {
            int u = edge[0];
            int v = edge[1];

            // u -> v is original direction, so cost = 1
            graph[u].add(new int[]{v, 1});

            // v -> u is already toward city 0, so cost = 0
            graph[v].add(new int[]{u, 0});
        }

        return dfs(0, -1, graph);
    }

    private int dfs(int node, int parent, List<int[]>[] graph) {
        int count = 0;

        for (int[] edge : graph[node]) {
            int next = edge[0];
            int cost = edge[1];

            if (next == parent) {
                continue;
            }

            count += cost;
            count += dfs(next, node, graph);
        }

        return count;
    }
}