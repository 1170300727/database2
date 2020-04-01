package database;

/**
 * This class is used for expressing the dependency of relations' attributes,
 * which is in fact a network of foreign keys in the database.
 * 
 * @author AtoshDustosh
 */
public class RelyKeyLocation {
  private int relyRelationSerialNum = 0;
  private int relyKeyAttributeIndex = 0;

  public RelyKeyLocation(int relyRelationSerialNum, int relyKeyAttributeIndex) {
    this.relyRelationSerialNum = relyRelationSerialNum;
    this.relyKeyAttributeIndex = relyKeyAttributeIndex;
  }

  public int getRelyRelationSerialNum() {
    return this.relyRelationSerialNum;
  }

  public int getRelyKeyAttributeIndex() {
    return this.relyKeyAttributeIndex;
  }
}
