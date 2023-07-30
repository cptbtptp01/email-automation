package problem1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Usage is a class that prints the usage of the program.
 */
public class Usage {

  /**
   * Print the usage of the program. The usage is stored in a file called usage.txt.
   */
  public static void printUsage(String fileName) {
    try (BufferedReader inputFile = new BufferedReader(
        new FileReader(fileName))) {
      String line;
      while ((line = inputFile.readLine()) != null) {
        System.out.println(line);
      }
    } catch (FileNotFoundException fnfe) {
      System.out.println("*** OOPS! A file was not found : " + fnfe.getMessage());
      fnfe.printStackTrace();
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
      ioe.printStackTrace();
    }
  }
}
