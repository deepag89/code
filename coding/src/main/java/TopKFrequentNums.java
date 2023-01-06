import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

class TopKFrequentNums {
    
    static class NumFreq {
        int num;
        int freq;
    
        public NumFreq(int num, int freq) {
            this.num = num;
            this.freq = freq;
        }
    
        public int getNum() {
            return num;
        }
    
        public int getFreq() {
            return freq;
        }
    }
    
    static List<Integer> topKFrequentNums(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        
        PriorityQueue<NumFreq> numFreqPriorityQueue = new PriorityQueue<>((nf1, nf2) -> nf1.getFreq() - nf2.getFreq());
        int i=0;
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (numFreqPriorityQueue.size() < k) {
                numFreqPriorityQueue.add(new NumFreq(entry.getKey(), entry.getValue()));
            } else {
                if (entry.getValue() > numFreqPriorityQueue.peek().getFreq()) {
                    numFreqPriorityQueue.poll();
                    numFreqPriorityQueue.add(new NumFreq(entry.getKey(), entry.getValue()));
                }
            }
        }
        
        return numFreqPriorityQueue
            .stream()
            .map(NumFreq::getNum)
            .collect(Collectors.toList());
    }
    public static void main(String[] args) {
        System.out.println(topKFrequentNums(new int[] {1, 3, 5, 12, 11, 12, 11}, 2));
        System.out.println(topKFrequentNums(new int[] {5, 12, 11, 3, 11}, 2));
    }
}