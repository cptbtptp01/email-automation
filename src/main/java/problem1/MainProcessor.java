package problem1;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * MainProcessor is a deals with the command-line arguments, which stores data from CSV file, and a
 * log message if there is any error.
 */
public class MainProcessor {

  private Map<String, String> data;
  private ErrorLogger log;

  /**
   * Constructor for MainProcessor
   *
   * @param args The command-line arguments
   */
  public MainProcessor(String[] args) {
    this.log = new ErrorLogger();
    this.data = this.populateData(args);
  }

  /**
   * Helper method to verify the command-line arguments based on different error types.
   *
   * @param args The command-line arguments
   * @return true if the command-line arguments are valid, false otherwise
   */
  private boolean verifyInput(String[] args) {
    ErrorType validationResult = InputValidator.isValidInput(args);
    switch (validationResult) {
      case INSUFFICIENT_ARGS:
        this.log.addLog("Error: insufficient args provided.");
        break;
      case EMAIL_TEMPLATE_MISSING_ERROR:
        this.log.addLog("Error: --email provided but no --email-template was given.");
        break;
      case EMAIL_ERROR:
        this.log.addLog("Error: no --email provided but --email-template was given.");
        break;
      case LETTER_TEMPLATE_MISSING_ERROR:
        this.log.addLog("Error: --letter provided but no --letter-template was given.");
        break;
      case LETTER_ERROR:
        this.log.addLog("Error: no --letter provided but --letter-template was given.");
        break;
      case OUTPUT_DIR_ERROR:
        this.log.addLog("Error: no --output-dir provided.");
        break;
      case CSV_FILE_ERROR:
        this.log.addLog("Error: no --csv-file provided.");
        break;
      case VALID:
        return true;
    }
    return false;
  }

  /**
   * Helper method to populate the data from the command-line arguments.
   *
   * @param args The command-line arguments
   * @return a map where the key is the member's property and the value is the member's value
   */
  private Map<String, String> populateData(String[] args) {
    Map<String, String> data = new HashMap<>();
    if (!this.verifyInput(args)) {
      return data; // return empty map if input is invalid
    }
    return ProcessInput.Read(args);
  }

  /**
   * Getter for data
   *
   * @return a map where the key is the member's property and the value is the member's value
   */
  public Map<String, String> getData() {
    return data;
  }

  /**
   * Getter for log
   *
   * @return a log message if there is any error
   */
  public ErrorLogger getLog() {
    return log;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MainProcessor that = (MainProcessor) o;
    return Objects.equals(data, that.data) && Objects.equals(log, that.log);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, log);
  }
}
