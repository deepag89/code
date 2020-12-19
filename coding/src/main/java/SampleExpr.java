import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class SampleExpr {

  static class Type {
    int num;

    public Type(int num) {
      this.num = num;
    }

    public Type() {}

    String nonStaticWithNoArg() {
      return "non-static hello with this.num " + this.num;
    }

    static String staticWithOneArg(Type num) {
      return "static hello with num " + num.num ;
    }

    String nonStaticWithOneArg(Type num) {
      return "non-static hello with num " + num.num;
    }
  }

  public static void main(String[] arg) {
    List<Type> numbers = Arrays.asList(new Type(10), new Type(20), new Type(30));

    List<Integer> numbers2 = Arrays.asList(10, 20, 30);

    System.out.println(
        numbers.stream()
            .map(Type::staticWithOneArg)
            .collect(Collectors.toList())
    );

    System.out.println(
        numbers.stream()
            .map(new Type()::nonStaticWithOneArg)
            .collect(Collectors.toList())
    );

    System.out.println(
        numbers.stream()
            .map(Type::nonStaticWithNoArg)
            .collect(Collectors.toList())
    );



//    System.out.println(
//        numbers2.stream()
//            .map(Integer::reverse)
//            .collect(Collectors.toList())
//    );

  }

}
   

