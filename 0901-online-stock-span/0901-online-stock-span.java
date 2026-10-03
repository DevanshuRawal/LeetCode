import java.util.ArrayDeque;
import java.util.Deque;

class StockSpanner {

    // Store pairs of [price, span]
    private final Deque<int[]> stack;

    public StockSpanner() {
        this.stack = new ArrayDeque<>();
    }
    
    public int next(int price) {
        int span = 1;
        
        // Pop all previous days whose price is <= current price
        // and accumulate their pre-calculated spans
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            span += stack.pop()[1];
        }
        
        // Push the current price and its accumulated span
        stack.push(new int[]{price, span});
        
        return span;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */