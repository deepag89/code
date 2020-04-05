package logging;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import logging.appender.Appender;
import logging.appender.ConsoleAppender;
import logging.common.LoggingLevel;
import logging.config.LoggingConfig;
import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;

public class LoggingManager {
  static Map<String, Logger> loggerNameToLoggerCache;
  static Trie<String, LoggingConfig> loggerNamesToConfigTrie;
  public static String ROOT_LOGGER = "ROOT";
  public static LoggingLevel DEFAULT_LEVEL = LoggingLevel.INFO;

  static {
    loggerNameToLoggerCache = new HashMap<>();
    loggerNamesToConfigTrie = new PatriciaTrie<>();

    loggerNamesToConfigTrie.put(
        ROOT_LOGGER,
        new LoggingConfig(
            DEFAULT_LEVEL,
            Arrays.asList(new ConsoleAppender("%s"))
        )
    );
  }

  public static Logger getLogger(String name) {
    return loggerNameToLoggerCache.computeIfAbsent(name,
        (n) ->createNewLogger(n, null, null));
  }

  public static Logger getLogger(String name, LoggingLevel level, List<Appender> appenders) {
    LoggingConfig loggingConfig = new LoggingConfig(level, appenders);
    loggerNamesToConfigTrie.put(name, loggingConfig);
    loggerNameToLoggerCache.put(name, new Logger(name, loggingConfig));
    return loggerNameToLoggerCache.get(name);
  }

  private static Logger createNewLogger(String name, LoggingLevel level, List<Appender> appenders) {
    LoggingConfig loggingConfig = getLoggingConfig(ROOT_LOGGER + "." + name, level, appenders);
    return new Logger(name, loggingConfig);
  }

  private static LoggingConfig getLoggingConfig(String name, LoggingLevel loggingLevel, List<Appender> appenders) {
    return loggerNamesToConfigTrie.computeIfAbsent(
        name,
        loggerName -> {
          int lastDotIndex = loggerName.lastIndexOf(".");
          if (lastDotIndex == -1) {
            return new LoggingConfig(loggingLevel, appenders);
          }
          String parentLoggerName = loggerName.substring(0, lastDotIndex);
          return getLoggingConfig(parentLoggerName, loggingLevel, appenders);
        });
  }


}
