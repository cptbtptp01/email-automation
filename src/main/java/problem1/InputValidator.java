package problem1;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * InputValidator class validates if command line arguments input is valid
 */
public class InputValidator extends inputArgs{

  private static final Integer MIN_NUM_OF_ARG = 7;
  private static final Integer MAX_NUM_OF_ARG = 10;
  private static final Integer MAX_NUM_OF_EACH_ARG = 1;

  /**
   * Method that checks if command line arguments is valid
   * @param args command arguments
   * @return an ErrorType that indicates the error type if not valid,
   * return ErrorType.VALID if is valid input
   */
  public static ErrorType isValidInput(String[] args) {

    if (hasEmailOption(args) && !hasEmailTemplate(args)) {
      return ErrorType.EMAIL_TEMPLATE_MISSING_ERROR;
    }
    if (!hasEmailOption(args) && hasEmailTemplate(args)) {
      return ErrorType.EMAIL_ERROR;
    }
    if (hasLetterOption(args) && !hasLetterTemplate(args)) {
      return ErrorType.LETTER_TEMPLATE_MISSING_ERROR;
    }
    if (!hasLetterOption(args) && hasLetterTemplate(args)) {
      return ErrorType.LETTER_ERROR;
    }
    if (!hasOutputDir(args)) {
      return ErrorType.OUTPUT_DIR_ERROR;
    }
    if (!hasCsvFile(args)) {
      return ErrorType.CSV_FILE_ERROR;
    }
    if (args.length != MIN_NUM_OF_ARG && args.length != MAX_NUM_OF_ARG) {
      return ErrorType.INSUFFICIENT_ARGS;
    }
    return ErrorType.VALID;
  }

  /**
   * Method that checks if email option is provided
   * @param args command line arguments
   * @return true if email option is provided
   */
  private static boolean hasEmailOption(String[] args) {
    int emailArgCnt = Collections.frequency(List.of(args), EMAIL_OPTION);
    if (emailArgCnt == MAX_NUM_OF_EACH_ARG) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }

  /**
   * Method that checks if email template is provided
   * @param args command line arguments
   * @return true if email template is provided
   */
  private static boolean hasEmailTemplate(String[] args) {
    int emailTemplateArgCnt = Collections.frequency(List.of(args), EMAIL_TEMPLATE);
    if (emailTemplateArgCnt == MAX_NUM_OF_EACH_ARG) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }


  /**
   * A method that checks if the letter option is provided
   * @param args command line arguments
   * @return true if letter option is provided
   */
  private static boolean hasLetterOption(String[] args) {
    int letterArgCnt = Collections.frequency(List.of(args), LETTER_OPTION);
    if (letterArgCnt == MAX_NUM_OF_EACH_ARG) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }

  /**
   * Method that checks if letter template is provided
   * @param args command line arguments
   * @return true if letter template is provided
   */
  private static boolean hasLetterTemplate(String[] args) {
    int letterTemplateArgCnt = Collections.frequency(List.of(args), LETTER_TEMPLATE);
    if (letterTemplateArgCnt == MAX_NUM_OF_EACH_ARG) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }



  /**
   * A method that checks if the output dir option is provided
   * @param args command line arguments
   * @return true if output dir option is provided
   */
  private static boolean hasOutputDir(String[] args) {
    int outputDirArgCnt = Collections.frequency(List.of(args), OUTPUT_DIR);
    if (outputDirArgCnt == MAX_NUM_OF_EACH_ARG) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }



  /**
   * A method that checks if the csv file option is provided
   * @param args command line arguments
   * @return true if csv file option is provided
   */
  private static boolean hasCsvFile(String[] args) {
    int csvFileArgCnt = Collections.frequency(List.of(args), CSV_FILE);
    if(csvFileArgCnt == MAX_NUM_OF_EACH_ARG) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }
}
