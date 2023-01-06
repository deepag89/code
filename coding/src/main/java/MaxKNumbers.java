import java.util.Arrays;
import java.util.PriorityQueue;

class MaxKNumbers {
    
    static Integer[] topKNums(int[] nums, int k) {
        PriorityQueue<Integer> topKNumsHeap = new PriorityQueue<>((n1, n2) -> n1-n2);
        Integer[] topKNumsInts = new Integer[k];
        
        for (int num : nums) {
            if (topKNumsHeap.size() < k) {
                topKNumsHeap.add(num);
            } else {
                if (topKNumsHeap.peek() < num) {
                    topKNumsHeap.remove();
                    topKNumsHeap.add(num);
                }
            }
        }
        
        return topKNumsHeap.toArray(topKNumsInts);
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKNums(new int[] {10, 3, 25, 12, 8, 0, -99, 99, 13, 1}, 1)));
    }
}