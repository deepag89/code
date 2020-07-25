package test;
import java.util.Arrays;
import java.util.List;

class Sample {

  public static long countSubarrays(List<Integer> numbers, int k) {
    // Write your code here
    int startIdx = 0;
    int prod = 1;
    int total = 0;
    for (int curIdx = 0; curIdx < numbers.size(); curIdx++) {
      prod = prod * numbers.get(curIdx);
      while (prod > k && startIdx <= curIdx) {
        prod = prod / numbers.get(startIdx);
        startIdx++;
      }
      if (startIdx <= curIdx) {
        total += (curIdx - startIdx) + 1;
      }
    }
    return total;
  }

  public static void main(String [] arg) {
      List<Integer> numbers = Arrays.asList(1, 1, 2);
      System.out.println(countSubarrays(numbers, 1));
  }

}
   

