import java.util.*;

class SmallestInfiniteSet {

    private TreeSet<Integer> set;

    public SmallestInfiniteSet() {
        set = new TreeSet<>();

        for (int i = 1; i <= 1000; i++) {
            set.add(i);
        }
    }

    public int popSmallest() {
        int smallest = set.first();
        set.remove(smallest);
        return smallest;
    }

    public void addBack(int num) {
        set.add(num);
    }
}