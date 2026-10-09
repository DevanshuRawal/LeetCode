class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int open = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                open++;
            } else {
                // Check whether we have a pair of "))"
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++;
                } else {
                    insertions++;
                }

                // Match this "))" with an opening "("
                if (open > 0) {
                    open--;
                } else {
                    insertions++;
                }
            }
        }

        // Each unmatched "(" needs two ")"
        insertions += open * 2;

        return insertions;
    }
}