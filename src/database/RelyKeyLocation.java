package database;

/**
 * This class is used for expressing the dependency of relations' attributes,
 * which is in fact a network of foreign keys in the database.
 * 
 * @author AtoshDustosh
 */
public class RelyKeyLocation {
  private String relyRelationName = null;
  private int relyKeyAttributeIndex = 0;

  public RelyKeyLocation(String relyRelationName, int relyKeyAttributeIndex) {
    this.relyRelationName = relyRelationName;
    this.relyKeyAttributeIndex = relyKeyAttributeIndex;
  }

  public String getRelyRelationNamem() {
    return this.relyRelationName;
  }

  public int getRelyKeyAttributeIndex() {
    return this.relyKeyAttributeIndex;
  }
}
