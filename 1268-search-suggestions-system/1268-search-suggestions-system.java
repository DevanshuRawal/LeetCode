import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> result = new ArrayList<>();
        
        // 1. Sort lexicographically so matching ranges are contiguous and ordered
        Arrays.sort(products);
        
        int left = 0;
        int right = products.length - 1;
        
        for (int i = 0; i < searchWord.length(); i++) {
            char c = searchWord.charAt(i);
            
            // Narrow left pointer
            while (left <= right && (products[left].length() <= i || products[left].charAt(i) != c)) {
                left++;
            }
            
            // Narrow right pointer
            while (left <= right && (products[right].length() <= i || products[right].charAt(i) != c)) {
                right--;
            }
            
            // Collect up to 3 lexicographically smallest matches
            List<String> suggestions = new ArrayList<>();
            for (int j = left; j <= Math.min(right, left + 2); j++) {
                suggestions.add(products[j]);
            }
            
            result.add(suggestions);
        }
        
        return result;
    }
}