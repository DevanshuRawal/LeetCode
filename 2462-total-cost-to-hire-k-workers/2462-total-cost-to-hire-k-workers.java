import java.util.*;

class Solution {
    public long totalCost(int[] costs, int k, int candidates) {
        int n = costs.length;

        PriorityQueue<Integer> left = new PriorityQueue<>();
        PriorityQueue<Integer> right = new PriorityQueue<>();

        int l = 0;
        int r = n - 1;

        // Left candidates
        while (l <= r && left.size() < candidates) {
            left.offer(costs[l++]);
        }

        // Right candidates
        while (l <= r && right.size() < candidates) {
            right.offer(costs[r--]);
        }

        long total = 0;

        for (int i = 0; i < k; i++) {

            if (right.isEmpty() ||
                (!left.isEmpty() && left.peek() <= right.peek())) {

                total += left.poll();

                if (l <= r) {
                    left.offer(costs[l++]);
                }

            } else {

                total += right.poll();

                if (l <= r) {
                    right.offer(costs[r--]);
                }
            }
        }

        return total;
    }
}