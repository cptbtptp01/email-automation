package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MainProcessorTest extends inputArgs{
  private MainProcessor testMainProcessor;
  private MainProcessor otherProcessor;
  private String validEmailPath = "./src/test/resources/email-template.txt";
  private String validLetterPath = "./src/test/resources/letter-template.txt";
  private String validOutputDir = "./src/test/resources/output";
  private String[] invalidArgs;
  private String CsvPath = "./src/test/resources/test-single.csv";

  ErrorLogger testLog;

  @BeforeEach
  void setUp() {
    testLog = new ErrorLogger();
  }

  @Test
  void getCodes() {
    String[] validArgs = {EMAIL_OPTION, EMAIL_TEMPLATE, validEmailPath, LETTER_OPTION, LETTER_TEMPLATE, validLetterPath, OUTPUT_DIR, validOutputDir, CSV_FILE, CsvPath};
    testMainProcessor = new MainProcessor(validArgs);
    String expected = "{Output=Success}";
    assertEquals(expected, testMainProcessor.getData().toString());
    String[] invalidArgs = {EMAIL_OPTION, EMAIL_TEMPLATE, validEmailPath, OUTPUT_DIR, validOutputDir};
    otherProcessor = new MainProcessor(invalidArgs);
    assertEquals("{}", otherProcessor.getData().toString());
  }

  @Test
  void getLog() {
    String[] args = {"--email-template", validEmailPath, "--output-dir", validOutputDir};
    testMainProcessor = new MainProcessor(args);
    testLog.addLog("Error: no --email provided but --email-template was given.");
    assertEquals(testLog, testMainProcessor.getLog());
  }
  @Test
  void testInsufficientArgs() {
    String[] args = {"--output-dir", validOutputDir, "--csv-file", CsvPath};
    testMainProcessor = new MainProcessor(args);
    testLog.addLog("Error: insufficient args provided.");
    assertEquals(testLog, testMainProcessor.getLog());
  }
  @Test
  void testEmailTempMissing() {
    String [] args = {"--email", "--output-dir", validOutputDir, "--csv-file", CsvPath};
    testMainProcessor = new MainProcessor(args);
    testLog.addLog("Error: --email provided but no --email-template was given.");
    assertEquals(testLog, testMainProcessor.getLog());
  }
  @Test
  void testLetterTempMissing() {
    String [] args = {"--letter", "--output-dir", validOutputDir, "--csv-file", CsvPath};
    testMainProcessor = new MainProcessor(args);
    testLog.addLog("Error: --letter provided but no --letter-template was given.");
    assertEquals(testLog, testMainProcessor.getLog());
  }
  @Test
  void testLetterMissing(){
    String [] args = {"--letter-template", validLetterPath, "--output-dir", validOutputDir, "--csv-file", CsvPath};
    testMainProcessor = new MainProcessor(args);
    testLog.addLog("Error: no --letter provided but --letter-template was given.");
    assertEquals(testLog, testMainProcessor.getLog());
  }
  @Test
  void testOutputDirMissing(){
    String [] args = {"--email","--email-template", validEmailPath, "--csv-file", CsvPath};
    testMainProcessor = new MainProcessor(args);
    testLog.addLog("Error: no --output-dir provided.");
    assertEquals(testLog, testMainProcessor.getLog());
  }

  @Test
  void testEquals_compareToSelf() {
    String[] args = {"--email", "--email-template", validEmailPath, "--output-dir", validOutputDir, "--csv-file", CsvPath};
    testMainProcessor = new MainProcessor(args);
    assertEquals(testMainProcessor, testMainProcessor);
  }
  @Test
  void testEquals_compareToNull() {
    String[] args = {"--email", "--email-template", validEmailPath, "--output-dir", validOutputDir, "--csv-file", CsvPath};
    testMainProcessor = new MainProcessor(args);
    assertFalse(testMainProcessor.equals(null));
  }
  @Test
  void testEquals_compareToOtherClass() {
    String[] args = {"--email", "--email-template", validEmailPath, "--output-dir", validOutputDir, "--csv-file", CsvPath};
    testMainProcessor = new MainProcessor(args);
    assertFalse(testMainProcessor.equals(validEmailPath));
  }
  @Test
  void testEquals_sameFields() {
    String[] args = {"--email", "--email-template", validEmailPath, "--output-dir", validOutputDir, "--csv-file", CsvPath};
    testMainProcessor = new MainProcessor(args);
    String[] otherArgs = {"--email", "--email-template", validEmailPath, "--output-dir", validOutputDir, "--csv-file", CsvPath};
    otherProcessor = new MainProcessor(otherArgs);
    assertTrue(testMainProcessor.equals(otherProcessor));
  }
  @Test
  void testEquals_differentArgs(){
    String[] args = {"--output-dir", validOutputDir, "--csv-file", CsvPath};
    testMainProcessor = new MainProcessor(args);
    String[] otherArgs = {"--email", "--email-template", validEmailPath, "--output-dir", validOutputDir, "--csv-file", CsvPath, "--letter", "--letter-template", validLetterPath};
    otherProcessor = new MainProcessor(otherArgs);
    assertFalse(testMainProcessor.equals(otherProcessor));
  }
  @Test
  void testHashCode() {
    String[] args = {"--output-dir", validOutputDir, "--csv-file", CsvPath};
    testMainProcessor = new MainProcessor(args);
    testLog.addLog("Error: insufficient args provided.");
    int expectedHash = Objects.hash("",testLog);
    assertEquals(expectedHash, testMainProcessor.hashCode());
  }
}