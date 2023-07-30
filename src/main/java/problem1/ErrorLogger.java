package problem1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ErrorLogger is a class that stores a log of errors.
 */

public class ErrorLogger {

  private List<String> log;

  /**
   * Constructor for ErrorLogger.
   */
  public ErrorLogger() {
    this.log = new ArrayList<>();
  }

  /**
   * Add an event to the log, an event is an error message.
   *
   * @param event - the event to be added
   */
  public void addLog(String event) {
    this.log.add(event);
  }

  /**
   * Check if the log is empty.
   *
   * @return - true if the log is empty, false otherwise
   */
  public boolean isEmpty() {
    return this.log.size() == 0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorLogger that = (ErrorLogger) o;
    return Objects.equals(log, that.log);
  }

  @Override
  public int hashCode() {
    return Objects.hash(log);
  }

  @Override
  public String toString() {
    if (this.log.isEmpty()) {
      return "Empty log";
    }
    String out = "";
    for (String entry : this.log) {
      out += entry + System.lineSeparator();
    }
    return out;
  }
}
