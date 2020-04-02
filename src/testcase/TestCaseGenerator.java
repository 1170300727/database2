package testcase;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import args.Relations;
import database.Relation;
import database.RelyKeyLocation;

/**
 * Unfinished. All logical designs are not definitive.
 * 
 * @author AtoshDustosh
 */
public class TestCaseGenerator {

  private String testCaseFilePath = null;

  private Map<String, Relation> relationMap = new HashMap<>();

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
   * Generate test cases under designated directory of a designated
   * number.
   * 
   * @param num number of test cases to be generated
   */
  public void generate(int num) {

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

  /**
   * Create a file under designated directory and use it as path for
   * test case generation.
   * 
   * @param filePath file path of the created file
   */
  private void createFile(String filePath) {
    this.testCaseFilePath = filePath;
    File file = new File(this.testCaseFilePath);
    try {
      // if file doesn't exist
      if (!file.exists()) {
        file.createNewFile();
        System.out.println("File created - " + filePath);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
