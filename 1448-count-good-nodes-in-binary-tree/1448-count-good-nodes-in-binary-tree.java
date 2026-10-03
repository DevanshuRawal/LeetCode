class Solution {
    public int goodNodes(TreeNode root) {
        return dfs(root, root.val);
    }

    private int dfs(TreeNode node, int maxValue) {
        if (node == null) {
            return 0;
        }

        int count = 0;

        if (node.val >= maxValue) {
            count = 1;
            maxValue = node.val;
        }

        count += dfs(node.left, maxValue);
        count += dfs(node.right, maxValue);

        return count;
    }
}