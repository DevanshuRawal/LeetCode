import java.util.*;

class Solution {

    static class Group {
        int start;
        int length;

        Group(int start, int length) {
            this.start = start;
            this.length = length;
        }
    }

    static class SparseTable {

        int[][] st;
        int[] log;

        SparseTable(int[] arr) {
            int n = arr.length;

            log = new int[n + 1];

            for (int i = 2; i <= n; i++) {
                log[i] = log[i / 2] + 1;
            }

            int levels = log[n] + 1;
            st = new int[levels][n];

            st[0] = arr.clone();

            for (int level = 1; level < levels; level++) {
                int len = 1 << level;
                int half = len / 2;

                for (int i = 0; i + len <= n; i++) {
                    st[level][i] = Math.max(
                            st[level - 1][i],
                            st[level - 1][i + half]
                    );
                }
            }
        }

        int query(int l, int r) {
            int len = r - l + 1;
            int level = log[len];

            return Math.max(
                    st[level][l],
                    st[level][r - (1 << level) + 1]
            );
        }
    }

    public List<Integer> maxActiveSectionsAfterTrade(
            String s, int[][] queries) {

        int n = s.length();

        int ones = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                ones++;
            }
        }

        List<Group> zeroGroups = new ArrayList<>();

        // Important:
        // har index ke liye previous/current zero group index
        int[] zeroGroupIndex = new int[n];

        for (int i = 0; i < n; i++) {

            if (s.charAt(i) == '0') {

                if (i > 0 && s.charAt(i - 1) == '0') {
                    zeroGroups.get(
                            zeroGroups.size() - 1
                    ).length++;
                } else {
                    zeroGroups.add(
                            new Group(i, 1)
                    );
                }
            }

            zeroGroupIndex[i] =
                    zeroGroups.size() - 1;
        }

        if (zeroGroups.isEmpty()) {
            return Collections.nCopies(
                    queries.length,
                    ones
            );
        }

        // Adjacent zero groups ka sum
        int[] merge =
                new int[zeroGroups.size() - 1];

        for (int i = 0; i < merge.length; i++) {

            merge[i] =
                    zeroGroups.get(i).length
                    + zeroGroups.get(i + 1).length;
        }

        SparseTable st =
                new SparseTable(merge);

        List<Integer> answer =
                new ArrayList<>();

        for (int[] query : queries) {

            int l = query[0];
            int r = query[1];

            int left = zeroGroupIndex[l] == -1
                    ? -1
                    : zeroGroups.get(
                            zeroGroupIndex[l]
                      ).length
                      - (
                            l
                            - zeroGroups.get(
                                zeroGroupIndex[l]
                              ).start
                        );

            int right = zeroGroupIndex[r] == -1
                    ? -1
                    : r
                      - zeroGroups.get(
                            zeroGroupIndex[r]
                        ).start
                      + 1;

            /*
             * Fully covered adjacent zero groups:
             *
             * start = group containing l + 1
             *
             * end =
             *   if r is 1:
             *      group containing r
             *   else:
             *      group containing r - 1
             */
            int startGroup =
                    zeroGroupIndex[l] + 1;

            int endGroup =
                    s.charAt(r) == '1'
                    ? zeroGroupIndex[r]
                    : zeroGroupIndex[r] - 1;

            int startPair = startGroup;
            int endPair = endGroup - 1;

            int activeSections = ones;

            /*
             * Special case:
             * Query starts and ends with 0
             * and only two boundary zero groups
             */
            if (s.charAt(l) == '0'
                    && s.charAt(r) == '0'
                    && zeroGroupIndex[l] + 1
                    == zeroGroupIndex[r]) {

                activeSections =
                        Math.max(
                                activeSections,
                                ones + left + right
                        );
            }

            /*
             * Fully covered adjacent groups
             */
            else if (startPair <= endPair) {

                activeSections =
                        Math.max(
                                activeSections,
                                ones
                                + st.query(
                                    startPair,
                                    endPair
                                )
                        );
            }

            /*
             * Left partial zero group
             * + next complete zero group
             */
            if (s.charAt(l) == '0'
                    && zeroGroupIndex[l] + 1
                    <= endGroup) {

                activeSections =
                        Math.max(
                                activeSections,
                                ones
                                + left
                                + zeroGroups.get(
                                    zeroGroupIndex[l] + 1
                                  ).length
                        );
            }

            /*
             * Previous complete zero group
             * + right partial zero group
             */
            if (s.charAt(r) == '0'
                    && zeroGroupIndex[l]
                    < zeroGroupIndex[r] - 1) {

                activeSections =
                        Math.max(
                                activeSections,
                                ones
                                + right
                                + zeroGroups.get(
                                    zeroGroupIndex[r] - 1
                                  ).length
                        );
            }

            answer.add(activeSections);
        }

        return answer;
    }
}