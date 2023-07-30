package problem1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Main is the entry of the program, the user can run the program by providing command-line
 * arguments.
 */
public class Main {

  private static final Integer EMPTY_ARG = 0;
  private static final String USAGE_FILE = "./src/main/resources/usage.txt";

  /**
   * The main method of the program, if the command-line arguments is empty, then the program will
   * not print anything, otherwise it validates the command-line arguments. If is valid, then it
   * will print the processed data, otherwise it will print the error log.
   *
   * @param args
   */
  public static void main(String[] args) {
    MainProcessor processor = new MainProcessor(args);
    if (args.length != EMPTY_ARG) {
      if (processor.getLog().isEmpty()) {
        System.out.println("Data is successfully processed.");
      } else {
        System.out.println(processor.getLog().toString());
        Usage.printUsage(USAGE_FILE);
      }
    }
  }
}
