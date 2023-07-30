package problem1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The TemplateReader class provides methods to read data from a template file, find placeholders,
 * and replace placeholders, then store in a string.
 */
public class TemplateReader extends inputArgs {

  private static final String PLACEHOLDER_REGEX = "\\[\\[([^\\[\\]]+)\\]\\]";

  /**
   * Helper Method processes a template file by replacing placeholders with data from a given map.
   *
   * @param templateFileName The name of the template file to be processed.
   * @param data             A map containing data values to replace placeholders in the template.
   * @return The content of the template file after placeholders have been replaced.
   */
  public static String processTemplate(String templateFileName, Map<String, String> data) {
    String content = TemplateReader.readTemplate(templateFileName);
    return TemplateReader.replacePlaceholders(content, data);
  }

  /**
   * Helper method counts the number of templates specified in the command-line arguments
   *
   * @param mapOfFiles A map containing the file names and output directory path for reading
   *                   templates and writing output files.
   * @return The type of template(s) specified in the command-line arguments.
   */
  public static TemplateType countTemplate(Map<String, String> mapOfFiles) {
    boolean hasEmailTemplate = mapOfFiles.containsKey(EMAIL_TEMPLATE);
    boolean hasLetterTemplate = mapOfFiles.containsKey(LETTER_TEMPLATE);

    if (hasEmailTemplate && hasLetterTemplate) {
      return TemplateType.EMAIL_AND_LETTER;
    } else if (hasEmailTemplate) {
      return TemplateType.EMAIL;
    } else if (hasLetterTemplate) {
      return TemplateType.LETTER;
    }
    return null;
  }

  /**
   * Helper Method reads a template file and returns its content as a string.
   *
   * @param templateFileName The name of the template file to be read.
   * @return The content of the template file as a string.
   */
  private static String readTemplate(String templateFileName) {
    try (BufferedReader inputFile = new BufferedReader(
        new FileReader(templateFileName)); StringWriter writer = new StringWriter()) {
      String line;

      while ((line = inputFile.readLine()) != null) {
        writer.write(line);
        writer.write(System.lineSeparator());
      }
      return writer.toString();
    } catch (FileNotFoundException fnfe) {
      System.out.println("*** OOPS! A file was not found : " + fnfe.getMessage());
      fnfe.printStackTrace();
      throw new RuntimeException("File Not Found", fnfe);
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
      ioe.printStackTrace();
      throw new RuntimeException("IO Exception", ioe);
    }
  }

  /**
   * Helper Method replace placeholder fields in the email or letter template content with values
   * from the given map and returns modified content.
   *
   * @param templateContent The content of the template file containing placeholders to be
   *                        replaced.
   * @param data            A map containing data values to replace placeholders in the template.
   * @return The modified content of the template after placeholders have been replaced with data
   * values.
   */
  private static String replacePlaceholders(String templateContent, Map<String, String> data) {
    Pattern pattern = Pattern.compile(PLACEHOLDER_REGEX);
    Matcher matcher = pattern.matcher(templateContent);

    StringBuilder replacedContent = new StringBuilder();
    while (matcher.find()) {
      String placeholder = matcher.group(1);
      String replacement = data.getOrDefault(placeholder, "error");
      matcher.appendReplacement(replacedContent, replacement);
    }
    matcher.appendTail(replacedContent);
    return replacedContent.toString();
  }
}
