package logging.filter;

import java.util.List;

public class FilterUtil {
  public static boolean evaluate(String rawMessage, List<Filter> filters) {
    if (filters == null) {
      return true;
    }
    // Pass through filters
    for (Filter filter : filters) {
      FilterResult filterResult = filter.evaluate(rawMessage);
      if (filterResult == FilterResult.DENY) {
        return false;
      }
      else if (filterResult == FilterResult.ACCEPT) {
        break;
      }
      else { // filterResult == NEUTRAL
        // evaluate next filter
      }
    }
    return true;
  }
}
