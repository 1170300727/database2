package database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class is used for storing all complete tuples of a relation.
 * 
 * @author AtoshDustosh
 */
public class Relation {
  private String relationName = null;

  private int attributeNum = 0;
  private int primaryKeyNum = 0;

  /*
   * \TODO add field "private List<String> attributeNameList"
   */

  /*
   * We use Map<String, Tuple> to locate a tuple quickly. The key "String" of it
   * is converted using primary key attributes of "Tuple" as tupleKey.
   */
  private Map<String, Tuple> tuples = new HashMap<String, Tuple>();

  public Relation(String name, int attributeNum, int primaryKeyNum) {
    this.relationName = name;
    this.attributeNum = attributeNum;
    this.primaryKeyNum = primaryKeyNum;
  }

  /**
   * Add a new tuple into this relation.
   * 
   * @param newTuple new tuple
   * @return 0 if added successfully; 1 otherwise
   */
  public int addTuple(Tuple newTuple) {
    if (newTuple.size() != this.attributeNum) {
      System.out.println("error: attributes lost when adding a new tuple.");
      return 1;
    } else {
    }
    List<String> primaryKeyAttributeList = new ArrayList<String>();
    for (int i = 0; i < this.primaryKeyNum; i++) {
      primaryKeyAttributeList.add(newTuple.getAttribute(i));
    }
    String tupleKey = primaryKeyAttributeList.toString();
    this.tuples.put(tupleKey, newTuple);
    return 0;
  }

  public String getRelationName() {
    return this.relationName;
  }

  public int getAttributeNum() {
    return this.attributeNum;
  }

  public int getPrimaryKeyNum() {
    return this.primaryKeyNum;
  }

  public Set<String> getTupleKeySet() {
    return this.tuples.keySet();
  }

  public Tuple getTuple(String tupleKey) {
    return this.tuples.get(tupleKey);
  }

}
