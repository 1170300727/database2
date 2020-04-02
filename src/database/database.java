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
//MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
  static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  // 3306/和？之间写数据库名称
  static final String DB_URL =
      "jdbc:mysql://localhost:3306/VehicleWebSite?useSSL=false&serverTimezone=UTC";


  // 数据库的用户名与密码，需要根据自己的设置
  static final String USER = "root";
  static final String PASS = "5218diandeng";

  public static void main(String[] args) {
    Connection conn = null;
    Statement stmt = null;
    try {
      // 注册 JDBC 驱动
      Class.forName(JDBC_DRIVER);

      // 打开链接
      System.out.println("连接数据库...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      // 执行查询
      System.out.println(" 实例化Statement对象...");
      
      GUI gui = new GUI(conn);
      //conn.close();
    } catch (SQLException se) {
      // 处理 JDBC 错误
      se.printStackTrace();
    } catch (Exception e) {
      // 处理 Class.forName 错误
      e.printStackTrace();
    } finally {
      // 关闭资源
//      try {
//        if (stmt != null) {
//          stmt.close();
//        }
//      } catch (SQLException se2) {} // 什么都不做
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