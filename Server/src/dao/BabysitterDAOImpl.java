package dao;

import model.Account;
import model.AccountList;
import model.Babysitter;
import model.MyDateTime;

import java.sql.*;

public class BabysitterDAOImpl implements BabysitterDAO
{
  private static BabysitterDAOImpl instance;
  public BabysitterDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized BabysitterDAOImpl getInstance() throws SQLException
  {

    if (instance == null)
    {
      instance = new BabysitterDAOImpl();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(URL, USERNAME, PASSWORD);
  }
  @Override public void createBabysitter(String firstName, String lastName,
      String userName, String email, String password, MyDateTime birthday,
      double babysittingExperience, double paymentPerHour, String mainLanguage,
      boolean hasFirstAidCertificate) throws SQLException{
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO babysitter ( username,email,password,first_name, last_name,birthday,payment,language,years_of_experience,first_aid_certificate) VALUES(?, ?,?,?,?,?,?,?,?,?);");
      statement.setString(1, userName);
      statement.setString(2,email);
      statement.setString(3,password);
      statement.setString(4,firstName);
      statement.setString(5,lastName);
      statement.setObject(6, birthday);
      statement.setDouble(7,babysittingExperience);
      statement.setDouble(8,paymentPerHour);
      statement.setString(9,mainLanguage);
      statement.setBoolean(10,hasFirstAidCertificate);
      statement.executeUpdate();
    }
  }


  public AccountList allBabysitters() throws SQLException
  {    AccountList babysitters = new AccountList();
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM babysitter ;");
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
}
