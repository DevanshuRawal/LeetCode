import java.util.*;

class Solution {
private List<Integer>[] adj;
private int[][] up;
private int[] depth, tin, tout;
private int timer, LOG;


public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
    adj = new ArrayList[n];

    for (int i = 0; i < n; i++) {
        adj[i] = new ArrayList<>();
    }

    for (int[] edge : edges) {
        adj[edge[0]].add(edge[1]);
        adj[edge[1]].add(edge[0]);
    }

    LOG = 1;
    while ((1 << LOG) <= n) {
        LOG++;
    }

    up = new int[LOG][n];
    depth = new int[n];
    tin = new int[n];
    tout = new int[n];

    timer = 0;
    dfs(0, 0);

    Fenwick bit = new Fenwick(n + 2);
    char[] chars = s.toCharArray();

    for (int i = 0; i < n; i++) {
        int mask = 1 << (chars[i] - 'a');
        bit.update(tin[i], mask);
        bit.update(tout[i] + 1, mask);
    }

    List<Boolean> result = new ArrayList<>();

    for (String query : queries) {
        String[] parts = query.split(" ");

        if (parts[0].equals("update")) {
            int node = Integer.parseInt(parts[1]);
            char c = parts[2].charAt(0);

            if (chars[node] != c) {
                int delta = (1 << (chars[node] - 'a'))
                          ^ (1 << (c - 'a'));

                bit.update(tin[node], delta);
                bit.update(tout[node] + 1, delta);

                chars[node] = c;
            }
        } else {
            int u = Integer.parseInt(parts[1]);
            int v = Integer.parseInt(parts[2]);

            int ancestor = lca(u, v);

            int maskU = bit.query(tin[u]);
            int maskV = bit.query(tin[v]);
            int lcaMask = 1 << (chars[ancestor] - 'a');

            int pathMask = maskU ^ maskV ^ lcaMask;

            result.add((pathMask & (pathMask - 1)) == 0);
        }
    }

    return result;
}

private void dfs(int node, int parent) {
    tin[node] = ++timer;
    up[0][node] = parent;

    for (int j = 1; j < LOG; j++) {
        up[j][node] = up[j - 1][up[j - 1][node]];
    }

    for (int next : adj[node]) {
        if (next == parent) {
            continue;
        }

        depth[next] = depth[node] + 1;
        dfs(next, node);
    }

    tout[node] = timer;
}

private int lca(int u, int v) {
    if (depth[u] < depth[v]) {
        int temp = u;
        u = v;
        v = temp;
    }

    int diff = depth[u] - depth[v];

    for (int j = 0; j < LOG; j++) {
        if (((diff >> j) & 1) != 0) {
            u = up[j][u];
        }
    }

    if (u == v) {
        return u;
    }

    for (int j = LOG - 1; j >= 0; j--) {
        if (up[j][u] != up[j][v]) {
            u = up[j][u];
            v = up[j][v];
        }
    }

    return up[0][u];
}

private static class Fenwick {
    private final int[] tree;
    private final int n;

    Fenwick(int n) {
        this.n = n;
        tree = new int[n + 1];
    }

    void update(int index, int value) {
        for (int i = index; i <= n; i += i & -i) {
            tree[i] ^= value;
        }
    }

    int query(int index) {
        int result = 0;

        for (int i = index; i > 0; i -= i & -i) {
            result ^= tree[i];
        }

        return result;
    }
}


}
