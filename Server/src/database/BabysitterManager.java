package database;

import model.Babysitter;
import model.Parent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BabysitterManager
{
  public BabysitterManager(){}

  public void addBabysitter(Babysitter babysitter) throws SQLException
  {
    try (Connection connection = Database.getInstance().getConnection()) {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO babysitter VALUES (?)");
      statement.setString(1, babysitter.getEmail());
      statement.executeUpdate();
    }
  }

  public void removeBabysitter(Babysitter babysitter) throws SQLException{
    try (Connection connection = Database.getInstance().getConnection()) {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM babysitter WHERE email = ?");
      statement.setString(1, babysitter.getEmail());
      statement.executeUpdate();
    }
  }
}
