import java.util.*;

class Solution {
    public double[] calcEquation(
            List<List<String>> equations,
            double[] values,
            List<List<String>> queries) {

        Map<String, Map<String, Double>> graph = new HashMap<>();

        // Build graph
        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            double value = values[i];

            graph.putIfAbsent(a, new HashMap<>());
            graph.putIfAbsent(b, new HashMap<>());

            graph.get(a).put(b, value);
            graph.get(b).put(a, 1.0 / value);
        }

        double[] answer = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            String start = queries.get(i).get(0);
            String end = queries.get(i).get(1);

            answer[i] = dfs(start, end, graph, new HashSet<>());
        }

        return answer;
    }

    private double dfs(
            String current,
            String target,
            Map<String, Map<String, Double>> graph,
            Set<String> visited) {

        if (!graph.containsKey(current) || !graph.containsKey(target)) {
            return -1.0;
        }

        if (current.equals(target)) {
            return 1.0;
        }

        visited.add(current);

        for (Map.Entry<String, Double> entry :
                graph.get(current).entrySet()) {

            String next = entry.getKey();
            double weight = entry.getValue();

            if (visited.contains(next)) {
                continue;
            }

            double result = dfs(next, target, graph, visited);

            if (result != -1.0) {
                return weight * result;
            }
        }

        return -1.0;
    }
}