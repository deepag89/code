import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ThreeSumInArray {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> triplets = new LinkedList<>();
        int n = nums.length;
        Arrays.sort(nums);
        
        for (int fix = 0; fix < n; fix++) {
            int sumToFind = -nums[fix];
            int start = fix + 1, end = n - 1;
            
            while (start < end) {
                int curSum = nums[start] + nums[end];
                if (curSum == sumToFind) {
                    List<Integer> triplet = new LinkedList<>();
                    triplet.add(nums[start]);
                    triplet.add(nums[fix]);
                    triplet.add(nums[end]);
                    Collections.sort(triplet);
                    
                    if (!triplets.contains(triplet)) {
                        triplets.add(triplet);
                    }
                }
                if (curSum < sumToFind) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        
        return triplets;
    }
    
    public static void main(String[] args) {
        int nums[] = {-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6};
        
        System.out.println(threeSum(nums));
        
    }
    
    
}
