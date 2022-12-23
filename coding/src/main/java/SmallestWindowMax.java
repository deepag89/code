import java.util.Arrays;

class SmallestWindowMax {
    
    static int getMinWindowsWithKSum(int[] nums, int k) {
        int curSum = 0;
        int winStart = 0;
        int minWindowSize = Integer.MAX_VALUE;
        for (int cur = 0; cur < nums.length; cur++) {
            curSum += nums[cur];
            while (curSum >= k && winStart < cur && curSum - nums[winStart] >= k) {
                curSum -= nums[winStart];
                winStart++;
            }
            if (curSum >= k) {
                minWindowSize = Math.min(minWindowSize, cur - winStart + 1);
            }
        }
        
        return minWindowSize;
    }
    
    public static void main(String[] args) {
        System.out.println(getMinWindowsWithKSum(new int[] {1, 3, 2, 6, 1, 4, 1, 8, 2}, 30));
    }
}