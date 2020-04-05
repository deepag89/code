package logging.appender;

import java.util.List;
import logging.filter.Filter;
import logging.filter.FilterResult;
import logging.filter.FilterUtil;
import logging.pattern.PatternUtil;

public abstract class Appender {
  List<Filter> filters;
  String pattern;

  // pattern is required
  Appender(String pattern) {
    this.pattern = pattern;
  }

  public Appender(List<Filter> filters, String pattern) {
    this.filters = filters;
    this.pattern = pattern;
  }

  public boolean append(String rawMessage) {
    if (!FilterUtil.evaluate(rawMessage, filters))
      return false;

    String decoratedMessage = PatternUtil.resolveMessageusingPattern(rawMessage, pattern);
    return handleDecoratedMessage(decoratedMessage);
  }

  abstract boolean handleDecoratedMessage(String decoratedMessage);
}
