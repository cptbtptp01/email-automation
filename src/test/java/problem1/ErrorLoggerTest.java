package problem1;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ErrorLoggerTest {
  private ErrorLogger testErrorLogger;
  private ErrorLogger otherErrorLogger;
  @BeforeEach
  void setUp() {
    testErrorLogger = new ErrorLogger();
    otherErrorLogger = new ErrorLogger();
  }

  @Test
  void addLog() {
    testErrorLogger.addLog("Testing log");
    String expected = "Testing log" + System.lineSeparator();
    assertEquals(testErrorLogger.toString(), expected);
  }

  @Test
  void isEmpty() {
    assertTrue(testErrorLogger.isEmpty());
    otherErrorLogger.addLog("test");
    assertFalse(otherErrorLogger.isEmpty());
  }

  @Test
  void testEquals_compareToSelf() {
    assertTrue(testErrorLogger.equals(testErrorLogger));
  }
  @Test
  void testEquals_compareToNull() {
    assertFalse(testErrorLogger.equals(null));
  }
  @Test
  void testEquals_sameFields(){
    testErrorLogger.addLog("test");
    otherErrorLogger.addLog("test");
    assertEquals(testErrorLogger, otherErrorLogger);
  }
  @Test
  void testEquals_differentDataType(){
    String otherDataType = "test";
    assertFalse(testErrorLogger.equals(otherDataType));
  }
  @Test
  void testHashCode() {
    testErrorLogger.addLog("testing log");
    List<String> expectedLog = new ArrayList<>();
    expectedLog.add("testing log");
    int expectedHashCode = Objects.hash(expectedLog);
    assertEquals(expectedHashCode, testErrorLogger.hashCode());
  }

  @Test
  void testToString() {
    testErrorLogger.addLog("testing log");
    String expectedToString = "testing log" + System.lineSeparator();
    assertEquals(testErrorLogger.toString(), expectedToString);
    assertEquals(otherErrorLogger.toString(), "Empty log");
  }
}