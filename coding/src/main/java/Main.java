import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    Map<String, String> map = new HashMap<>();
    map.put("s1", "v1");
    map.put("s2", null);
    map.values()
        .stream()
        .map(val -> {
          System.out.println(val + " -> " + val.hashCode());
          return val.hashCode();
        })
    .collect(Collectors.toSet());
//    System.out.println(map.values());


  }
}
