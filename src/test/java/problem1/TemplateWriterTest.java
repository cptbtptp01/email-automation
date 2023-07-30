package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TemplateWriterTest {
  Map<String, String> fileMap;
  Map<String, String> data;

  @BeforeEach
  void setUp() {
    fileMap = new HashMap<>();
    fileMap.put("--output-dir", "./src/test/resources/output_wrong");
    fileMap.put("--email-template", "./src/test/resources/email-template.txt");

    data = new HashMap<>();
    String[] header = new String[]{"first_name", "last_name", "company_name", "address", "city",
        "county",
        "state", "zip", "phone1", "phone2", "email", "web"};
    String[] lastLine = new String[]{"Art", "Venere", "Chemel, James L Cpa", "8 W Cerritos Ave #54",
        "Bridgeport",
        "Gloucester", "NJ", "08014", "856-636-8749", "856-264-4130", "art@venere.org",
        "http://www.chemeljameslcpa.com"};
    for (int i = 0; i < header.length; i++) {
      data.put(header[i], lastLine[i]);
    }
  }

  // since write() method is invoked when Read() is being successfully called, testing is covered in the ProcessInputTest,
  // so the test will only test for io exception
  @Test
  void testIOException() {
    assertThrows(RuntimeException.class, () -> TemplateWriter.Write(data,fileMap,"test"));
  }
}