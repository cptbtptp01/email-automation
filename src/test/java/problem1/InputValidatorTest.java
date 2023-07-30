package problem1;

import static problem1.InputValidator.isValidInput;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InputValidatorTest {
  String[] args1;
  String[] args2;
  String[] args3;


  @BeforeEach
  void setUp() {
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
  }
  
  @Test
  void isValidInputEmail() {
    assertEquals(ErrorType.VALID, isValidInput(args1));
  }

  @Test
  void isValidInputLetter() {
    assertEquals(ErrorType.VALID, isValidInput(args2));
  }

  @Test
  void isValidInputEmailLetter() {
    assertEquals(ErrorType.VALID, isValidInput(args3));
  }

  @Test
  void notValidInputEmailTemplate() {
    String[] otherArgs = new String[]{
        "--csv-file", "./src/test/resources/test.csv",
        "./src/test/resources/email-template.txt",
        "--output-dir", "./src/test/resources/output",
        "--email"
    };
    assertEquals(ErrorType.EMAIL_TEMPLATE_MISSING_ERROR, isValidInput(otherArgs));
  }

  @Test
  void notValidInputEmailTemplate1() {
    String[] otherArgs = new String[]{
        "--csv-file", "./src/test/resources/test.csv",
        "--email-templatee","./src/test/resources/email-template.txt",
        "--output-dir", "./src/test/resources/output",
        "--email"
    };
    assertEquals(ErrorType.EMAIL_TEMPLATE_MISSING_ERROR, isValidInput(otherArgs));
  }
  @Test
  void notValidInputEmailTemplate2() {
    String[] otherArgs = new String[]{
        "--csv-file", "./src/test/resources/test.csv",
        "--email-template","./src/test/resources/email-template.txt",
        "--output-dir", "./src/test/resources/output",
        "--emaill"
    };
    assertEquals(ErrorType.EMAIL_ERROR, isValidInput(otherArgs));
  }

  @Test
  void notValidInputEmail() {
    String[] otherArgs = new String[]{
        "--csv-file", "./src/test/resources/test.csv",
        "--email-template", "./src/test/resources/email-template.txt",
        "--output-dir", "./src/test/resources/output"
    };
    assertEquals(ErrorType.EMAIL_ERROR, isValidInput(otherArgs));
  }

  @Test
  void notValidInputLetterTemplate() {
    String[] otherArgs = new String[]{
        "--csv-file", "./src/test/resources/test.csv",
        "./src/test/resources/letter-template.txt",
        "--output-dir", "./src/test/resources/output",
        "--letter"
    };
    assertEquals(ErrorType.LETTER_TEMPLATE_MISSING_ERROR, isValidInput(otherArgs));
  }

  @Test
  void notValidInputLetterTemplate1() {
    String[] otherArgs = new String[]{
        "--csv-file", "./src/test/resources/test.csv",
        "--letter-templatee","./src/test/resources/letter-template.txt",
        "--output-dir", "./src/test/resources/output",
        "--letter"
    };
    assertEquals(ErrorType.LETTER_TEMPLATE_MISSING_ERROR, isValidInput(otherArgs));
  }

  @Test
  void notValidInputLetterTemplate2() {
    String[] otherArgs = new String[]{
        "--csv-file", "./src/test/resources/test.csv",
        "--letter-template","./src/test/resources/letter-template.txt",
        "--output-dir", "./src/test/resources/output",
        "--letterr"
    };
    assertEquals(ErrorType.LETTER_ERROR, isValidInput(otherArgs));
  }
  @Test
  void notValidInputLetter() {
    String[] otherArgs = new String[]{
        "--csv-file", "./src/test/resources/test.csv",
        "--letter-template", "./src/test/resources/letter-template.txt",
        "--output-dir", "./src/test/resources/output",
    };
    assertEquals(ErrorType.LETTER_ERROR, isValidInput(otherArgs));
  }


  @Test
  void notValidInputLetterPath2() {
    String[] otherArgs = new String[]{
        "--csv-file", "./src/test/resources/test.csv",
        "--letter-template",
        "--output-dir", "./src/test/resources/output",
        "--letter"
    };
    assertEquals(ErrorType.INSUFFICIENT_ARGS, isValidInput(otherArgs));
  }

  @Test
  void notValidInputOutputDir() {
    String[] otherArgs = new String[]{
        "--csv-file", "./src/test/resources/test.csv",
        "--email-template", "./src/test/resources/email-template.txt",
        "./src/test/resources/output",
        "--email"
    };
    assertEquals(ErrorType.OUTPUT_DIR_ERROR, isValidInput(otherArgs));
  }
  @Test
  void notValidInputOutputDir2() {
    String[] otherArgs = new String[]{
        "--csv-file", "./src/test/resources/test.csv",
        "--email-template", "./src/test/resources/email-template.txt",
        "--output-dirr","./src/test/resources/output",
        "--email"
    };
    assertEquals(ErrorType.OUTPUT_DIR_ERROR, isValidInput(otherArgs));
  }


  @Test
  void notValidInputCsv() {
    String[] otherArgs = new String[]{
        "./src/test/resources/test.csv",
        "--email-template", "./src/test/resources/email-template.txt",
        "--output-dir", "./src/test/resources/output",
        "--email"
    };
    assertEquals(ErrorType.CSV_FILE_ERROR, isValidInput(otherArgs));
  }

  @Test
  void notValidInputCsv2() {
    String[] otherArgs = new String[]{
        "--csv-filee","./src/test/resources/test.csv",
        "--email-template", "./src/test/resources/email-template.txt",
        "--output-dir", "./src/test/resources/output",
        "--email"
    };
    assertEquals(ErrorType.CSV_FILE_ERROR, isValidInput(otherArgs));
  }
}