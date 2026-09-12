import java.util.*;

class Solution {
    static class T {
        long score;
        List<Integer> ids;

        T(long s, List<Integer> i) {
            score = s;
            ids = i;
        }
    }

    static class I {
        int l, r, w, id;

        I(int l, int r, int w, int id) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.id = id;
        }
    }

    T[][] dp;
    List<I> a;

    public int[] maximumWeight(List<List<Integer>> intervals) {
        a = new ArrayList<>();

        for (int i = 0; i < intervals.size(); i++) {
            List<Integer> x = intervals.get(i);
            a.add(new I(x.get(0), x.get(1), x.get(2), i));
        }

        a.sort(Comparator.comparingInt(x -> x.l));
        dp = new T[a.size()][5];

        return solve(0, 4).ids.stream().mapToInt(x -> x).toArray();
    }

    T solve(int i, int k) {
        if (i == a.size() || k == 0)
            return new T(0, new ArrayList<>());

        if (dp[i][k] != null)
            return dp[i][k];

        T skip = solve(i + 1, k);

        I x = a.get(i);
        int j = next(i + 1, x.r);

        T temp = solve(j, k - 1);
        List<Integer> ids = new ArrayList<>(temp.ids);
        ids.add(x.id);
        Collections.sort(ids);

        T take = new T(x.w + temp.score, ids);

        if (take.score > skip.score ||
            (take.score == skip.score && compare(take.ids, skip.ids) < 0))
            return dp[i][k] = take;

        return dp[i][k] = skip;
    }

    int next(int l, int r) {
        int h = a.size();

        while (l < h) {
            int m = (l + h) / 2;

            if (a.get(m).l > r)
                h = m;
            else
                l = m + 1;
        }

        return l;
    }

    int compare(List<Integer> a, List<Integer> b) {
        for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
            if (!a.get(i).equals(b.get(i)))
                return Integer.compare(a.get(i), b.get(i));
        }

        return Integer.compare(a.size(), b.size());
    }
}