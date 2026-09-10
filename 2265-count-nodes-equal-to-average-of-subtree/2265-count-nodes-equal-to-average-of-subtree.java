class Solution {
    int ans = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return ans;
    }

    int[] dfs(TreeNode node) {
        if (node == null) return new int[]{0, 0};

        int[] l = dfs(node.left);
        int[] r = dfs(node.right);

        int sum = l[0] + r[0] + node.val;
        int count = l[1] + r[1] + 1;

        if (sum / count == node.val)
            ans++;

        return new int[]{sum, count};
    }
}