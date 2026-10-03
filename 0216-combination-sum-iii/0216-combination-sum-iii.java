import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, k, n, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int k, int remain, List<Integer> current, List<List<Integer>> result) {
        // Base case: formed a combination of size k
        if (current.size() == k) {
            if (remain == 0) {
                result.add(new ArrayList<>(current));
            }
            return;
        }

        // Loop through available numbers
        for (int i = start; i <= 9; i++) {
            // Prune: if the current number is already larger than the required remaining sum,
            // subsequent numbers (which are greater) will also exceed it.
            if (i > remain) {
                break;
            }

            // Choose
            current.add(i);

            // Explore next number (must be > i to avoid duplicate numbers)
            backtrack(i + 1, k, remain - i, current, result);

            // Backtrack (un-choose)
            current.remove(current.size() - 1);
        }
    }
}