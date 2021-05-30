package database;

import model.Parent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ParentManager
{
  public ParentManager(){}

  public void addParent(Parent parent) throws SQLException{
    try (Connection connection = Database.getInstance().getConnection()) {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO parent VALUES (?)");
      statement.setString(1, parent.getEmail());
      statement.executeUpdate();
    }
  }

  public void removeParent(Parent parent) throws SQLException{
    try (Connection connection = Database.getInstance().getConnection()) {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM parent WHERE email = ?");
      statement.setString(1, parent.getEmail());
      statement.executeUpdate();
    }
  }
}
