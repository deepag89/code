// https://www.geeksforgeeks.org/count-possible-decodings-given-digit-sequence/

import java.util.Set;
import java.util.TreeSet;

class maxProductSubArray {
    public int maxProduct(int[] nums) {
        int neg = 1;
        int pos = 1;

        int max  = -99999999;

        for (int i=0; i<nums.length; i++) {
            if (nums[i] >=0) {
                neg *= nums[i];
                pos *= nums[i];
            }
            else {
                int nPast = neg;
                neg = pos * nums[i];
                pos = nPast * nums[i];
            }

            max = Math.max(max, pos);

            pos = Math.max(1, pos);
        }

        return max;
    }
}
