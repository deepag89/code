package logging.appender;

import java.util.List;
import logging.filter.Filter;

public class ConsoleAppender extends Appender {

  // pattern is required
  public ConsoleAppender(String pattern) {
    super(pattern);
  }

  public ConsoleAppender(List<Filter> filters, String pattern) {
    super(filters, pattern);
  }

  @Override boolean handleDecoratedMessage(String decoratedMessage) {
    System.out.println(decoratedMessage);
    return true;
  }
}
