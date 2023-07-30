package problem1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The CSVProcessor class provides methods to read data from a CSV file, process it, extract
 * information and store in a map.
 */
public class CSVProcessor {

  private static final String CSV_REGEX = ",(?=(?:[^\"]*\"[^\"]*\")*(?![^\"]*\"))";

  /**
   * Method process CSV data and stores the processed data in a map, where keys are properties, then
   * write into output files.
   *
   * @param lines      A list of string arrays where each array represents a row of data from the
   *                   CSV file.
   * @param mapOfFiles A map containing the file names and output directory path for reading
   *                   templates and writing output files.
   * @return A map containing the processed data with keys as property names and values as
   * corresponding data values. e.g. {"first_name"="James","last_name"="Butt",...}
   */
  public static Map<String, String> processCSV(List<String[]> lines,
      Map<String, String> mapOfFiles) {
    Map<String, String> data = new HashMap<>();
    Map<String, String> after = new HashMap<>();
    boolean isHeader = true;
    String[] header = null;
    for (int i = 0; i < lines.size(); i++) {
      if (isHeader) {
        header = lines.get(i);
        isHeader = false;
        continue;
      }
      data = processCSVLine(header, lines.get(i));
      System.out.println("Read:" + data + "\n");
      TemplateWriter.Write(data, mapOfFiles, Integer.toString(i));
    }
    after.put("Output", "Success");
    return after;
  }

  /**
   * Helper method processes a single line of CSV data and stores the processed data in a map, where
   * the keys are properties.
   *
   * @param header The header line containing property names.
   * @param line   The data line containing values corresponding to the header.
   * @return A map containing the processed data with keys as property names and values as
   * corresponding data values.
   */
  private static Map<String, String> processCSVLine(String[] header, String[] line) {
    Map<String, String> data = new HashMap<>();
    // TODO do we need check if null?
    for (int i = 0; i < line.length; i++) {
      // {"first_name"="James","last_name"="Butt",...}
      data.put(header[i].trim(), line[i].trim());
    }
    return data;
  }

  /**
   * Method reads a CSV file from the given path and returns the data as a list of string arrays.
   *
   * @param path The path to the CSV file to be read
   * @return A list of string arrays where each array represents a row of data from the CSV file.
   * e.g. [["James","Butt","Benton, John B Jr","6649 N Blue Gum St",....]]
   */
  public static List<String[]> readCSV(String path) {
    List<String[]> lines = new ArrayList<>();

    try (BufferedReader inputFile = new BufferedReader(new FileReader(path))) {
      String line;
      while ((line = inputFile.readLine()) != null) {
        // parse csv line
        String[] fields = readCSVLine(line);
        lines.add(fields);
      }
    } catch (FileNotFoundException fnfe) {
      System.out.println("*** OOPS! A file was not found : " + fnfe.getMessage());
      fnfe.printStackTrace();
      throw new RuntimeException("File Not Found", fnfe);
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
      ioe.printStackTrace();
      throw new RuntimeException("IO Exception", ioe);
    }
    return lines;
  }

  /**
   * Helper method reads CSV per line, handling situation such as comma inside quoted fields.
   *
   * @param line The data line to be processed.
   * @return An array of strings representing the individual fields from the CSV line. e.g.first
   * line/header: ["first_name","last_name","company_name","address",...] other
   * line:["James","Butt","Benton, John B Jr","6649 N Blue Gum St",....]
   */
  private static String[] readCSVLine(String line) {
    Pattern pattern = Pattern.compile(CSV_REGEX);
    Matcher matcher = pattern.matcher(line);

    String[] fields = pattern.split(line);
    for (int i = 0; i < fields.length; i++) {
      fields[i] = fields[i].replaceAll("^\"|\"$", ""); // Remove enclosing double quotes
    }
    return fields;
  }
}
