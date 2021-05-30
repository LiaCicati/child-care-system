package database;

import model.Kid;
import model.MyDateTime;
import model.Parent;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KidManager
{
  public KidManager()
  {
  }

  public void addKid(Parent parent, Kid kid) throws SQLException
  {
    try (Connection connection = Database.getInstance().getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("INSERT INTO kid VALUES (?,?,?,?)");
      statement.setString(1, parent.getEmail());
      statement.setInt(2, kid.getId());
      statement.setBoolean(3, kid.getGender());
      statement.setDate(4, Date.valueOf(kid.getDateOfBirth()));
      statement.setString(5, kid.getHealthConditions());

      statement.executeUpdate();
    }
  }

  public void editKidData(Parent parent, Kid kid) throws SQLException
  {
    try (Connection connection = Database.getInstance().getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE kid SET  gender =? , birthday =?, health_condition = ? WHERE parent_email = ? AND ID = ?;");
      statement.setBoolean(1, kid.getGender());
      statement.setDate(2, Date.valueOf(kid.getDateOfBirth()));
      statement.setString(3, kid.getHealthConditions());
      statement.executeUpdate();

    }

  }

  public void getAllKids() throws SQLException
  {
    List<Kid> kids = new ArrayList<>();
    try (Connection connection = Database.getInstance().getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM kid;");
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next())
      {
        String parent_email = resultSet.getString("parent_email");
        int ID = resultSet.getInt("ID");
        boolean gender = resultSet.getBoolean("gender");
        Date date = (Date) resultSet.getObject(" birthday ");
        MyDateTime birthday = new MyDateTime(date.toLocalDate().getDayOfMonth(),
            date.toLocalDate().getMonthValue(), date.toLocalDate().getYear());
        String health = resultSet.getString("health_condition");

      }
    }
  }
}



