import java.util.*;

class Solution {
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> map = new HashMap<>();

        map.put(0L, 1);

        return dfs(root, 0L, targetSum, map);
    }

    private int dfs(TreeNode node, long currentSum,
                    int targetSum, Map<Long, Integer> map) {

        if (node == null) {
            return 0;
        }

        currentSum += node.val;

        int count = map.getOrDefault(
            currentSum - targetSum, 0
        );

        map.put(
            currentSum,
            map.getOrDefault(currentSum, 0) + 1
        );

        count += dfs(node.left, currentSum, targetSum, map);
        count += dfs(node.right, currentSum, targetSum, map);

        // Backtrack
        map.put(currentSum, map.get(currentSum) - 1);

        return count;
    }
}