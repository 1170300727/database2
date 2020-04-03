package testcase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import args.Relations;
import database.Relation;
import database.RelyKeyLocation;
import database.Tuple;

/**
 * Unfinished. All logical designs are not definitive.
 * 
 * @author AtoshDustosh
 */
public class TestCaseGenerator {
  public static final String generatedFilePath = "src/testcase/testcases.sql";

  private String testCaseFilePath = null;

  private Map<String, Relation> relationMap = new HashMap<>();

  public TestCaseGenerator(String testCaseFilePath) {
    this.initialize();
  }

  public static void main(String[] args) {
    TestCaseGenerator generator = new TestCaseGenerator(
        TestCaseGenerator.generatedFilePath);
    generator.generate(5000);
    System.out.println(generator.toString());
    generator.writeFile(TestCaseGenerator.generatedFilePath);
    System.out.println("Writing finished!!!");

  }

  public void writeFile(String filePath) {
    try {
      PrintWriter printWriter = new PrintWriter(new File(filePath));
      printWriter
          .write(this.relationToSQLForm(this.relationMap.get("VehicleBrand")));
      printWriter
          .write(this.relationToSQLForm(this.relationMap.get("Shop")));
      printWriter
          .write(this.relationToSQLForm(this.relationMap.get("Manufacturer")));
      printWriter
          .write(this.relationToSQLForm(this.relationMap.get("WebUser")));
      printWriter
          .write(this.relationToSQLForm(this.relationMap.get("UserFriend")));
      printWriter
          .write(this.relationToSQLForm(this.relationMap.get("VehicleType")));
      printWriter
          .write(
              this.relationToSQLForm(this.relationMap.get("VehicleTypeShop")));
      printWriter
          .write(this.relationToSQLForm(this.relationMap.get("VehicleColour")));
      printWriter
          .write(this.relationToSQLForm(this.relationMap.get("Indent")));
      printWriter
          .write(this
              .relationToSQLForm(this.relationMap.get("VehicleEvaluation")));
      printWriter
          .write(this.relationToSQLForm(
              this.relationMap.get("VehicleBrandManufacture")));
      printWriter
          .write(this.relationToSQLForm(this.relationMap.get("Works")));
      printWriter
          .write(this.relationToSQLForm(this.relationMap.get("WorksReply")));
      printWriter
          .write(this.relationToSQLForm(
              this.relationMap.get("WorksAboutVehicleBrand")));
      printWriter.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * Generate test cases under designated directory of a designated
   * number. Number of relations that rely on others will decrease by 5%
   * by every dependency.
   * 
   * @param num number of test cases to be generated
   */
  public void generate(int num) {
    System.out.println("Start generation ...\n");
    int count = 0;
    Relation relation = null;
    List<String> relyAttributes1 = null;
    List<String> relyAttributes2 = null;
    List<String> relyAttributes3 = null;

    // VehicleBrand
    System.out.println("generate VehicleBrand");
    count = (int) (num * this.randNearOne());
    relation = this.relationMap.get("VehicleBrand");
    for (int i = 0; i < count; i++) {
      List<String> attributeList = new ArrayList<>();
      attributeList.add(this.randNumberString(6));
      attributeList.add("\'" + this.randUppercaseString(6) + "\'");
      relation.addTuple(new Tuple(attributeList));
    }

    // Shop
    System.out.println("generate Shop");
    count = (int) (num * this.randNearOne());
    relation = this.relationMap.get("Shop");
    for (int i = 0; i < count; i++) {
      List<String> attributeList = new ArrayList<>();
      attributeList.add(
          "\'" + this.randUppercaseString(4) + "-S-"
              + this.randUppercaseString(2)
              + "-" + this.randNumberString(4) + "\'");
      relation.addTuple(new Tuple(attributeList));
    }

    // Manufacturer
    System.out.println("generate Manufacturer");
    count = (int) (num * this.randNearOne());
    relation = this.relationMap.get("Manufacturer");
    for (int i = 0; i < count; i++) {
      List<String> attributeList = new ArrayList<>();
      attributeList.add(
          "\'" + this.randUppercaseString(4) + "-M-"
              + this.randUppercaseString(2)
              + "-" + this.randNumberString(4) + "\'");
      relation.addTuple(new Tuple(attributeList));
    }

    // WebUser
    System.out.println("generate WebUser");
    count = (int) (num * this.randNearOne());
    relation = this.relationMap.get("WebUser");
    for (int i = 0; i < count; i++) {
      List<String> attributeList = new ArrayList<>();
      attributeList.add("\'" + this.randNumberString(10) + "\'");
      attributeList.add("\'" + this.randNumberString(11) + "\'");
      relation.addTuple(new Tuple(attributeList));
    }

    Random rand = new Random();

    // UserFriend - rely
    System.out.println("generate UserFriend");
    count = (int) (num * this.randNearOne());
    relation = this.relationMap.get("UserFriend");
    relyAttributes1 = this.relationMap.get("WebUser")
        .getAttributeValueList(0);
    relyAttributes2 = this.relationMap.get("WebUser")
        .getAttributeValueList(0);
    for (int i = 0; i < count; i++) {
      List<String> attributeList = new ArrayList<>();
      String user1 = relyAttributes1.get(rand.nextInt(relyAttributes1.size()));
      String user2 = relyAttributes2.get(rand.nextInt(relyAttributes2.size()));
      if (user1.equals(user2) == false) {
        attributeList.add(user1);
        attributeList.add(user2);
        relation.addTuple(new Tuple(attributeList));
      }
    }

    // VehicleType - rely
    System.out.println("generate VehicleType");
    count = (int) (num * this.randNearOne());
    relation = this.relationMap.get("VehicleType");
    relyAttributes1 = this.relationMap.get("VehicleBrand")
        .getAttributeValueList(0);
    for (int i = 0; i < count; i++) {
      List<String> attributeList = new ArrayList<>();
      attributeList
          .add("\'" + this.randUppercaseString(4) + this.randNumberString(4)
              + "-" + this.randNumberString(2) + "\'");
      attributeList
          .add(relyAttributes1.get(rand.nextInt(relyAttributes1.size())));
      attributeList.add(Integer.toString(rand.nextInt(10) * 15 + 200));
      attributeList.add(Integer.toString(rand.nextInt(3)));
      relation.addTuple(new Tuple(attributeList));
    }

    // VehicleTypeShop - rely
    System.out.println("generate VehicleTypeShop");
    count = (int) (num * this.randNearOne());
    relation = this.relationMap.get("VehicleTypeShop");
    relyAttributes1 = this.relationMap.get("Shop")
        .getAttributeValueList(0);
    relyAttributes2 = this.relationMap.get("VehicleType")
        .getAttributeValueList(0);
    for (int i = 0; i < count; i++) {
      List<String> attributeList = new ArrayList<>();
      attributeList
          .add(relyAttributes1.get(rand.nextInt(relyAttributes1.size())));
      attributeList
          .add(relyAttributes2.get(rand.nextInt(relyAttributes2.size())));
      attributeList.add(Integer.toString(rand.nextInt(1000000) + 30000));
      relation.addTuple(new Tuple(attributeList));
    }

    // VehicleColour - rely
    System.out.println("generate VehicleColour");
    count = (int) (num * this.randNearOne());
    relation = this.relationMap.get("VehicleColour");
    relyAttributes1 = this.relationMap.get("VehicleType")
        .getAttributeValueList(0);
    for (int i = 0; i < count; i++) {
      List<String> attributeList = new ArrayList<>();
      attributeList
          .add(relyAttributes1.get(rand.nextInt(relyAttributes1.size())));
      attributeList
          .add("\'" + this.randUppercaseString(1) + "-"
              + this.randNumberString(2) + "\'");
      relation.addTuple(new Tuple(attributeList));
    }

    // Indent - rely
    System.out.println("generate Indent");
    count = (int) (num * this.randNearOne());
    relation = this.relationMap.get("Indent");
    relyAttributes1 = this.relationMap.get("WebUser")
        .getAttributeValueList(0);
    relyAttributes2 = this.relationMap.get("Shop")
        .getAttributeValueList(0);
    relyAttributes3 = this.relationMap.get("VehicleType")
        .getAttributeValueList(0);
    for (int i = 0; i < count; i++) {
      List<String> attributeList = new ArrayList<>();
      attributeList.add("\'" + this.randNumberString(20) + "\'");
      attributeList
          .add(relyAttributes1.get(rand.nextInt(relyAttributes1.size())));
      attributeList
          .add(relyAttributes2.get(rand.nextInt(relyAttributes2.size())));
      attributeList
          .add(relyAttributes3.get(rand.nextInt(relyAttributes3.size())));
      attributeList.add("\'" + Integer.toString(2013 + rand.nextInt(7)) + "-0" +
          Integer.toString(rand.nextInt(9) + 1) + "-"
          + Integer.toString(rand.nextInt(18) + 10) + "\'");
      relation.addTuple(new Tuple(attributeList));
    }

    // VehicleEvaluation - rely
    System.out.println("generate VehicleEvaluation");
    count = (int) (num * this.randNearOne());
    relation = this.relationMap.get("VehicleEvaluation");
    relyAttributes1 = this.relationMap.get("VehicleType")
        .getAttributeValueList(0);
    relyAttributes2 = this.relationMap.get("WebUser")
        .getAttributeValueList(0);
    relyAttributes3 = this.relationMap.get("Shop")
        .getAttributeValueList(0);
    for (int i = 0; i < count; i++) {
      List<String> attributeList = new ArrayList<>();
      attributeList.add("" + rand.nextInt(Integer.MAX_VALUE));
      attributeList
          .add(relyAttributes1.get(rand.nextInt(relyAttributes1.size())));
      attributeList
          .add(relyAttributes2.get(rand.nextInt(relyAttributes2.size())));
      attributeList
          .add(relyAttributes3.get(rand.nextInt(relyAttributes3.size())));
      attributeList.add("\'" + Integer.toString(2013 + rand.nextInt(7)) + "-0" +
          Integer.toString(rand.nextInt(9) + 1) + "-"
          + Integer.toString(rand.nextInt(18) + 10) + "\'");
      attributeList.add("\'" + this.randLowercaseString(50) + "\'");
      attributeList.add(Double.toString((rand.nextInt(70) + 30) / 10.0));
      relation.addTuple(new Tuple(attributeList));
    }

    // VehicleBrandManufacture - rely
    System.out.println("generate VehicleBrandManufacture");
    count = (int) (num * this.randNearOne());
    relation = this.relationMap.get("VehicleBrandManufacture");
    relyAttributes1 = this.relationMap.get("Manufacturer")
        .getAttributeValueList(0);
    relyAttributes2 = this.relationMap.get("VehicleBrand")
        .getAttributeValueList(0);
    for (int i = 0; i < count; i++) {
      List<String> attributeList = new ArrayList<>();
      attributeList
          .add(relyAttributes1.get(rand.nextInt(relyAttributes1.size())));
      attributeList
          .add(relyAttributes2.get(rand.nextInt(relyAttributes2.size())));
      relation.addTuple(new Tuple(attributeList));
    }

    // VehicleTypeManufacture - rely
    System.out.println("generate VehicleTypeManufacture");
    count = (int) (num * this.randNearOne());
    relation = this.relationMap.get("VehicleTypeManufacture");
    relyAttributes1 = this.relationMap.get("Manufacturer")
        .getAttributeValueList(0);
    relyAttributes2 = this.relationMap.get("VehicleType")
        .getAttributeValueList(0);
    for (int i = 0; i < count; i++) {
      List<String> attributeList = new ArrayList<>();
      attributeList
          .add(relyAttributes1.get(rand.nextInt(relyAttributes1.size())));
      attributeList
          .add(relyAttributes2.get(rand.nextInt(relyAttributes2.size())));
      attributeList.add(Integer.toString(rand.nextInt(100) * 10000));
      relation.addTuple(new Tuple(attributeList));
    }

    // Works - rely
    System.out.println("generate Works");
    count = (int) (num * this.randNearOne());
    relation = this.relationMap.get("Works");
    relyAttributes1 = this.relationMap.get("WebUser")
        .getAttributeValueList(0);
    for (int i = 0; i < count; i++) {
      List<String> attributeList = new ArrayList<>();
      attributeList.add(this.randNumberString(24));
      attributeList
          .add(relyAttributes1.get(rand.nextInt(relyAttributes1.size())));
      attributeList.add("" + rand.nextInt(3));
      attributeList.add("\'" + this.randLowercaseString(50) + "\'");
      relation.addTuple(new Tuple(attributeList));
    }

    // WorksReply - rely
    System.out.println("generate WorksReply");
    count = (int) (num * this.randNearOne());
    relation = this.relationMap.get("WorksReply");
    relyAttributes1 = this.relationMap.get("WebUser")
        .getAttributeValueList(0);
    relyAttributes2 = this.relationMap.get("Works")
        .getAttributeValueList(0);
    for (int i = 0; i < count; i++) {
      List<String> attributeList = new ArrayList<>();
      attributeList.add(this.randNumberString(30));
      attributeList
          .add(relyAttributes1.get(rand.nextInt(relyAttributes1.size())));
      attributeList
          .add(relyAttributes2.get(rand.nextInt(relyAttributes2.size())));
      attributeList.add("\'" + this.randLowercaseString(50) + "\'");
      relation.addTuple(new Tuple(attributeList));
    }

    // WorksAboutVehicleBrand - rely
    System.out.println("generate WorksAboutVehicleBrand");
    count = (int) (num * this.randNearOne());
    relation = this.relationMap.get("WorksAboutVehicleBrand");
    relyAttributes1 = this.relationMap.get("Works")
        .getAttributeValueList(0);
    relyAttributes2 = this.relationMap.get("VehicleBrand")
        .getAttributeValueList(0);
    for (int i = 0; i < count; i++) {
      List<String> attributeList = new ArrayList<>();
      attributeList
          .add(relyAttributes1.get(rand.nextInt(relyAttributes1.size())));
      attributeList
          .add(relyAttributes2.get(rand.nextInt(relyAttributes2.size())));
      relation.addTuple(new Tuple(attributeList));
    }
    System.out.println();
  }

  private void initialize() {
    RelyKeyLocation rely = null;
    Relation relation = null;

    Relations VehicleBrand = Relations.VehicleBrand;
    relation = new Relation(VehicleBrand.getName(),
        VehicleBrand.getAttributeNum(), VehicleBrand.getPrimaryKeyNum());
    relation.addAttributeName("vehicle_brand_id");
    relation.addAttributeName("vehicle_brand_name");
    this.relationMap.put(relation.getRelationName(), relation);

    Relations VehicleType = Relations.VehicleType;
    relation = new Relation(VehicleType.getName(),
        VehicleType.getAttributeNum(),
        VehicleType.getPrimaryKeyNum());
    relation.addAttributeName("vehicle_type_id");
    relation.addAttributeName("vehicle_brand_id");
    relation.addAttributeName("vehicle_speed");
    relation.addAttributeName("sale_status");
    rely = new RelyKeyLocation(1, VehicleBrand.getName(), 0);
    relation.addRely(rely);
    this.relationMap.put(relation.getRelationName(), relation);

    Relations VehicleColour = Relations.VehicleColour;
    relation = new Relation(VehicleColour.getName(),
        VehicleColour.getAttributeNum(),
        VehicleColour.getPrimaryKeyNum());
    relation.addAttributeName("vehicle_type_id");
    relation.addAttributeName("vehicle_colour");
    rely = new RelyKeyLocation(0, VehicleType.getName(), 0);
    relation.addRely(rely);
    this.relationMap.put(relation.getRelationName(), relation);

    Relations Manufacturer = Relations.Manufacturer;
    relation = new Relation(Manufacturer.getName(),
        Manufacturer.getAttributeNum(), Manufacturer.getPrimaryKeyNum());
    relation.addAttributeName("manufacturer_id");
    this.relationMap.put(relation.getRelationName(), relation);

    Relations Shop = Relations.Shop;
    relation = new Relation(Shop.getName(),
        Shop.getAttributeNum(), Shop.getPrimaryKeyNum());
    relation.addAttributeName("shop_id");
    this.relationMap.put(relation.getRelationName(), relation);

    Relations WebUser = Relations.WebUser;
    relation = new Relation(WebUser.getName(),
        WebUser.getAttributeNum(), WebUser.getPrimaryKeyNum());
    relation.addAttributeName("user_id");
    relation.addAttributeName("phone");
    this.relationMap.put(relation.getRelationName(), relation);

    Relations VehicleEvaluation = Relations.VehicleEvaluation;
    relation = new Relation(VehicleEvaluation.getName(),
        VehicleEvaluation.getAttributeNum(),
        VehicleEvaluation.getPrimaryKeyNum());
    relation.addAttributeName("evaluation_id");
    relation.addAttributeName("vehicle_type_id");
    relation.addAttributeName("user_id");
    relation.addAttributeName("shop_id");
    relation.addAttributeName("evaluation_date");
    relation.addAttributeName("evaluation_content");
    relation.addAttributeName("score");
    rely = new RelyKeyLocation(1, VehicleType.getName(), 0);
    relation.addRely(rely);
    rely = new RelyKeyLocation(2, WebUser.getName(), 0);
    relation.addRely(rely);
    rely = new RelyKeyLocation(3, Shop.getName(), 0);
    relation.addRely(rely);
    this.relationMap.put(relation.getRelationName(), relation);

    Relations Works = Relations.Works;
    relation = new Relation(Works.getName(),
        Works.getAttributeNum(), Works.getPrimaryKeyNum());
    relation.addAttributeName("works_id");
    relation.addAttributeName("user_id");
    relation.addAttributeName("works_type");
    relation.addAttributeName("works_content");
    rely = new RelyKeyLocation(1, WebUser.getName(), 0);
    relation.addRely(rely);
    this.relationMap.put(relation.getRelationName(), relation);

    Relations WorksReply = Relations.WorksReply;
    relation = new Relation(WorksReply.getName(),
        WorksReply.getAttributeNum(), WorksReply.getPrimaryKeyNum());
    relation.addAttributeName("reply_id");
    relation.addAttributeName("user_id");
    relation.addAttributeName("works_id");
    relation.addAttributeName("reply_content");
    rely = new RelyKeyLocation(1, WebUser.getName(), 0);
    relation.addRely(rely);
    rely = new RelyKeyLocation(2, Works.getName(), 0);
    relation.addRely(rely);
    this.relationMap.put(relation.getRelationName(), relation);

    Relations Indent = Relations.Indent;
    relation = new Relation(Indent.getName(),
        Indent.getAttributeNum(), Indent.getPrimaryKeyNum());
    relation.addAttributeName("indent_id");
    relation.addAttributeName("user_id");
    relation.addAttributeName("shop_id");
    relation.addAttributeName("vehicle_type_id");
    relation.addAttributeName("indent_date");
    rely = new RelyKeyLocation(1, WebUser.getName(), 0);
    relation.addRely(rely);
    rely = new RelyKeyLocation(2, Shop.getName(), 0);
    relation.addRely(rely);
    rely = new RelyKeyLocation(3, VehicleType.getName(), 0);
    relation.addRely(rely);
    this.relationMap.put(relation.getRelationName(), relation);

    Relations VehicleBrandManufacture = Relations.VehicleBrandManufacture;
    relation = new Relation(VehicleBrandManufacture.getName(),
        VehicleBrandManufacture.getAttributeNum(),
        VehicleBrandManufacture.getPrimaryKeyNum());
    relation.addAttributeName("manufacturer_id");
    relation.addAttributeName("vehicle_brand_id");
    rely = new RelyKeyLocation(0, Manufacturer.getName(), 0);
    relation.addRely(rely);
    rely = new RelyKeyLocation(1, VehicleBrand.getName(), 0);
    relation.addRely(rely);
    this.relationMap.put(relation.getRelationName(), relation);

    Relations VehicleTypeManufacture = Relations.VehicleTypeManufacture;
    relation = new Relation(VehicleTypeManufacture.getName(),
        VehicleTypeManufacture.getAttributeNum(),
        VehicleTypeManufacture.getPrimaryKeyNum());
    relation.addAttributeName("manufacturer_id");
    relation.addAttributeName("vehicle_type_id");
    relation.addAttributeName("quantity");
    rely = new RelyKeyLocation(0, Manufacturer.getName(), 0);
    relation.addRely(rely);
    rely = new RelyKeyLocation(1, VehicleType.getName(), 0);
    relation.addRely(rely);
    this.relationMap.put(relation.getRelationName(), relation);

    Relations VehicleTypeShop = Relations.VehicleTypeShop;
    relation = new Relation(VehicleTypeShop.getName(),
        VehicleTypeShop.getAttributeNum(),
        VehicleTypeShop.getPrimaryKeyNum());
    relation.addAttributeName("shop_id");
    relation.addAttributeName("vehicle_type_id");
    relation.addAttributeName("vehicle_price");
    rely = new RelyKeyLocation(0, Shop.getName(), 0);
    relation.addRely(rely);
    rely = new RelyKeyLocation(1, VehicleType.getName(), 0);
    relation.addRely(rely);
    this.relationMap.put(relation.getRelationName(), relation);

    Relations UserFriend = Relations.UserFriend;
    relation = new Relation(UserFriend.getName(),
        UserFriend.getAttributeNum(),
        UserFriend.getPrimaryKeyNum());
    relation.addAttributeName("user_idx");
    relation.addAttributeName("user_idy");
    rely = new RelyKeyLocation(0, WebUser.getName(), 0);
    relation.addRely(rely);
    rely = new RelyKeyLocation(1, WebUser.getName(), 0);
    relation.addRely(rely);
    this.relationMap.put(relation.getRelationName(), relation);

    Relations WorksAboutVehicleBrand = Relations.WorksAboutVehicleBrand;
    relation = new Relation(WorksAboutVehicleBrand.getName(),
        WorksAboutVehicleBrand.getAttributeNum(),
        WorksAboutVehicleBrand.getPrimaryKeyNum());
    relation.addAttributeName("works_id");
    relation.addAttributeName("vehicle_brand_id");
    rely = new RelyKeyLocation(0, Works.getName(), 0);
    relation.addRely(rely);
    rely = new RelyKeyLocation(1, VehicleBrand.getName(), 0);
    relation.addRely(rely);
    this.relationMap.put(relation.getRelationName(), relation);

  }

  private String relationToSQLForm(Relation relation) {
    String str = "INSERT INTO " + relation.getRelationName() + " VALUES\n";
    int count = 0;
    for (String key : relation.getTupleKeySet()) {
      Tuple tuple = relation.getTuple(key);
      String tupleStr = "\t(";
      for (int i = 0; i < tuple.size() - 1; i++) {
        tupleStr = tupleStr + tuple.getAttribute(i) + ", ";
      }
      tupleStr = tupleStr + tuple.getAttribute(tuple.size() - 1) + ")";
      count++;
      if (count < relation.getTupleKeySet().size()) {
        tupleStr = tupleStr + ",";
      }
      tupleStr = tupleStr + "\n";
      str = str + tupleStr;
    }
    str = str + ";\n";
    return str;
  }

  private String randUppercaseString(int length) {
    String str = "";
    Random rand = new Random();
    for (int i = 0; i < length; i++) {
      char ch = (char) ('A' + rand.nextInt(26));
      str = str + ch;
    }
    return str;
  }

  private String randLowercaseString(int length) {
    String str = "";
    Random rand = new Random();
    for (int i = 0; i < length; i++) {
      char ch = (char) ('a' + rand.nextInt(26));
      str = str + ch;
    }
    return str;
  }

  private String randNumberString(int length) {
    String str = "";
    Random rand = new Random();
    for (int i = 0; i < length; i++) {
      char ch = (char) ('0' + rand.nextInt(10));
      str = str + ch;
    }
    return str;
  }

  private double randNearOne() {
    Random rand = new Random();
    return (100 + rand.nextInt(30) - 15) / 100.0;
  }

  @Override
  public String toString() {
    String str = "";
    for (String key : this.relationMap.keySet()) {
      Relation relation = this.relationMap.get(key);
//      str = str + relation.toString() + "\n";
      str = str + this.relationToSQLForm(relation);
    }
    return str;
  }

}
