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
  
  public GUI(Connection conn) {
    this.conn = conn;
  //�����������
    Frame f = new Frame("search");
    //���ô���λ�á���С
    f.setBounds(300, 200, 800, 400);
    //f.setb
    //���ô���ɼ�
    f.setVisible(true);
    //���ò�ѯ���ʹ���
    SearchVehicleType(f);
    //��x�رմ���
    f.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
  }
  // This method is used for searching vehicle type according to speed and status. 
  private static void SearchVehicleType(Frame f) {
  //���÷ֱ���
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
    //�����ı���
    TextArea searchArea = new TextArea(10, 40);
    searchArea.setBounds(x + width + 100, y, 300, 300);
    //���ð�ť
    Button searchButton = new Button("Search");
    searchButton.setBounds(x - 100, y + 150, width * 2, height);
    //���ð�ť����
    searchButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        //��ȡ�ı��������ݣ���ȥ�ַ���ǰ��Ŀո�
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
          list = convertList(rs);
        } catch (SQLException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }     
        printResult(list, searchArea);
        //statusText.setText(null);
        //statusText.requestFocus();
      }
    }
        );
    f.add(statusText);
    f.add(highestSpeedText);
    f.add(lowestSpeedText);
    f.add(searchButton);
    f.add(searchArea);
  }
  
  private static String createSqlForSearchingVehicleType(String higestSpeed,String lowestSpeed,String status) {
    String sql = "SELECT * FROM VehicleType WHERE sale_status = " + status + " AND" + " vehicle_speed >= " + lowestSpeed + " and vehicle_speed <= " + higestSpeed + ";";
    return sql;
  }
  
  private static  void printResult(List list,TextArea searchArea) {
    Iterator<Object> iterator = list.iterator();
    while (iterator.hasNext()) {
      Object element = iterator.next(); 
      searchArea.append(String.valueOf(element));
      searchArea.append("\n");
  }
  }
  
  //��SQL���صĲ�ѯ������浽list
  private static List convertList(ResultSet rs) throws SQLException{
    List list = new ArrayList();
    ResultSetMetaData md = rs.getMetaData();//��ȡ����
    int columnCount = md.getColumnCount();//��ȡ�е�����
    while (rs.next()) {
    Map rowData = new HashMap();//����Map
    for (int i = 1; i <= columnCount; i++) {
    rowData.put(md.getColumnName(i), rs.getObject(i));//��ȡ������ֵ
    }
    list.add(rowData);
    }
    return list;
  }
  
}


