package logging.common;

public enum LoggingLevel {
  ERROR(2),
  WARNING(3),
  INFO(4),
  DEBUG(5),
  TRACE(6);

  LoggingLevel(int levelNumber) {
    this.levelNumber = levelNumber;
  }

  public boolean isAllowed(LoggingLevel requestedLevel) {
    return requestedLevel.levelNumber <= this.levelNumber;
  }

  private int levelNumber;
}
