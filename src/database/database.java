package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import com.mysql.jdbc.Connection;

public class database {
//MySQL 8.0 ���ϰ汾 - JDBC �����������ݿ� URL
  static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  // 3306/�ͣ�֮��д���ݿ�����
  static final String DB_URL =
      "jdbc:mysql://localhost:3306/product?useSSL=false&serverTimezone=UTC";


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
      stmt = conn.createStatement();
      String sql;
      sql = "SELECT maker FROM product";
      ResultSet rs = stmt.executeQuery(sql);

      // չ����������ݿ�
      while (rs.next()) {
        // ͨ���ֶμ���
        // int id = rs.getInt("id");
        String maker = rs.getString("maker");
        // String url = rs.getString("url");

        // �������
        // System.out.print("ID: " + id);
        System.out.print(", ������: " + maker);
        // System.out.print(", վ�� URL: " + url);
        System.out.print("\n");
      }
      // ��ɺ�ر�
      rs.close();
      stmt.close();
      conn.close();
    } catch (SQLException se) {
      // ���� JDBC ����
      se.printStackTrace();
    } catch (Exception e) {
      // ���� Class.forName ����
      e.printStackTrace();
    } finally {
      // �ر���Դ
      try {
        if (stmt != null) {
          stmt.close();
        }
      } catch (SQLException se2) {} // ʲô������
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException se) {
        se.printStackTrace();
      }
    }
    System.out.println("Goodbye!");
  }

}