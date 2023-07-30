package problem1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * The TemplateWriter class provides methods to process a template file with replaced placeholders,
 * then output files.
 */
public class TemplateWriter extends inputArgs {

  /**
   * Method writes the processed data to output files with given templates.
   *
   * @param data       A map containing the processed data with keys as property names and values as
   *                   corresponding data values.
   * @param mapOfFiles A map containing the file names and output directory path for reading
   *                   templates and writing output files.
   * @param identifier A unique identifier used to distinguish output files
   */
  public static void Write(Map<String, String> data, Map<String, String> mapOfFiles,
      String identifier) {

    String outputDir = mapOfFiles.get(OUTPUT_DIR);
    TemplateType template = TemplateReader.countTemplate(mapOfFiles);

    switch (template) {
      case EMAIL -> {
        String replacedContent = TemplateReader.processTemplate(mapOfFiles.get(EMAIL_TEMPLATE),
            data);
        writeSingle(outputDir + "/email_" + identifier + ".txt", replacedContent);
      }
      case LETTER -> {
        String replacedContent = TemplateReader.processTemplate(mapOfFiles.get(LETTER_TEMPLATE),
            data);
        writeSingle(outputDir + "/letter_" + identifier + ".txt", replacedContent);
      }
      case EMAIL_AND_LETTER -> {
        String replacedContentEmail = TemplateReader.processTemplate(
            mapOfFiles.get(EMAIL_TEMPLATE), data);
        String replacedContentLetter = TemplateReader.processTemplate(
            mapOfFiles.get(LETTER_TEMPLATE), data);
        writeSingle(outputDir + "/email_" + identifier + ".txt", replacedContentEmail);
        writeSingle(outputDir + "/letter_" + identifier + ".txt", replacedContentLetter);
      }
    }
  }

  /**
   * Helper method write data into an output file with given content
   *
   * @param fileName output file name
   * @param content  given content for the output file
   */
  private static void writeSingle(String fileName, String content) {
    try (BufferedWriter outputFile = new BufferedWriter(new FileWriter(fileName))) {
      outputFile.write(content);
    } catch (IOException ioe) {
      System.out.println("Something went wrong with IO! : " + ioe.getMessage());
      throw new RuntimeException("IO Exception", ioe);
    }
  }
}
