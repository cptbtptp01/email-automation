package problem1;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProcessInputTest {

  String[] args1;
  String[] args2;
  String[] args3;
  Map<String, String> result;

  File output;
  File directory;

  String outputDir;

  @BeforeEach
  void setUp() {
    // --email
    // --email-template ./src/test/resources/email-template.txt
    // --output-dir ./src/test/resources/output
    // --csv-file ./src/test/resources/test.csv
    // --letter
    // --letter-template ./src/test/resources/letter-template.txt
    args1 = new String[]{
        "--csv-file", "./src/test/resources/test.csv",
        "--email-template", "./src/test/resources/email-template.txt",
        "--output-dir", "./src/test/resources/output",
        "--email"
    };
    args2 = new String[]{
        "--csv-file", "./src/test/resources/test.csv",
        "--letter-template", "./src/test/resources/letter-template.txt",
        "--output-dir", "./src/test/resources/output",
        "--letter"
    };
    args3 = new String[]{
        "--csv-file", "./src/test/resources/test.csv",
        "--letter-template", "./src/test/resources/letter-template.txt",
        "--output-dir", "./src/test/resources/output",
        "--letter",
        "--email",
        "--email-template", "./src/test/resources/email-template.txt"
    };
    outputDir = "./src/test/resources/output";
    directory = new File(outputDir);
  }

  @AfterEach
  public void cleanup() {
    // clean output folder everytime after testing
    if (directory.exists()) {
      File[] files = directory.listFiles(new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
          return !name.equals(".gitkeep");
        }
      });

      if (files != null) {
        for (File file : files) {
          file.delete();
        }
      }
    }
  }

  @Test
  void readEmailOnly(){
    ProcessInput.Read(args1);
    int numFilesGenerated = 3;
    for (int i = 1; i <= numFilesGenerated; i++) {
      output = new File(outputDir + "/email_" + i + ".txt");
      assertTrue(output.exists());
    }
  }

  @Test
  void readLetterOnly(){
    ProcessInput.Read(args2);
    int numFilesGenerated = 3;
    for (int i = 1; i <= numFilesGenerated; i++) {
      output = new File(outputDir + "/letter_" + i + ".txt");
      assertTrue(output.exists());
    }
  }

  @Test
  void readEmailAndLetter(){
    ProcessInput.Read(args3);
    int processingTime = 3;
    for (int i = 1; i <= processingTime; i++) {
      File output1 = new File(outputDir + "/email_" + i + ".txt");
      File output2 = new File(outputDir + "/letter_" + i + ".txt");
      assertTrue(output1.exists() && output2.exists());
    }
  }
}