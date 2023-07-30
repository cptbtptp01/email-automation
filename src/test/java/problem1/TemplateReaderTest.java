package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TemplateReaderTest {
  Map<String, String> fileMap;
  Map<String, String> codes;
  @BeforeEach
  void setUp() {
    fileMap = new HashMap<>();
    fileMap.put("--email-template", "./src/test/resources/email-template.txt");
    fileMap.put("--output-dir", "./src/test/resources/output");

    codes = new HashMap<>();
    String[] header = new String[]{"first_name", "last_name", "company_name", "address", "city",
        "county",
        "state", "zip", "phone1", "phone2", "email", "web"};
    String[] lastLine = new String[]{"Art", "Venere", "Chemel, James L Cpa", "8 W Cerritos Ave #54",
        "Bridgeport",
        "Gloucester", "NJ", "08014", "856-636-8749", "856-264-4130", "art@venere.org",
        "http://www.chemeljameslcpa.com"};
    for (int i = 0; i < header.length; i++) {
      codes.put(header[i], lastLine[i]);
    }
  }

  @Test
  void processTemplate() {
    String result = TemplateReader.processTemplate("./src/test/resources/email-template.txt", codes);
    String expected = "From: insuranceCompany@ic.com\n"
        + "To: art@venere.org\n"
        + "Subject: Insurance company – information about recent data breach\n"
        + "Dear Art Venere,\n"
        + "As you may have heard or read, last month we learned that criminals forced their way into our systems, \n"
        + "and stole information about our customers. Late last week, as part of our ongoing investigation, \n"
        + "we learned that the taken information includes names, mailing addresses, phone numbers or email addresses.\n"
        + " \n"
        + "I am writing to make you aware that your name, mailing address, phone number or email address may have been \n"
        + "taken during the intrusion. \n"
        + "\n"
        + "I am truly sorry this incident occurred, and I sincerely regret any inconvenience it may cause you. \n"
        + "\n"
        + "Because we value you as a customer, and because your trust is important to us, our company is offering you one \n"
        + "year of free credit monitoring through Experian’s ProtectMyID product, which includes identity theft insurance \n"
        + "where available. You will receive more information about this offer via regular mail.\n"
        + "\n"
        + "You can find additional information and FAQs about this incident at our website. If you have further questions, \n"
        + "you may call us at 866-852-8680. \n"
        + "\n"
        + "Thank you for your patience and your loyalty. \n"
        + "Sincerely,\n"
        + "Insurance Company CEO"
        + "\n"
        + "\n";
    assertEquals(expected,result);
  }

  @Test
  void countTemplate() {
    TemplateType template = TemplateReader.countTemplate(fileMap);
    assertEquals(TemplateType.EMAIL, template);
  }
  @Test
  void testFileNotFoundException() {
    String nonExistentPath = "non_existent_file.csv";
    assertThrows(RuntimeException.class, () -> TemplateReader.processTemplate(nonExistentPath,codes));
  }
}