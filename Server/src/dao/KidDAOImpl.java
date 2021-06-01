package dao;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KidDAOImpl implements KidDAO
{
  private static KidDAOImpl instance;

  private KidDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized KidDAOImpl getInstance() throws SQLException
  {

    if (instance == null)
    {
      instance = new KidDAOImpl();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(URL, USERNAME, PASSWORD);
  }

  @Override public Kid create(Kid kid, Parent parent) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("INSERT INTO kid VALUES (?,?,?,?,?);");
      statement.setString(1, parent.getEmail());
      statement.setInt(2, kid.getId());
      statement.setBoolean(3, kid.getGender());
      statement.setDate(4, Date.valueOf(String.valueOf(kid.getDateOfBirth())));
      statement.setString(5, kid.getHealthConditions());
      statement.executeUpdate();
      return new Kid(kid.getId(), kid.getDateOfBirth().getDayOfMonth(),kid.getDateOfBirth().getMonthValue(),kid.getDateOfBirth().getYear(),kid.getGender(),
          kid.getHealthConditions());
    }
  }

 @Override public void update(Kid kid) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE kid SET  gender = ? birthday = ?, health_condition = ? WHERE ID = ?");

      statement.setBoolean(1, kid.getGender());
      statement.setDate(2, Date.valueOf(kid.getDateOfBirth()));
      statement.setString(3, kid.getHealthConditions());
      statement.setInt(4, kid.getId());
      statement.executeUpdate();
    }
  }


  @Override public void delete(Kid kid) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM kid WHERE ID = ?");
      statement.setInt(1, kid.getId());
      statement.executeUpdate();
    }
  }

//  public ArrayList<Kid> getAllKids() throws SQLException
//  {
//    ArrayList<Kid> kidList = new ArrayList<>();
//    try (Connection connection = getConnection())
//    {
//      PreparedStatement statement = connection
//          .prepareStatement("SELECT * FROM kid;");
//      ResultSet resultSet = statement.executeQuery();
//      while (resultSet.next())
//      {
//        String parent_email = resultSet.getString("parent_email");
//        int ID = resultSet.getInt("ID");
//        boolean gender = resultSet.getBoolean("gender");
////        Date date = (Date) resultSet.getObject(" birthday ");
////        MyDateTime birthday = new MyDateTime(date.toLocalDate().getDayOfMonth(),
////            date.toLocalDate().getMonthValue(), date.toLocalDate().getYear());
//        Date date = (Date) resultSet.getObject("birthday");
//        MyDateTime birthday = new MyDateTime(date.toLocalDate().getDayOfMonth(),
//            date.toLocalDate().getMonthValue(), date.toLocalDate().getYear());
//        String health = resultSet.getString("health_condition");
//        Kid kid = new Kid(ID,birthday.getDay(),birthday.getMonth(),birthday.getYear(),gender,health);
//        kidList.add(kid);
//
//      }
//return kidList;
//    }
//  }

  public AccountList allParents() throws SQLException
  {
    AccountList parents = new AccountList();
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM account, parent WHERE account.email = parent.email;");
      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next())
      {
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String password = resultSet.getString("password");
        boolean hasPets = resultSet.getBoolean("has_pets");
        Account parent = new Parent(firstName, lastName, username, email,
            password, hasPets);
        parents.addAccount(parent);
      }
      return parents;
    }
  }


}
  //  @Override public Kid readById(int ID) throws SQLException{
  //    try (Connection connection = getConnection())
  //    {
  //      PreparedStatement statement = connection
  //          .prepareStatement("SELECT * FROM kid JOIN parent ON email = parent_email WHERE ID = ?");
  //      statement.setInt(1,ID);
  //      ResultSet resultSet = statement.executeQuery();
  //      if (resultSet.next()) {
  //        String parent_email = resultSet.getString("parent_email");
  //        int id = resultSet.getInt("ID");
  //        boolean gender = resultSet.getBoolean("gender");
  //        Date date = (Date) resultSet.getObject(" birthday ");
  //        MyDateTime birthday = new MyDateTime(date.toLocalDate().getDayOfMonth(),
  //            date.toLocalDate().getMonthValue(), date.toLocalDate().getYear());
  //        String health = resultSet.getString("health_condition");
  //        String username = resultSet.getString("username");
  //        String email = resultSet.getString("email");
  //        String firstName = resultSet.getString("first_name");
  //        String lastName = resultSet.getString("last_name");
  //        String password = resultSet.getString("password");
  //        boolean hasPets = resultSet.getBoolean("has_pets");
  //        Parent parent = new Parent()
  //            Kid kid  = c
  //
  //
  //      }
//}



//  public static Kid addKid(ResultSet resultSet) throws SQLException{
//    String parent_email = resultSet.getString("parent_email");
//    int id = resultSet.getInt("ID");
//    boolean gender = resultSet.getBoolean("gender");
//    Date date = (Date) resultSet.getObject(" birthday ");
//    MyDateTime birthday = new MyDateTime(date.toLocalDate().getDayOfMonth(),
//        date.toLocalDate().getMonthValue(), date.toLocalDate().getYear());
//    String health = resultSet.getString("health_condition");
//    String username = resultSet.getString("username");
//    String email = resultSet.getString("email");
//    String firstName = resultSet.getString("first_name");
//    String lastName = resultSet.getString("last_name");
//    String password = resultSet.getString("password");
//    boolean hasPets = resultSet.getBoolean("has_pets");
//    Parent parent = new Parent(firstName,lastName,username,email,password,hasPets);
//    return new Kid(id,birthday.getDay(),birthday.getMonth(),birthday.getYear(),gender,health);
//  }
//}
