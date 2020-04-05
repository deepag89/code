package logging.config;

import java.util.List;
import logging.common.LoggingLevel;
import logging.appender.Appender;
import logging.filter.Filter;

public class LoggingConfig {
  LoggingLevel loggingLevel;
  List<Filter> filters;
  List<Appender> appenders;

  // logging level and appender are required
  public LoggingConfig(LoggingLevel loggingLevel, List<Appender> appenders) {
    this.loggingLevel = loggingLevel;
    this.appenders = appenders;
  }

  public LoggingConfig(LoggingLevel loggingLevel, List<Filter> filters,
      List<Appender> appenders) {
    this.loggingLevel = loggingLevel;
    this.filters = filters;
    this.appenders = appenders;
  }

  public LoggingLevel getLoggingLevel() {
    return loggingLevel;
  }

  public List<Filter> getFilters() {
    return filters;
  }

  public List<Appender> getAppenders() {
    return appenders;
  }
}
