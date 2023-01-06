import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

class TopKClosestNumsInSortedArray {
    
    static List<Integer> kClosestNums(int[] nums, int k, int x) {
        List<Integer> kClosestNums = new ArrayList<>(k);
    
        int leftPointer = getClosestValue(nums, x);
        int rightPointer = leftPointer + 1;
        
        while (kClosestNums.size() < k) {
            if (leftPointer >=0 && rightPointer < nums.length) {
                int leftDiff = Math.abs(nums[leftPointer] - x);
                int rightDiff = Math.abs(nums[rightPointer] - x);
                if (leftDiff <= rightDiff) {
                    kClosestNums.add(0, nums[leftPointer--]);
                } else {
                    kClosestNums.add(nums[rightPointer++]);
                }
            } else if (leftPointer >=0) {
                kClosestNums.add(0, nums[leftPointer--]);
            } else if (rightPointer < nums.length) {
                kClosestNums.add(nums[rightPointer++]);
            } else {
                break;
            }
        }
        
        return kClosestNums;
    }
    
    private static int getClosestValue(int[] nums, int x) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == x) {
                // if exactly found, return that element as closest value
                return mid;
            } else if (x < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        // it means value x is not found. In this case, left is always right+1 (for e.g. left is index 6, and right
        // is index 5). The value x is between these two pointers (in this example, nums[right] < x < nums[left]. So
        // return left-1 (which is right's value).
        if (left > 0) {
            return left - 1;
        }
        // In case left is 0, return 0. left is never negative.
        return left;
    }
    
    public static void main(String[] args) {
        System.out.println(kClosestNums(new int[]{5,6,20,50,100}, 3, 35));
    }
}