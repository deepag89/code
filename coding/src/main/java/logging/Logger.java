package logging;

import logging.appender.Appender;
import logging.common.LoggingLevel;
import logging.config.LoggingConfig;
import logging.filter.FilterUtil;

public class Logger {
  String name;
  LoggingConfig loggingConfig;

  Logger(String name, LoggingConfig loggingConfig) {
    this.name = name;
    this.loggingConfig = loggingConfig;
  }

  public boolean log(String message, LoggingLevel requestedLoggingLevel) {
    if (!loggingConfig.getLoggingLevel().isAllowed(requestedLoggingLevel))
      return false;

    if (!FilterUtil.evaluate(message, loggingConfig.getFilters()))
      return false;

    loggingConfig.getAppenders()
        .forEach(appender -> appender.append(message));
    return true;
  }
}
