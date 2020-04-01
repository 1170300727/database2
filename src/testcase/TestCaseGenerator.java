package testcase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import database.Relation;

/**
 * Unfinished. All logical designs are not definitive.
 * 
 * @author AtoshDustosh
 */
public class TestCaseGenerator {

  private String testCaseFilePath = null;

  private List<String> relationNameList = new ArrayList<String>();
  private List<Relation> relationList = new ArrayList<Relation>();

  public TestCaseGenerator(String testCaseFilePath) {
    this.createFile(testCaseFilePath);
    this.initialize();
  }

  public static void main(String[] args) {
    List<String> list1 = new ArrayList<String>();
    list1.add("aaa");
    list1.add("bbb");
    list1.add("ccc");
    System.out.println("before adding null: " + list1.size());
    list1.add(null);
    System.out.println("after adding null: " + list1.size());
    System.out.println("list1.toString(): " + list1.toString());

  }

  /**
   * Generate test cases under designated directory of a designated number.
   * 
   * @param num number of test cases to be generated
   */
  public void generate(int num) {

  }

  private void initialize() {

  }

  /**
   * Create a file under designated directory and use it as path for test case
   * generation.
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
