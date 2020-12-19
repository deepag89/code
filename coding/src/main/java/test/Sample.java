package test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Sample {

  public static void main(String [] arg) {
    List<Integer> numbers = Arrays.asList(1, 8, 10);
    System.out.println(Collections.binarySearch(numbers, 5));
  }

}
   

