class Solution {
public String[] createGrid(int m, int n) {
String[] grid = new String[m];


    for (int i = 0; i < m; i++) {
        char[] row = new char[n];
        java.util.Arrays.fill(row, '#');
        grid[i] = new String(row);
    }

    char[][] arr = new char[m][n];

    for (int i = 0; i < m; i++) {
        java.util.Arrays.fill(arr[i], '#');
    }

    // Path: first move right, then move down
    for (int j = 0; j < n; j++) {
        arr[0][j] = '.';
    }

    for (int i = 1; i < m; i++) {
        arr[i][n - 1] = '.';
    }

    for (int i = 0; i < m; i++) {
        grid[i] = new String(arr[i]);
    }

    return grid;
}


}
