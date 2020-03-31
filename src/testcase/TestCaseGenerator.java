package testcase;

import java.io.File;

public class TestCaseGenerator {

  private String testCaseFilePath = null;

  public TestCaseGenerator(String testCaseFilePath) {
    this.createFile(testCaseFilePath);
  }

  public static void main(String[] args) {

  }

  /**
   * Generate test cases under designated directory of a designated number.
   * 
   * @param num number of test cases to be generated
   */
  public void generate(int num) {

  }

  /**
   * Create a file under designated directory and use it as path for test case generation.
   * 
   * @param filePath file path of the created file
   */
  private void createFile(String filePath) {
    File file = new File(filePath);
    try {
      // if file doesn't exist
      if (!file.exists()) {
        file.createNewFile();
        System.out.println("File created - " + filePath);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    this.testCaseFilePath = filePath;
  }
}
