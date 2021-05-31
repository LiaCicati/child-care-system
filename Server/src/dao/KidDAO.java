package dao;

import model.Kid;
import model.MyDateTime;
import model.Parent;

import java.sql.SQLException;

public interface KidDAO extends DAO
{
  Kid create(Kid kid, Parent parent) throws SQLException;
//  Kid readById(int ID) throws SQLException;
  void update(Kid kid) throws SQLException;
  void delete(Kid kid) throws SQLException;
  void getAllKids() throws SQLException;
}
