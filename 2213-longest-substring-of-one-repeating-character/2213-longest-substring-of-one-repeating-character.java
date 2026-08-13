class Solution {
    char[] arr;
    int[] left;
    int[] right;
    int[] best;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        arr = s.toCharArray();

        int n = arr.length;

        left = new int[4 * n];
        right = new int[4 * n];
        best = new int[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[queryIndices.length];

        for (int i = 0; i < queryIndices.length; i++) {
            int index = queryIndices[i];
            char c = queryCharacters.charAt(i);

            arr[index] = c;

            update(1, 0, n - 1, index);

            ans[i] = best[1];
        }

        return ans;
    }

    private void build(int node, int l, int r) {
        if (l == r) {
            left[node] = 1;
            right[node] = 1;
            best[node] = 1;
            return;
        }

        int mid = l + (r - l) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        merge(node, l, mid, r);
    }

    private void update(int node, int l, int r, int index) {
        if (l == r) {
            left[node] = 1;
            right[node] = 1;
            best[node] = 1;
            return;
        }

        int mid = l + (r - l) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index);
        } else {
            update(node * 2 + 1, mid + 1, r, index);
        }

        merge(node, l, mid, r);
    }

    private void merge(int node, int l, int mid, int r) {
        int L = node * 2;
        int R = node * 2 + 1;

        left[node] = left[L];
        right[node] = right[R];

        best[node] = Math.max(best[L], best[R]);

        if (arr[mid] == arr[mid + 1]) {
            best[node] = Math.max(
                best[node],
                right[L] + left[R]
            );

            if (left[L] == mid - l + 1) {
                left[node] = left[L] + left[R];
            }

            if (right[R] == r - mid) {
                right[node] = right[R] + right[L];
            }
        }
    }
}