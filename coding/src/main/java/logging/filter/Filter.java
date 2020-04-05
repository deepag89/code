package logging.filter;

public interface Filter {
  FilterResult evaluate(String rawMessage);
}
