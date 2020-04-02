package database;

/**
 * This class is used for expressing the dependency of relations'
 * attributes, which is in fact a network of foreign keys in the
 * database.
 * 
 * @author AtoshDustosh
 */
public class RelyKeyLocation {
  private int foreignKeyIndex = 0;
  private String relyRelationName = null;
  private int relyKeyAttributeIndex = 0;

  public RelyKeyLocation() {

  }

  public RelyKeyLocation(int foreignKeyIndex, String relyRelationName,
      int relyKeyAttributeIndex) {
    this.foreignKeyIndex = foreignKeyIndex;
    this.relyRelationName = relyRelationName;
    this.relyKeyAttributeIndex = relyKeyAttributeIndex;
  }

  public int getForeignKeyIndex() {
    return this.foreignKeyIndex;
  }

  public String getRelyRelationNamem() {
    return this.relyRelationName;
  }

  public int getRelyKeyAttributeIndex() {
    return this.relyKeyAttributeIndex;
  }
}
