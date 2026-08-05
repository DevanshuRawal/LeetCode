import java.util.*;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {

        // Graph banao
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : invocations) {
            graph.get(edge[0]).add(edge[1]);
        }

        // Suspicious methods find karo
        boolean[] suspicious = new boolean[n];

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(k);
        suspicious[k] = true;

        while (!queue.isEmpty()) {

            int current = queue.poll();

            for (int next : graph.get(current)) {

                if (!suspicious[next]) {
                    suspicious[next] = true;
                    queue.offer(next);
                }
            }
        }

        // Check karo koi non-suspicious method
        // suspicious method ko invoke to nahi kar raha
        for (int[] edge : invocations) {

            int from = edge[0];
            int to = edge[1];

            if (!suspicious[from] && suspicious[to]) {

                // Suspicious group safely remove nahi ho sakta
                List<Integer> all = new ArrayList<>();

                for (int i = 0; i < n; i++) {
                    all.add(i);
                }

                return all;
            }
        }

        // Suspicious methods remove kar do
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            if (!suspicious[i]) {
                ans.add(i);
            }
        }

        return ans;
    }
}