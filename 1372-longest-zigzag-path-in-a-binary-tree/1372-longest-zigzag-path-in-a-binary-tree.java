class Solution {
    int max = 0;

    public int longestZigZag(TreeNode root) {
        dfs(root, 0, 0);
        return max;
    }

    private void dfs(TreeNode node, int left, int right) {
        if (node == null) {
            return;
        }

        max = Math.max(max, Math.max(left, right));

        // Go left: previous move should be right
        dfs(node.left, right + 1, 0);

        // Go right: previous move should be left
        dfs(node.right, 0, left + 1);
    }
}