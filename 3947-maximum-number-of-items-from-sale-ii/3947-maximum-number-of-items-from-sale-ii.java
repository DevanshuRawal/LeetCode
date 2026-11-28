import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxFactor = n;
        int minPrice = Integer.MAX_VALUE;

        int[] count = new int[maxFactor + 1];

        // Count how many items have each factor
        for (int[] item : items) {
            count[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        // Calculate how many free items each factor can generate
        int[] gain = new int[maxFactor + 1];

        for (int f = 1; f <= maxFactor; f++) {
            for (int j = f; j <= maxFactor; j += f) {
                gain[f] += count[j];
            }
            gain[f]--; // Exclude the item itself
        }

        // Store useful purchases: [price, gain]
        List<int[]> candidates = new ArrayList<>();

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            if (gain[factor] > 0 && (long) price <= 2L * minPrice) {
                candidates.add(new int[]{price, gain[factor]});
            }
        }

        // Buy cheaper double-value items first
        candidates.sort(Comparator.comparingInt(a -> a[0]));

        long ans = 0;
        long remainingBudget = budget;

        for (int[] candidate : candidates) {
            long price = candidate[0];
            long available = candidate[1];

            long buy = Math.min(available, remainingBudget / price);

            ans += buy * 2;
            remainingBudget -= buy * price;
        }

        // Spend remaining budget on the cheapest item
        ans += remainingBudget / minPrice;

        return (int) ans;
    }
}