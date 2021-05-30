package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database
{
  private final String url;
  private final String username;
  private final String password;
  public static String URL = "jdbc:postgresql://localhost:5432/postgres?currentSchema=childcare";
  public static String USERNAME = "postgres";
  public static String PASSWORD = "kimnamjoon.";
  private static Database instance;
  private static final Object lock = new Object();

  private Database(String url, String username, String password)
  {
    this.password = password;
    this.url = url;
    this.username = username;
  }

  private Database()
  {
    this(URL, USERNAME, PASSWORD);
  }


  public static Database getInstance() {
    if (instance == null) {
      synchronized (lock) {
        if (instance == null)
          instance = new Database();
      }
    }
    return instance;
  }



  public Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(url, username, password);
  }
}
