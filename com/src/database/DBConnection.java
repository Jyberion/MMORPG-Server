package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

  private Connection connection;

  public DBConnection() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      this.connection = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/mmorpg", 
        "username", 
        "password"
      );
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public Connection getConnection() {
    return this.connection; 
  }

}