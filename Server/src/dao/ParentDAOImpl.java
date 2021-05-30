//package dao;
//
//import model.Parent;
//
//import java.sql.*;
//
//public class ParentDAOImpl implements ParentDAO
//{
//  private static ParentDAOImpl instance;
//
//  private ParentDAOImpl() throws SQLException
//  {
//    DriverManager.registerDriver(new org.postgresql.Driver());
//  }
//
//  public static synchronized ParentDAOImpl getInstance() throws SQLException
//  {
//
//    if (instance == null)
//    {
//      instance = new ParentDAOImpl();
//    }
//    return instance;
//  }
//
//  private Connection getConnection() throws SQLException
//  {
//    return DriverManager.getConnection(URL, USERNAME, PASSWORD);
//  }
//
//  @Override public void create(Parent parent) throws SQLException
//  {
//    try (Connection connection = getConnection())
//    {
//      PreparedStatement statement = connection
//          .prepareStatement("INSERT INTO parent VALUES (?,?)");
//      statement.setString(1, parent.getEmail());
//      statement.setBoolean(2,parent.hasPets());
//      statement.executeUpdate();
//    }
//  }
//
//  @Override public Parent readByEmail(String email) throws SQLException{
//    try (Connection connection = getConnection())
//    {
//      PreparedStatement statement = connection
//          .prepareStatement("SELECT * FROM parent WHERE email = ?");
//      statement.setString(1,email);
//      ResultSet resultSet = statement.executeQuery();
//      if (resultSet.next()) {
//        String first_name = resultSet.getString("first_name");
//        String last_name = resultSet.getString("last_name");
//        String
//        return new Parent(id, name);
//      } else {
//        return null;
//      }
//    }
//  }
//}
