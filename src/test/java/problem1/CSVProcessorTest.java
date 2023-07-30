package problem1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CSVProcessorTest {

  private String path;

  @BeforeEach
  void setUp() {
    path = "./src/test/resources/test.csv";
  }

  @Test
  void testProcessCSV() {
    Map<String, String> expected = new HashMap<>();
    Map<String, String> fileMap = new HashMap<>();
    expected.put("Output","Success");
    fileMap.put("--email-template", "./src/test/resources/email-template.txt");
    fileMap.put("--output-dir", "./src/test/resources/output");

    List<String[]> lines = CSVProcessor.readCSV(path);
    Map<String, String> result = CSVProcessor.processCSV(lines, fileMap);
    assertEquals(expected, result);
  }

  @Test
  void testReadCSV() {
    List<String[]> expected = new ArrayList<>();
    expected.add(
        new String[]{"first_name", "last_name", "company_name", "address", "city", "county",
            "state", "zip", "phone1", "phone2", "email", "web"});
    expected.add(
        new String[]{"James", "Butt", "Benton, John B Jr", "6649 N Blue Gum St", "New Orleans",
            "Orleans", "LA", "70116", "504-621-8927", "504-845-1427", "jbutt@gmail.com",
            "http://www.bentonjohnbjr.com"});
    expected.add(
        new String[]{"Josephine", "Darakjy", "Chanay, Jeffrey A Esq", "4 B Blue Ridge Blvd",
            "Brighton", "Livingston", "MI", "48116", "810-292-9388", "810-374-9840",
            "josephine_darakjy@darakjy.org", "http://www.chanayjeffreyaesq.com"});
    expected.add(
        new String[]{"Art", "Venere", "Chemel, James L Cpa", "8 W Cerritos Ave #54", "Bridgeport",
            "Gloucester", "NJ", "08014", "856-636-8749", "856-264-4130", "art@venere.org",
            "http://www.chemeljameslcpa.com"});
    List<String[]> test = CSVProcessor.readCSV(path);

    for (int i = 0; i < test.size(); i++) {
      assertEquals(Arrays.toString(expected.get(i)), Arrays.toString(test.get(i)));
    }
  }

  @Test
  void testFileNotFoundException() {
    String nonExistentPath = "non_existent_file.csv";
    assertThrows(RuntimeException.class, () -> CSVProcessor.readCSV(nonExistentPath));
  }
}