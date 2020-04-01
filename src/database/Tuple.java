package database;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a packed datatype of tuples in a relation.
 * 
 * @author AtoshDustosh
 */
public class Tuple {
  private List<String> attributeList = new ArrayList<>();

  public Tuple() {
  }

  public Tuple(List<String> attributeList) {
    this.setAllAttributes(attributeList);
  }

  public void setAllAttributes(List<String> attributeList) {
    this.attributeList.clear();
    this.attributeList.addAll(attributeList);
  }

  public String getAttribute(int index) {
    return this.attributeList.get(index);
  }

  public List<String> getAttributeList() {
    List<String> newList = new ArrayList<>();
    newList.addAll(this.attributeList);
    return newList;
  }

  public int size() {
    return this.attributeList.size();
  }

  @Override
  public String toString() {
    return this.attributeList.toString();
  }

}
