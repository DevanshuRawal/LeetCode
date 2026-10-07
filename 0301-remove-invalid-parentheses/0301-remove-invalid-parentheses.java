import java.util.*;

class Solution {

    Set<String> result = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {

        int removeLeft = 0;
        int removeRight = 0;

        // Find minimum brackets to remove
        for (char c : s.toCharArray()) {

            if (c == '(') {
                removeLeft++;

            } else if (c == ')') {

                if (removeLeft > 0) {
                    removeLeft--;
                } else {
                    removeRight++;
                }
            }
        }

        dfs(s, 0, removeLeft, removeRight, 0, new StringBuilder());

        return new ArrayList<>(result);
    }

    private void dfs(String s, int index,
                     int removeLeft,
                     int removeRight,
                     int open,
                     StringBuilder path) {

        // Invalid state
        if (open < 0) {
            return;
        }

        // End
        if (index == s.length()) {

            if (removeLeft == 0 &&
                removeRight == 0 &&
                open == 0) {

                result.add(path.toString());
            }

            return;
        }

        char c = s.charAt(index);

        // Remove current character
        if (c == '(' && removeLeft > 0) {

            dfs(
                s,
                index + 1,
                removeLeft - 1,
                removeRight,
                open,
                path
            );
        }

        if (c == ')' && removeRight > 0) {

            dfs(
                s,
                index + 1,
                removeLeft,
                removeRight - 1,
                open,
                path
            );
        }

        // Keep current character
        if (c == '(') {

            path.append(c);

            dfs(
                s,
                index + 1,
                removeLeft,
                removeRight,
                open + 1,
                path
            );

            path.deleteCharAt(path.length() - 1);

        } else if (c == ')') {

            if (open > 0) {

                path.append(c);

                dfs(
                    s,
                    index + 1,
                    removeLeft,
                    removeRight,
                    open - 1,
                    path
                );

                path.deleteCharAt(path.length() - 1);
            }

        } else {

            // Letter
            path.append(c);

            dfs(
                s,
                index + 1,
                removeLeft,
                removeRight,
                open,
                path
            );

            path.deleteCharAt(path.length() - 1);
        }
    }
}