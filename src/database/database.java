package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


//import com.mysql.jdbc.Connection;

public class database {
//MySQL 8.0 ���ϰ汾 - JDBC �����������ݿ� URL
  static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  // 3306/�ͣ�֮��д���ݿ�����
  static final String DB_URL =
      "jdbc:mysql://localhost:3306/VehicleWebSite?useSSL=false&serverTimezone=UTC";


  // ���ݿ���û��������룬��Ҫ�����Լ�������
  static final String USER = "root";
  static final String PASS = "5218diandeng";

  public static void main(String[] args) {
    Connection conn = null;
    Statement stmt = null;
    try {
      // ע�� JDBC ����
      Class.forName(JDBC_DRIVER);

      // ������
      System.out.println("�������ݿ�...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      // ִ�в�ѯ
      System.out.println(" ʵ����Statement����...");
      
      GUI gui = new GUI(conn);
      //conn.close();
    } catch (SQLException se) {
      // ���� JDBC ����
      se.printStackTrace();
    } catch (Exception e) {
      // ���� Class.forName ����
      e.printStackTrace();
    } finally {
      // �ر���Դ
//      try {
//        if (stmt != null) {
//          stmt.close();
//        }
//      } catch (SQLException se2) {} // ʲô������
//      try {
//        if (conn != null) {
//          conn.close();
//        }
//      } catch (SQLException se) {
//        se.printStackTrace();
//      }
    }
    System.out.println("Goodbye!");
  }


}