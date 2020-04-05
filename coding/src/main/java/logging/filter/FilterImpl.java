package logging.filter;

public class FilterImpl implements Filter {
  @Override public FilterResult evaluate(String rawMessage) {
    return FilterResult.ACCEPT;
  }
}
