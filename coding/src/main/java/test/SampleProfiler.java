package test;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

class SampleProfiler {

  private static class MyClass {
  }

  public static void main(String [] arg) {
    randomLinkListFun();
    randomArrayFun();
    System.out.println("done");
  }

  private static List randomLinkListFun() {
    List l = new LinkedList();

    for (int i=0; i<12233; i++) {
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      l.add(new MyClass());
    }

    return l;
  }

  private static List randomArrayFun() {
    List l = new ArrayList();

    for (int i=0; i<12233; i++) {
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      l.add(new MyClass());
    }

    return l;
  }

}
   

