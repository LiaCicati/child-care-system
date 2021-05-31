package dao;


import model.*;

import java.sql.*;

public class AccountDAOImpl implements AccountDAO
{
  private static AccountDAOImpl instance;

  public AccountDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized AccountDAOImpl getInstance() throws SQLException
  {

    if (instance == null)
    {
      instance = new AccountDAOImpl();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(URL, USERNAME, PASSWORD);
  }

  @Override public void create(Account account) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO account(username, email, first_name, last_name,password) VALUES(?, ?, ?, ?, ?) ;");

      statement.setString(1, account.getUserName());
      statement.setString(2, account.getEmail());
      statement.setString(3, account.getFirstName());
      statement.setString(4, account.getLastName());
      statement.setString(5, account.getPassword());
      statement.executeUpdate();
//      PreparedStatement statement1 = connection.prepareStatement(
//          "INSERT INTO parent( email, has_pets) VALUES(?, ?);");
//      statement1.setString(1,account.getEmail());
//      statement1.setBoolean(2,);

    }
  }



//  @Override public void createParent(String firstName, String lastName,
//      String userName, String email, String password, boolean hasPets) throws SQLException{
//    try (Connection connection = getConnection())
//    {
//      PreparedStatement statement = connection.prepareStatement(
//          "INSERT INTO parent ( username,email,password,first_name, last_name,has_pets) VALUES(?, ?,?,?,?,?);");
//      statement.setString(1, userName);
//      statement.setString(2,email);
//      statement.setString(3,password);
//      statement.setString(4,firstName);
//      statement.setString(5,lastName);
//      statement.setBoolean(6, hasPets);
//      statement.executeUpdate();
//    }
//  }

  @Override public void createParent(String email,  boolean hasPets) throws SQLException{
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO parent ( email,has_pets) VALUES(?, ?);");
//      statement.setString(1, userName);
      statement.setString(1,email);
//      statement.setString(3,password);
//      statement.setString(4,firstName);
//      statement.setString(5,lastName);
      statement.setBoolean(2, hasPets);
      statement.executeUpdate();
    }
  }


  @Override public void createBabysitter( String email,  MyDateTime birthday,
      double babysittingExperience, double paymentPerHour, String mainLanguage,
      boolean hasFirstAidCertificate) throws SQLException{
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO babysitter ( email,birthday,payment,language,years_of_experience,first_aid_certificate) VALUES(?, ?,?,?,?,?);");

      statement.setString(1,email);
      statement.setObject(2,birthday);
      statement.setDouble(3,babysittingExperience);
      statement.setDouble(4,paymentPerHour);
      statement.setString(5,mainLanguage);
      statement.setBoolean(6,hasFirstAidCertificate);
      statement.executeUpdate();
    }
  }

  @Override public void update(Account account) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE account set username = ?, email = ?, first_name = ?,last_name = ?,password = ?  ;");
      statement.setString(1, account.getUserName());
      statement.setString(2, account.getEmail());
      statement.setString(3, account.getFirstName());
      statement.setString(4, account.getLastName());
      statement.setString(5, account.getPassword());
      statement.executeUpdate();
    }
  }

  @Override public void delete(Account account) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM account WHERE email = ?");
      statement.setString(1, account.getEmail());
      statement.executeUpdate();
    }
  }

  public AccountList getAllAccounts() throws SQLException
  {

    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM account;");
      PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM parent");
      ResultSet resultSet1 = statement1.executeQuery();
      ResultSet resultSet = statement.executeQuery();
      AccountList accounts = new AccountList();
      while (resultSet.next() && resultSet1.next())
      {
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String password = resultSet.getString("password");
//        Date date = (Date) resultSet.getObject(" birthday ");
//        MyDateTime birthday = new MyDateTime(date.toLocalDate().getDayOfMonth(),
//            date.toLocalDate().getMonthValue(), date.toLocalDate().getYear());
//        double experience = resultSet.getDouble("years_of_experience");
//        double payment = resultSet.getDouble("payment ");
//        String mainLanguage = resultSet.getString("language");
//        boolean hasFirstAidCertificate = resultSet
//            .getBoolean("first_aid_certificate");
        boolean hasPets = resultSet1.getBoolean("has_pets");
//        Babysitter babysitter = new Babysitter(firstName, lastName, username,
//            email, password, birthday, experience, payment, mainLanguage,
//            hasFirstAidCertificate);
//        accounts.addAccount(babysitter);
//        Parent parent = new Parent(firstName, lastName, username, email,
//            password, hasPets);
        Account parent = new Parent(firstName, lastName, username, email, password, hasPets);
        accounts.addAccount(parent);

      }
      return accounts;
    }
  }

//  public Parent getParent(String firstName, String lastName,
//      String userName, String email, String password, boolean hasPets) throws SQLException
//  {
//    try (Connection connection = getConnection())
//    {
//      PreparedStatement statement = connection
//          .prepareStatement("SELECT * from parent WHERE username=? ");
//      statement.setString(1, userName);
//      ResultSet resultSet = statement.executeQuery();
//      if (resultSet.next())
//      {
//        String emailResult = resultSet.getString("email");
////        String username = resultSet.getString("username");
//        String first_Name = resultSet.getString("first_name");
//        String last_Name = resultSet.getString("last_name");
//        String pass = resultSet.getString("password");
//        boolean has_Pets = resultSet.getBoolean("has_pets");
//        return new Parent(first_Name, last_Name, userName, emailResult, pass,
//            has_Pets);
//      }
//      else
//      {
//        throw new IllegalStateException("Account not in database");
//      }
//    }
//  }

  public AccountList allParents() throws SQLException
  {
    AccountList parents = new AccountList();
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM account, parent ;");
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
//        Parent account = getParent(firstName,lastName,username,email,password,hasPets);
//        parents.addAccount(account);

      }
      return parents;
    }
  }
  public Babysitter getBabysitter(String email) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * from babysitter WHERE email=? ");
      statement.setString(1, email);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        String emailResult = resultSet.getString("email");
        String username = resultSet.getString("username");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String password = resultSet.getString("password");
        Date date = (Date) resultSet.getObject(" birthday ");
        MyDateTime birthday = new MyDateTime(date.toLocalDate().getDayOfMonth(),
            date.toLocalDate().getMonthValue(), date.toLocalDate().getYear());
        double experience = resultSet.getDouble("years_of_experience");
        double payment = resultSet.getDouble("payment ");
        String mainLanguage = resultSet.getString("language");
        boolean hasFirstAidCertificate = resultSet.getBoolean("first_aid_certificate");
        return new Babysitter(firstName, lastName, username, emailResult, password,
            birthday, experience, payment, mainLanguage, hasFirstAidCertificate);
      }
      else
      {
        throw new IllegalStateException("Account not in database");
      }
    }
  }


  public AccountList allBabysitters() throws SQLException
  {    AccountList babysitters = new AccountList();
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM account, babysitter ;");
      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next())
      {
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String password = resultSet.getString("password");
        Date date = (Date) resultSet.getObject(" birthday ");
        MyDateTime birthday = new MyDateTime(date.toLocalDate().getDayOfMonth(),
            date.toLocalDate().getMonthValue(), date.toLocalDate().getYear());

        double experience = resultSet.getDouble("years_of_experience");
        double payment = resultSet.getDouble("payment ");
        String mainLanguage = resultSet.getString("language");
        boolean hasFirstAidCertificate = resultSet.getBoolean("first_aid_certificate");
        Account babysitter = new Babysitter(firstName, lastName, username,
            email, password, birthday, experience, payment, mainLanguage,
            hasFirstAidCertificate);
        babysitters.addAccount(babysitter);
      }
      return babysitters;
    }
  }

  @Override public Account readByEmail(String email) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM parent WHERE email = ?");
      statement.setString(1, email);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        String username = resultSet.getString("username");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String password = resultSet.getString("password");
        boolean hasPets = resultSet.getBoolean("has_pets");

        Date date = (Date) resultSet.getObject(" birthday ");
        MyDateTime birthday = new MyDateTime(date.toLocalDate().getDayOfMonth(),
            date.toLocalDate().getMonthValue(), date.toLocalDate().getYear());
        double experience = resultSet.getDouble("years_of_experience");
        double payment = resultSet.getDouble("payment ");
        String mainLanguage = resultSet.getString("language");
        boolean hasFirstAidCertificate = resultSet
            .getBoolean("first_aid_certificate");

        return new Parent(firstName, lastName, username, email, password,
            hasPets);
//        return new Babysitter(firstName, lastName, username, email, password,
//                    birthday, experience, payment, mainLanguage, hasFirstAidCertificate);
              }
        return null;
      }

}

  public void removeParent(Parent parent) throws SQLException{
    try (Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM parent WHERE email = ?");
      statement.setString(1, parent.getEmail());
      statement.executeUpdate();
    }
  }

  public void removeBabysitter(Babysitter babysitter) throws SQLException{
    try (Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM babysitter WHERE email = ?");
      statement.setString(1, babysitter.getEmail());
      statement.executeUpdate();
    }
  }
}


