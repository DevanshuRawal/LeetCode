class Solution {
    public int minFlips(int a, int b, int c) {
        int flips = 0;
        
        while (a > 0 || b > 0 || c > 0) {
            int bitA = a & 1;
            int bitB = b & 1;
            int bitC = c & 1;
            
            if (bitC == 0) {
                // Both bitA and bitB must be turned to 0
                flips += (bitA + bitB);
            } else {
                // At least one must be 1; if both are 0, flip one of them
                if (bitA == 0 && bitB == 0) {
                    flips += 1;
                }
            }
            
            // Shift to examine the next bit
            a >>= 1;
            b >>= 1;
            c >>= 1;
        }
        
        return flips;
    }
}