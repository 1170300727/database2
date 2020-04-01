package args;

/**
 * This enum class stores relations of this database. Each instance has a name
 * for the relation and the count of the number of the relation's attributes and
 * the count of attributes that compose its primary key.
 * 
 * @author AtoshDustosh
 */
public enum Relations {
  VehicleBrand("VehicleBrand", 2, 1),
  VehicleType("VehicleType", 4, 1),
  VehicleColour("VehicleCfolour", 2, 2),
  Manufacturer("Manufacturer", 1, 1),
  Shop("Shop", 1, 1),
  WebUser("WebUser", 2, 1),
  VehicleEvaluation("VehicleEvaluation", 7, 1),
  Works("Works", 4, 1),
  WorksReply("WorksReply", 4, 1),
  Indent("Indent", 5, 1),
  VehicleBrandManufacture("VehicleBrandManufacture", 2, 2),
  VehicleTypeManufacture("VehicleTypeManufacture", 3, 2),
  VehicleTypeShop("VehicleTypeShop", 3, 2),
  UserFriend("UserFriend", 2, 2),
  WorksAboutVehicleBrand("WorksAboutVehicleBrand", 2, 2),;

  private String name = null;
  private int attributeNum = 0;
  private int primaryKeyNum = 0;

  private Relations(String name, int attributeNum, int primaryKeyNum) {
    this.name = name;
    this.attributeNum = attributeNum;
    this.primaryKeyNum = primaryKeyNum;
  }

  public String getName() {
    return this.name;
  }

  public int getAttributeNum() {
    return this.attributeNum;
  }

  public int getPrimaryKeyNum() {
    return this.primaryKeyNum;
  }
}
