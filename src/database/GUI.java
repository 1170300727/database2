package database;
import java.awt.Button; 
import java.awt.FlowLayout;
import java.awt.Frame; 
import java.awt.TextArea; 
import java.awt.TextField; 
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map; 

public class GUI {
  
  private static Statement stmt = null;
  private static Connection conn = null;
  static Frame frameSearchVehicleType = new Frame("searchVehicleType");
  static Frame menu = new Frame("menu");
  public GUI(Connection conn) {
    this.conn = conn;
  //创建窗体对象
    
    //设置窗体位置、大小
    frameSearchVehicleType.setBounds(300, 200, 900, 400);
    menu.setBounds(300, 200, 800, 400);
    //f.setb
    //设置窗体可见
    menu.setVisible(true);
    //调用查询车型窗口
    Menu(menu);
    SearchVehicleType(frameSearchVehicleType);
    //点x关闭窗口
    frameSearchVehicleType.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    menu.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
  }
  //This method is used for menu
  private static void Menu(Frame menu) {
    int x = 100;
    int y = 100;
    int width = 100;
    int height = 30;
    
    Button SearchVehicleType = new Button("SearchVehicleType");
    Button Insertion = new Button("Insert");
    
    SearchVehicleType.setBounds(x, y, width * 2, height);
    Insertion.setBounds(x, y + 50, width * 2, height);
    
    SearchVehicleType.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frameSearchVehicleType.setVisible(true);
        menu.setVisible(false);
      }
    });
    Insertion.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frameSearchVehicleType.setVisible(true);
        menu.setVisible(false);
      }
    });
    menu.setLayout(null);
    menu.add(SearchVehicleType);
    menu.add(Insertion);
  }
  // This method is used for searching vehicle type according to speed and status. 
  private static void SearchVehicleType(Frame frameSearchVehicleType) {
  //设置分本框
    //the position of left-up
    int x = 200;
    int y = 100;
    int width = 100;
    int height = 30;
    
    TextField statusText = new TextField();
    statusText.setBounds(x, y, width, height);
    TextField highestSpeedText = new TextField();
    highestSpeedText.setBounds(x, y + 50, width, height);
    TextField lowestSpeedText = new TextField();
    lowestSpeedText.setBounds(x, y + 100, width, height);
    
    TextField statusTip = new TextField();
    statusTip.setBounds(x - 160, y, width + 50, height);
    statusTip.setEditable(false);
    statusTip.setText("Input the status");
    TextField highestSpeedTip = new TextField();
    highestSpeedTip.setBounds(x - 160, y + 50, width + 50, height);
    highestSpeedTip.setEditable(false);
    highestSpeedTip.setText("Input the higest speed");
    TextField lowestSpeedTip = new TextField();
    lowestSpeedTip.setBounds(x - 160, y + 100, width + 50, height);
    lowestSpeedTip.setEditable(false);
    lowestSpeedTip.setText("Input the lowest speed");
    frameSearchVehicleType.add(statusTip);
    frameSearchVehicleType.add(highestSpeedTip);
    frameSearchVehicleType.add(lowestSpeedTip);
    
    //设置文本域
    TextArea searchArea = new TextArea(10, 40);
    searchArea.setBounds(x + width + 50, y, 500, 295);
    searchArea.setEditable(false);
    
    //设置按钮
    Button searchButton = new Button("Search");
    Button backButton = new Button("back");
    searchButton.setBounds(x - 100, y + 150, width * 2, height);
    backButton.setBounds(x - 100, y + 200, width * 2, height);
    
    //设置按钮功能
    searchButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        //获取文本框中内容，出去字符串前后的空格
        String status = statusText.getText().trim();
        String higestspeed = highestSpeedText.getText().trim();
        String lowestspeed = lowestSpeedText.getText().trim();
        String sql = createSqlForSearchingVehicleType(higestspeed, lowestspeed, status);
        System.out.println(sql);
        ResultSet rs;
        List list = null; 
        try {
          stmt = conn.createStatement();
          rs = stmt.executeQuery(sql);
          printResult(rs, searchArea);
        } catch (SQLException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }     
        
        //statusText.setText(null);
        //statusText.requestFocus();
      }
    }
        );
    backButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frameSearchVehicleType.setVisible(false);
        menu.setVisible(true);
      }
    });
    frameSearchVehicleType.setLayout(null);
    
    frameSearchVehicleType.add(backButton);
    frameSearchVehicleType.add(statusText);
    frameSearchVehicleType.add(highestSpeedText);
    frameSearchVehicleType.add(lowestSpeedText);
    frameSearchVehicleType.add(searchButton);
    frameSearchVehicleType.add(searchArea);
  }
  
  private static String createSqlForSearchingVehicleType(String higestSpeed,String lowestSpeed,String status) {
    String sql = "SELECT * FROM VehicleType WHERE sale_status = " + status + " AND" + " vehicle_speed >= " + lowestSpeed + " and vehicle_speed <= " + higestSpeed + ";";
    return sql;
  }
  
  private static  void printResult(ResultSet rs,TextArea searchArea) throws SQLException {
    ResultSetMetaData md = rs.getMetaData();//获取键名
    int columnCount = md.getColumnCount();//获取列的数量
    int max = getMaxLength(md);
    for (int i = 1; i <= columnCount; i++) {
      System.out.println(MakeUpLength(md.getColumnName(i), max).length());
      searchArea.append(MakeUpLength(md.getColumnName(i), max) + "  ");
    }
    searchArea.append("\n");
    while (rs.next()) {
    //Map rowData = new HashMap();//声明Map
    for (int i = 1; i <= columnCount; i++) {
      System.out.println(MakeUpLength(String.valueOf(rs.getObject(i)), max).length());
      searchArea.append(MakeUpLength(String.valueOf(rs.getObject(i)), max) + "  ");
    }
    searchArea.append("\n");
    }
  }
  
  private static String MakeUpLength(String s,int max) {
    int length = s.length();
    int lack = 0;
    if (length == max) {
      return s;
    } else {
      lack = max - length;
      for (int i = 0; i < lack; i++) {
        s = s + " ";
      }
    }
    return s;
  }
  private static int getMaxLength(ResultSetMetaData md) throws SQLException {
    int columnCount = md.getColumnCount();//获取列的数量
    int max = 0;
    for (int i = 1; i <= columnCount; i++) {
      if (md.getColumnName(i).length() > max) {
        max = md.getColumnName(i).length();
      }
    }
    return max;
  }

  
}


