package logging;

import java.util.Arrays;
import logging.appender.ConsoleAppender;
import logging.common.LoggingLevel;

public class Main {
  public static void main(String[] args) {
    Logger logger = LoggingManager.getLogger("io.util.logger");
    logger.log("test message", LoggingLevel.INFO);

    Logger coronaLogger = LoggingManager.getLogger("io.util.logger.corona", LoggingLevel.DEBUG,
        Arrays.asList(new ConsoleAppender("")));
    coronaLogger.log("Log Coro na", LoggingLevel.TRACE);
  }
}
