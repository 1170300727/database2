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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class GUI {
  
  private static Statement stmt = null;
  private static Connection conn = null;
  static int x = 200;
  static int y = 100;
  static int width = 100;
  static int height = 30;
  static Frame frameSearchVehicleType = new Frame("searchVehicleType");
  static Frame UserModel = new Frame("UserModel");
  static Frame frameManufacturerModel = new Frame("ManufacturerModel");
  static Frame menu = new Frame("menu");
  static Frame addManufacturer = new Frame("AddManufacturer");
  static Frame modifyManufacturer = new Frame("ModifyManufacturer");
  static Frame deleteManufacturer = new Frame("DeleteManufacturer");
  static Frame addVehicleBrand = new Frame("AddVehicleBrand");
  static Frame modifyVehicleBrand = new Frame("ModifyVehicleBrand");
  static Frame deleteVehicleBrand = new Frame("DeleteVehicleBrand");
  static Frame addVehicleType = new Frame("AddVehicleType");
  static Frame modifyVehicleType = new Frame("ModifyVehicleType");
  static Frame deleteVehicleType = new Frame("DeleteVehicleType");
  static Frame vehicleWithBestEvaluation = new Frame("VehicleWithBestEvaluation");
  static Frame logIn = new Frame("LogIn");
  
  public GUI(Connection conn) {
    this.conn = conn;
    //AddFrame(menu);
    AddFrame(frameSearchVehicleType);
    AddFrame(UserModel);
    AddFrame(frameManufacturerModel);
    AddFrame(menu);
    AddFrame(addManufacturer);
    AddFrame(modifyManufacturer);
    AddFrame(deleteManufacturer);
    AddFrame(addVehicleBrand);
    AddFrame(modifyVehicleBrand);
    AddFrame(deleteVehicleBrand);
    AddFrame(addVehicleType);
    AddFrame(modifyVehicleType);
    AddFrame(deleteVehicleType);
    AddFrame(vehicleWithBestEvaluation);
    AddFrame(logIn);
    //设置窗体可见
    menu.setVisible(true);
    //调用查询车型窗口
    Menu(menu);
    userModel(UserModel);
    SearchVehicleType(frameSearchVehicleType);
    ManufacturerModel(frameManufacturerModel);
    AddManufacturer ();
    DeleteManufacturer ();
    ModifyManufacturer ();
    AddVehicleType();
    VehicleWithBestEvaluation();
    LogIn();
    //点x关闭窗口
  }

  private static void VehicleWithBestEvaluation() {  
    
    TextField statusText = new TextField();
    statusText.setBounds(x, y, width, height);
    vehicleWithBestEvaluation.add(statusText);
    
    AddTip("evaluation top:", x - 160, y, width, height, vehicleWithBestEvaluation);
    
    //设置文本域
    TextArea searchArea = new TextArea(10, 40);
    searchArea.setBounds(x + width + 50, y, 500, 295);
    searchArea.setEditable(false);
    vehicleWithBestEvaluation.add(searchArea);
    
    Button clear = new Button("clear");
    AddButton(clear, x - 100, y + 250, width * 2, height, vehicleWithBestEvaluation);
    clear.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        searchArea.setText(null);
      }
    });
    
    Button searchButton = new Button("search");
    AddButton(searchButton, x - 100, y + 150, width * 2, height, vehicleWithBestEvaluation);
    searchButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        //获取文本框中内容，出去字符串前后的空格
        String status = statusText.getText().trim();
        String sql = "select * from VehicleTypeAVGScore order by avg_score desc limit " + status + ";";
        System.out.println(sql);
        ResultSet rs;
        try {
          stmt = conn.createStatement();
          rs = stmt.executeQuery(sql);
          printResult(rs, searchArea);
        } catch (SQLException e1) {
          // TODO Auto-generated catch block
          String message = e1.getMessage();
          System.out.println(message);
          if (message.contains("Unknown column") | message.contains("Undeclared variable") | message.contains("SQL syntax")) {
            searchArea.append("Input format error\n");
          }
          //e1.printStackTrace();
        }   
      }
    });
    
    Button back = new Button("back");
    AddButton(back, x - 100, y + 200, width * 2, height, vehicleWithBestEvaluation);
    back.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        vehicleWithBestEvaluation .setVisible(false);
        UserModel.setVisible(true);
      }
    });
  }
  private static void LogIn() {  
    
    TextField statusText = new TextField();
    statusText.setBounds(x, y, width, height);
    logIn.add(statusText);
    
    TextField phone = new TextField();
    phone.setBounds(x, y + 50, width, height);
    logIn.add(phone);
    
    AddTip("user_id:", x - 160, y, width, height, logIn);
    AddTip("phone:", x - 160, y + 50, width, height, logIn);
    
    //设置文本域
    TextArea searchArea = new TextArea(10, 40);
    searchArea.setBounds(x + width + 50, y, 500, 295);
    searchArea.setEditable(false);
    logIn.add(searchArea);
    
    Button clear = new Button("clear");
    AddButton(clear, x - 100, y + 250, width * 2, height, logIn);
    clear.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        searchArea.setText(null);
      }
    });
    
    Button searchButton = new Button("logIn");
    AddButton(searchButton, x - 100, y + 150, width * 2, height, logIn);
    searchButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        //获取文本框中内容，出去字符串前后的空格
        String status = statusText.getText().trim();
        String Phone = phone.getText().trim();
        String sql = "select * from WebUser where user_id = '" + status + "' and phone = '" + Phone + "';";
        System.out.println(sql);
        ResultSet rs;
        try {
          stmt = conn.createStatement();
          rs = stmt.executeQuery(sql);
          if (rs.next()) {
            searchArea.append("log in success\n");
          } else {
            searchArea.append("Wrong user name or password\n");
          }
          //printResult(rs, searchArea);
        } catch (SQLException e1) {
          // TODO Auto-generated catch block
          String message = e1.getMessage();
          System.out.println(message);
          if (message.contains("Unknown column") | message.contains("Undeclared variable") | message.contains("SQL syntax")) {
            searchArea.append("Input format error\n");
          }
          //e1.printStackTrace();
        }   
      }
    });
    
    Button back = new Button("back");
    AddButton(back, x - 100, y + 200, width * 2, height, logIn);
    back.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        logIn .setVisible(false);
        UserModel.setVisible(true);
      }
    });
  }
  // This method is used for user to register, search the vehicle type,
  // publish works and so on.
  private static void userModel(Frame UserModel) {
    int x = 100;
    int y = 100;
    int width = 100;
    int height = 30;
    
    Button SearchVehicleType = new Button("SearchVehicleType");
    Button Insertion = new Button("Insert");
    Button back = new Button("back");
    
    
    Button LogIn = new Button("LogIn");
    AddButton(LogIn, x + width * 2 + 20, y + 50, width * 2, height,UserModel);
    LogIn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        logIn.setVisible(true);
        UserModel.setVisible(false);
      }
    });
    
    Button VehicleWithBestEvaluation = new Button("VehicleWithBestEvaluation");
    AddButton(VehicleWithBestEvaluation, x + width * 2 + 20, y, width * 2, height,UserModel);
    SearchVehicleType.setBounds(x, y, width * 2, height);
    Insertion.setBounds(x, y + 50, width * 2, height);
    back.setBounds(x, y + 100, width * 2, height);
    
    VehicleWithBestEvaluation.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        vehicleWithBestEvaluation.setVisible(true);
        UserModel.setVisible(false);
      }
    });
    
    SearchVehicleType.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frameSearchVehicleType.setVisible(true);
        UserModel.setVisible(false);
      }
    });
    Insertion.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frameSearchVehicleType.setVisible(true);
        UserModel.setVisible(false);
      }
    });
    back.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        menu.setVisible(true);
        UserModel.setVisible(false);
      }
    });
    
    UserModel.setLayout(null);
    UserModel.add(SearchVehicleType);
    UserModel.add(back);
    UserModel.add(Insertion);
  }
  // This method is used for Modifying Manufacturer data.
  private static void ModifyManufacturer () {
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
    
    TextField statusTip = new TextField();
    statusTip.setBounds(x - 160, y, width + 50, height);
    statusTip.setEditable(false);
    statusTip.setText("Input the old id");
    TextField highestSpeedTip = new TextField();
    highestSpeedTip.setBounds(x - 160, y + 50, width + 50, height);
    highestSpeedTip.setEditable(false);
    highestSpeedTip.setText("Input the new id");

    modifyManufacturer .add(statusTip);
    modifyManufacturer .add(highestSpeedTip);
    
    //设置文本域
    TextArea searchArea = new TextArea(10, 40);
    searchArea.setBounds(x + width + 50, y, 500, 295);
    searchArea.setEditable(false);
    
    //设置按钮
    Button searchButton = new Button("Modify");
    Button backButton = new Button("back");
    searchButton.setBounds(x - 100, y + 150, width * 2, height);
    backButton.setBounds(x - 100, y + 200, width * 2, height);
    
    //设置按钮功能
    searchButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        //获取文本框中内容，出去字符串前后的空格
        String status = statusText.getText().trim();
        String higestspeed = highestSpeedText.getText().trim();
        String sql = "UPDATE Manufacturer SET manufacturer_id = '" + higestspeed + "' WHERE manufacturer_id = '" + status + "'";
        System.out.println(sql);
        try {
          stmt = conn.createStatement();
          int ret = stmt.executeUpdate (sql);
          if (ret == 1) {
            searchArea.append("Modify success\n");
          } else {
            searchArea.append("Modify failure,please inpute the right ID\n");
          }
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
        modifyManufacturer .setVisible(false);
        frameManufacturerModel.setVisible(true);
      }
    });
    modifyManufacturer .setLayout(null);
    
    modifyManufacturer .add(backButton);
    modifyManufacturer .add(statusText);
    modifyManufacturer .add(highestSpeedText);
    modifyManufacturer .add(searchButton);
    modifyManufacturer .add(searchArea);
  }
  // This method is used for Manufacturer to update the Manufacturer data,
  // VehicleBrand data and VehicleType data.
  private static void ManufacturerModel(Frame frameManufacturerModel) {
    int x = 100;
    int y = 100;
    int width = 100;
    int height = 30;
    frameManufacturerModel.setLayout(null);

    //
    Button AddManufacturer = new Button("AddManufacturer");
    AddManufacturer.setBounds(x, y, width * 2, height);
    frameManufacturerModel.add(AddManufacturer);
    AddManufacturer.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        addManufacturer.setVisible(true);
        frameManufacturerModel.setVisible(false);
      }
    });
    
    Button ModifyManufacturer = new Button("ModifyManufacturer");
    ModifyManufacturer.setBounds(x, y + 50, width * 2, height);
    frameManufacturerModel.add(ModifyManufacturer);
    ModifyManufacturer.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        modifyManufacturer .setVisible(true);
        frameManufacturerModel.setVisible(false);
      }
    });
    
    Button DeleteManufacturer = new Button("DeleteManufacturer");
    DeleteManufacturer.setBounds(x, y + 100, width * 2, height);
    frameManufacturerModel.add(DeleteManufacturer);
    DeleteManufacturer.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        deleteManufacturer .setVisible(true);
        frameManufacturerModel.setVisible(false);
      }
    });
    
    Button AddVehicleBrand = new Button("AddVehicleBrand");
    AddVehicleBrand.setBounds(x + width * 2 + 20, y, width * 2, height);
    frameManufacturerModel.add(AddVehicleBrand);
    AddVehicleBrand.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        addVehicleBrand .setVisible(true);
        frameManufacturerModel.setVisible(false);
      }
    });
    
    Button ModifyVehicleBrand = new Button("ModifyVehicleBrand");
    ModifyVehicleBrand.setBounds(x + width * 2 + 20, y + 50, width * 2, height);
    frameManufacturerModel.add(ModifyVehicleBrand);
    ModifyVehicleBrand.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        modifyVehicleBrand .setVisible(true);
        frameManufacturerModel.setVisible(false);
      }
    });
    
    Button DeleteVehicleBrand = new Button("DeleteVehicleBrand");
    DeleteVehicleBrand.setBounds(x + width * 2 + 20, y + 100, width * 2, height);
    frameManufacturerModel.add(DeleteVehicleBrand);
    DeleteVehicleBrand.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        deleteVehicleBrand .setVisible(true);
        frameManufacturerModel.setVisible(false);
      }
    });
    
    Button AddVehicleType = new Button("AddVehicleType");
    AddVehicleType.setBounds(x + width * 4 + 40, y, width * 2, height);
    frameManufacturerModel.add(AddVehicleType);
    AddVehicleType.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        addVehicleType .setVisible(true);
        frameManufacturerModel.setVisible(false);
      }
    });
    
    Button ModifyVehicleType = new Button("ModifyVehicleType");
    ModifyVehicleType.setBounds(x + width * 4 + 40, y + 50, width * 2, height);
    frameManufacturerModel.add(ModifyVehicleType);
    ModifyVehicleType.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        modifyVehicleType .setVisible(true);
        frameManufacturerModel.setVisible(false);
      }
    });
    
    Button DeleteVehicleType = new Button("DeleteVehicleType");
    DeleteVehicleType.setBounds(x + width * 4 + 40, y + 100, width * 2, height);
    frameManufacturerModel.add(DeleteVehicleType);
    DeleteVehicleType.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        deleteVehicleType .setVisible(true);
        frameManufacturerModel.setVisible(false);
      }
    });
    
    Button back = new Button("back");
    back.setBounds(x, y + 150, width * 2, height);
    frameManufacturerModel.add(back);
    back.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        menu.setVisible(true);
        frameManufacturerModel.setVisible(false);
      }
    });
   
  }
  // This method is used for DeleteManufacturer.
  private static void DeleteManufacturer () {
    //设置分本框
      //the position of left-up
      int x = 200;
      int y = 100;
      int width = 100;
      int height = 30;
      
      TextField manufacturer_idText = new TextField();
      manufacturer_idText.setBounds(x, y, width, height);
      
      TextField statusTip = new TextField();
      statusTip.setBounds(x - 180, y, width + 70, height);
      statusTip.setEditable(false);
      statusTip.setText("Input the manufacturer_id");
      
      deleteManufacturer  .add(statusTip);
      
      //设置文本域
      TextArea AddArea = new TextArea(10, 40);
      AddArea.setBounds(x + width + 50, y, 500, 295);
      AddArea.setEditable(false);
      
      //设置按钮
      Button AddButton = new Button("Delete");
      Button backButton = new Button("back");
      AddButton.setBounds(x - 100, y + 150, width * 2, height);
      backButton.setBounds(x - 100, y + 200, width * 2, height);
      
      //设置按钮功能
      AddButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          //获取文本框中内容，出去字符串前后的空格
          String manufacturer_id = manufacturer_idText.getText().trim();
          String sql = "DELETE FROM Manufacturer WHERE manufacturer_id = '" + manufacturer_id + "';";
          System.out.println(sql);
          try {
            stmt = conn.createStatement();
            int ret = stmt.executeUpdate (sql);
            if (ret == 1) {
              AddArea.append("Delete success\n");
            } else {
              AddArea.append("Delete failure,please inpute the right ID\n");
            }
            
            //printResult(rs, AddArea);
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
          deleteManufacturer   .setVisible(false);
          frameManufacturerModel.setVisible(true);
        }
      });
      deleteManufacturer  .setLayout(null);
      
      deleteManufacturer  .add(backButton);
      deleteManufacturer  .add(manufacturer_idText);
      deleteManufacturer  .add(AddButton);
      deleteManufacturer  .add(AddArea);
    }
  // This method is used for AddManufacturer.
  private static void AddVehicleType () {
  //设置分本框
    //the position of left-up
    int x = 200;
    int y = 100;
    int width = 100;
    int height = 30;
    
    TextField vehicle_type_id = new TextField();
    vehicle_type_id.setBounds(x, y, width, height);
    addVehicleType.add(vehicle_type_id);
    TextField vehicle_brand_id = new TextField();
    vehicle_brand_id.setBounds(x, y + 50, width, height);
    addVehicleType.add(vehicle_brand_id);
    TextField vehicle_speed = new TextField();
    vehicle_speed.setBounds(x, y + 100, width, height);
    addVehicleType.add(vehicle_speed);
    TextField sale_status = new TextField();
    sale_status.setBounds(x, y + 150, width, height);
    addVehicleType.add(sale_status);
    
    
    TextField statusTip = new TextField();
    statusTip.setBounds(x - 180, y, width + 70, height);
    statusTip.setEditable(false);
    statusTip.setText("vehicle_type_id");
    addVehicleType.add(statusTip);
    
    TextField vehicle_brand_idTip = new TextField();
    vehicle_brand_idTip.setBounds(x - 180, y + 50, width + 70, height);
    vehicle_brand_idTip.setEditable(false);
    vehicle_brand_idTip.setText("vehicle_brand_id");
    addVehicleType.add(vehicle_brand_idTip);
    
    TextField vehicle_speedTip = new TextField();
    vehicle_speedTip.setBounds(x - 180, y + 100, width + 70, height);
    vehicle_speedTip.setEditable(false);
    vehicle_speedTip.setText("vehicle_speed");
    addVehicleType.add(vehicle_speedTip);
    
    TextField sale_statusTip = new TextField();
    sale_statusTip.setBounds(x - 180, y + 150, width + 70, height);
    sale_statusTip.setEditable(false);
    sale_statusTip.setText("sale_status");
    addVehicleType.add(sale_statusTip);
    
    //设置文本域
    TextArea AddArea = new TextArea(10, 40);
    AddArea.setBounds(x + width + 50, y, 500, 295);
    AddArea.setEditable(false);
    
    //设置按钮
    Button AddButton = new Button("Add");
    Button backButton = new Button("back");
    AddButton.setBounds(x - 100, y + 200, width * 2, height);
    backButton.setBounds(x - 100, y + 250, width * 2, height);
    
    //设置按钮功能
    AddButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        //获取文本框中内容，出去字符串前后的空格
        String Vehicle_type_id = vehicle_type_id.getText().trim();
        String Vehicle_brand_id = vehicle_brand_id.getText().trim();
        String Vehicle_speed = vehicle_speed.getText().trim();
        String Sale_status = sale_status.getText().trim();
        String sql = "INSERT INTO VehicleType VALUES ('" + Vehicle_type_id 
            + "'," + Vehicle_brand_id + "," + Vehicle_speed + "," + Sale_status + ");";
        System.out.println(sql);
        try {
          stmt = conn.createStatement();     
          int ret = stmt.executeUpdate (sql);
          if (ret == 1) {
            AddArea.append("Add success\n");
          } else {
            AddArea.append("Add failure\n");
          }
          
          //printResult(rs, AddArea);
        } catch (SQLException e1) {
          // TODO Auto-generated catch block
          String pattern = "(?<=FOREIGN KEY \\().*?(?=\\))";
          Pattern p = Pattern.compile(pattern);
          String exp = e1.getMessage();
          Matcher m = p.matcher(exp);
          if (m.find()) {
            String str2 = m.group();
            AddArea.append(str2 + " can't satify the foreign key\n");
          }
          //String sub = FOREIGN KEY (`vehicle_brand_id`;
          if (e1.getMessage().substring(0, 9).equals("Duplicate")) {
            AddArea.append("Duplicate Entry\n");
          }
          //e1.printStackTrace();
        }     
        
        //statusText.setText(null);
        //statusText.requestFocus();
      }
    }
        );
    backButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        addVehicleType .setVisible(false);
        frameManufacturerModel.setVisible(true);
      }
    });
    addVehicleType.setLayout(null);
    
    addVehicleType.add(backButton);
    
    addVehicleType.add(AddButton);
    addVehicleType.add(AddArea);
  }
  // This method is used for AddManufacturer.
  private static void AddManufacturer () {
  //设置分本框
    //the position of left-up
    int x = 200;
    int y = 100;
    int width = 100;
    int height = 30;
    
    TextField manufacturer_idText = new TextField();
    manufacturer_idText.setBounds(x, y, width, height);
    
    TextField statusTip = new TextField();
    statusTip.setBounds(x - 180, y, width + 70, height);
    statusTip.setEditable(false);
    statusTip.setText("Input the manufacturer_id");
    
    addManufacturer.add(statusTip);
    
    //设置文本域
    TextArea AddArea = new TextArea(10, 40);
    AddArea.setBounds(x + width + 50, y, 500, 295);
    AddArea.setEditable(false);
    
    //设置按钮
    Button AddButton = new Button("Add");
    Button backButton = new Button("back");
    AddButton.setBounds(x - 100, y + 150, width * 2, height);
    backButton.setBounds(x - 100, y + 200, width * 2, height);
    
    //设置按钮功能
    AddButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        //获取文本框中内容，出去字符串前后的空格
        String manufacturer_id = manufacturer_idText.getText().trim();
        String sql = "INSERT INTO Manufacturer VALUES ('" + manufacturer_id + "');";
        System.out.println(sql);
        try {
          stmt = conn.createStatement();
          int ret = stmt.executeUpdate (sql);
          if (ret == 1) {
            AddArea.append("Add success\n");
          } else {
            AddArea.append("Add failure\n");
          }
          
          //printResult(rs, AddArea);
        } catch (SQLException e1) {
          // TODO Auto-generated catch block
          System.out.println(e1.getMessage());
          if (e1.getMessage().substring(0, 9).equals("Duplicate")) {
            AddArea.append("Duplicate Entry\n");
          }
          //e1.printStackTrace();
        }     
        
        //statusText.setText(null);
        //statusText.requestFocus();
      }
    }
        );
    backButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        addManufacturer .setVisible(false);
        frameManufacturerModel.setVisible(true);
      }
    });
    addManufacturer.setLayout(null);
    
    addManufacturer.add(backButton);
    addManufacturer.add(manufacturer_idText);
    addManufacturer.add(AddButton);
    addManufacturer.add(AddArea);
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
    
    TextField statusInputTip = new TextField();
    statusInputTip.setBounds(x - 160, y - 50, width + 250, height);
    statusInputTip.setEditable(false);
    statusInputTip.setText("status:0-don't sale, 1-ready on sale, 2-on sale");
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
    
    Button clear = new Button("clear");
    clear.setBounds(x - 100, y + 250, width * 2, height);
    frameSearchVehicleType.add(clear);
    clear.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        searchArea.setText(null);
      }
    });
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
          String message = e1.getMessage();
          System.out.println(message);
          if (message.contains("Unknown column")) {
            searchArea.append("Input format error\n");
          }
          //e1.printStackTrace();
        }     
        
        //statusText.setText(null);
        //statusText.requestFocus();
      }
    }
        );
    backButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frameSearchVehicleType.setVisible(false);
        UserModel.setVisible(true);
      }
    });
    frameSearchVehicleType.setLayout(null);
    frameSearchVehicleType.add(statusInputTip);
    frameSearchVehicleType.add(backButton);
    frameSearchVehicleType.add(statusText);
    frameSearchVehicleType.add(highestSpeedText);
    frameSearchVehicleType.add(lowestSpeedText);
    frameSearchVehicleType.add(searchButton);
    frameSearchVehicleType.add(searchArea);
  }
  //This method is used for creating SQL For Searching Vehicle Type.
  private static String createSqlForSearchingVehicleType(String higestSpeed,String lowestSpeed,String status) {
    String sql = "SELECT * FROM VehicleType WHERE sale_status = " + status + " AND" + " vehicle_speed >= " + lowestSpeed + " and vehicle_speed <= " + higestSpeed + ";";
    return sql;
  }
  //This method is used for printing Result from SQL search.
  private static  void printResult(ResultSet rs,TextArea searchArea) throws SQLException {
    ResultSetMetaData md = rs.getMetaData();//获取键名
    int columnCount = md.getColumnCount();//获取列的数量
    int max = getMaxLength(md);
    for (int i = 1; i <= columnCount; i++) {
      //System.out.println(MakeUpLength(md.getColumnName(i), max).length());
      searchArea.append(MakeUpLength(md.getColumnName(i), max) + "  ");
    }
    searchArea.append("\n");
    while (rs.next()) {
    //Map rowData = new HashMap();//声明Map
    for (int i = 1; i <= columnCount; i++) {
      //System.out.println(MakeUpLength(String.valueOf(rs.getObject(i)), max).length());
      searchArea.append(MakeUpLength(String.valueOf(rs.getObject(i)), max) + "  ");
    }
    searchArea.append("\n");
    }
  }
  //This method is used for Making Up the Length of String to output in order.
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
  //This method is used for get the max length of the clomn name.
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
  private static void AddFrame(Frame f) {
    f.setBounds(300, 200, 900, 400);
    f.setLayout(null);
    f.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
  }
  // This method is used for menu
  private static void Menu(Frame menu) {
    int x = 100;
    int y = 100;
    int width = 100;
    int height = 30;
    
    Button userModel = new Button("userModel");
    Button ManufacturerModel = new Button("ManufacturerModel");
    
    userModel.setBounds(x, y, width * 2, height);
    ManufacturerModel.setBounds(x, y + 50, width * 2, height);
    
    userModel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        UserModel.setVisible(true);
        menu.setVisible(false);
      }
    });
    ManufacturerModel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frameManufacturerModel.setVisible(true);
        menu.setVisible(false);
      }
    });
    menu.setLayout(null);
    menu.add(userModel);
    menu.add(ManufacturerModel);
  }
  // This method is used for Adding Button and Seting the position and size of Button.
  private static void AddButton(Button button,int x,int y,int width,int height,Frame f) {
    button.setBounds(x, y, width, height);
    f.add(button);
  }
  private static void AddTip(String s,int x,int y,int width,int height,Frame f) {
    TextField statusTip = new TextField();
    statusTip.setBounds(x, y, width, height);
    statusTip.setEditable(false);
    statusTip.setText(s);
    f.add(statusTip);
  }
}


