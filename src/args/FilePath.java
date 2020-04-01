package args;

public enum FilePath {
  SQLtestcase("src/testcase/testcase.sql");

  ;

  private String filePath = null;

  private FilePath(String filePath) {
    this.filePath = filePath;
  }

  public String getPath() {
    return this.filePath;
  }

}
