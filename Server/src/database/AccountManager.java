package database;

import model.*;

import java.sql.*;

public class AccountManager
{
  private ParentManager parentManager;
  private BabysitterManager babysitterManager;
  private KidManager kidManager;

  public AccountManager(ParentManager parentManager,
      BabysitterManager babysitterManager, KidManager kidManager)
  {
    this.parentManager = parentManager;
    this.babysitterManager = babysitterManager;
    this.kidManager = kidManager;
  }

  public void addAccount(Account account) throws SQLException
  {
    try (Connection connection = Database.getInstance().getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO account(username, email, first_name, last_name,password) VALUES(?, ?, ?, ?, ?);");
      statement.setString(1, account.getUserName());
      statement.setString(2, account.getEmail());
      statement.setString(3, account.getFirstName());
      statement.setString(4, account.getLastName());
      statement.setString(5, account.getPassword());
      statement.executeUpdate();

    }
  }

  public Parent getParent(String email) throws SQLException
  {
    try (Connection connection = Database.getInstance().getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * from parent WHERE email=? ");
      statement.setString(1, email);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        String emailResult = resultSet.getString("email");
        String username = resultSet.getString("username");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String password = resultSet.getString("password");
        boolean hasPets = resultSet.getBoolean("has_pets");
        return new Parent(firstName, lastName, username, emailResult, password,
            hasPets);
      }
      else
      {
        throw new IllegalStateException("Account not in database");
      }
    }
  }

  public Babysitter getBabysitter(String email) throws SQLException
  {
    try (Connection connection = Database.getInstance().getConnection())
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

  public void addParent(Parent parent) throws SQLException
  {
    addAccount(parent);
  }

  public void addBabysitter(Babysitter babysitter) throws SQLException
  {
    addAccount(babysitter);
  }

  public void update(Account account) throws SQLException
  {
    try (Connection connection = Database.getInstance().getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE account set username = ?, email = ?, first_name = ?,last_name = ?,password = ?  ");
      statement.setString(1, account.getUserName());
      statement.setString(2, account.getEmail());
      statement.setString(3, account.getFirstName());
      statement.setString(4, account.getLastName());
      statement.setString(5, account.getPassword());
      statement.executeUpdate();

    }
  }

  public AccountList getAllAccounts() throws SQLException
  {

    try (Connection connection = Database.getInstance().getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT account.*,parent.*, babysitter.*");
      ResultSet resultSet = statement.executeQuery();
      AccountList accounts = new AccountList();
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
        boolean hasFirstAidCertificate = resultSet
            .getBoolean("first_aid_certificate");
        boolean hasPets = resultSet.getBoolean("has_pets");
        Babysitter babysitter = new Babysitter(firstName, lastName, username,
            email, password, birthday, experience, payment, mainLanguage,
            hasFirstAidCertificate);
        accounts.addAccount(babysitter);
        Parent parent = new Parent(firstName, lastName, username, email,
            password, hasPets);
        accounts.addAccount(parent);

      }
      return accounts;
    }
  }

  public AccountList allParents() throws SQLException
  {
    try (Connection connection = Database.getInstance().getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM parent ");
      ResultSet resultSet = statement.executeQuery();
      AccountList parents = new AccountList();
      while (resultSet.next())
      {
        String email = resultSet.getString("email");
        Parent account = getParent(email);
        parents.addAccount(account);

      }
      return parents;
    }
  }

  public AccountList allBabysitters() throws SQLException
  {
    try (Connection connection = Database.getInstance().getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM babysitter ");
      ResultSet resultSet = statement.executeQuery();
      AccountList babysitters = new AccountList();
      while (resultSet.next())
      {
        String email = resultSet.getString("email");
        Babysitter account = getBabysitter(email);
        babysitters.addAccount(account);

      }
      return babysitters;
    }
  }
}