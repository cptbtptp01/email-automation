package problem1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The ProcessInput class provides methods to read data from a CSV file, process it, and write the
 * processed data to output files based on provided templates.
 */
public class ProcessInput extends inputArgs{

  /**
   * Method reads data from CSV file, process it base on provided templates, then write into output
   * files. (e.g. "first_name" : "AMY")
   *
   * @param args The command-line arguments
   * @return a map where the key is the member's property and the value is the member's value
   */
  public static Map<String, String> Read(String[] args) {
    Map<String, String> mapOfFiles = getFileNames(args);
    String path = mapOfFiles.get(CSV_FILE);
    List<String[]> lines = CSVProcessor.readCSV(path);
    return CSVProcessor.processCSV(lines, mapOfFiles);
  }

  /**
   * Helper method gets file names, output directory from the command-line arguments, stores them in
   * a map for easy access.
   *
   * @param args Command-line argument
   * @return A map containing file names and output path (e.g., {"--csv-file": "customer.csv",
   * "--email-template": "email-template.txt"})
   */
  private static Map<String, String> getFileNames(String[] args) {
    // e.g. Key: --email-template, Value: email-template.txt
    Map<String, String> fileNameMap = new HashMap<>();
    for (int i = 0; i < args.length - 1; i++) {
      if (CSV_FILE.equals(args[i])) {
        fileNameMap.put(CSV_FILE, args[i + 1]);
      } else if (EMAIL_TEMPLATE.equals(args[i])) {
        fileNameMap.put(EMAIL_TEMPLATE, args[i + 1]);
      } else if (LETTER_TEMPLATE.equals(args[i])) {
        fileNameMap.put(LETTER_TEMPLATE, args[i + 1]);
      } else if (OUTPUT_DIR.equals(args[i])) {
        fileNameMap.put(OUTPUT_DIR, args[i + 1]);
      }
    }
    return fileNameMap;
  }
}
