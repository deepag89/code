import java.util.Arrays;

class SlidingWindowAverage {
    
    static double[] getKSlidingWindowAverages(int[] nums, int k) {
        double[] averages = new double[nums.length - k + 1];
        
        int sum = 0;
        for (int cur = 0; cur < nums.length; cur++) {
            sum += nums[cur];
            if (cur >= k - 1) {
                averages[cur-(k-1)] = (double) sum / k;
                sum -= nums[cur-(k-1)];
            }
        }
        
        return averages;
    }
    
    public static void main(String[] args) {
        System.out.println(Arrays.toString(getKSlidingWindowAverages(new int[] {1, 3, 2, 6, -1, 4, 1, 8, 2}, 5)));
    }
}