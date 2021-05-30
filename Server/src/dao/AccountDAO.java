package dao;

import model.Account;
import model.AccountList;
import model.Babysitter;
import model.Parent;

import java.sql.SQLException;

public interface AccountDAO extends DAO
{
//  void create(Account account) throws SQLException;
  void update(Account account) throws SQLException;
  void delete(Account account) throws SQLException;
  AccountList getAllAccounts() throws SQLException;
  AccountList allParents() throws SQLException;
  AccountList allBabysitters() throws SQLException;
  Account readByEmail(String email) throws SQLException;
  void createParent(String firstName, String lastName,
      String userName, String email, String password, boolean hasPets) throws SQLException;
//  Parent getParent(String firstName, String lastName,
//      String userName, String email, String password, boolean hasPets) throws SQLException;
  Babysitter getBabysitter(String email) throws SQLException;
}
