package dao;

import model.*;

import java.sql.Date;
import java.sql.SQLException;

public interface AccountDAO extends DAO
{
  void create(Account account) throws SQLException;
  void update(Account account) throws SQLException;
  void delete(Account account) throws SQLException;
  AccountList getAllAccounts() throws SQLException;
  AccountList allParents() throws SQLException;
  AccountList allBabysitters() throws SQLException;
  Account readByEmail(String email) throws SQLException;
//  void createParent(String firstName, String lastName,
//      String userName, String email, String password, boolean hasPets) throws SQLException;
  void createParent(String email,  boolean hasPets) throws SQLException;

  void createBabysitter( String email,  MyDateTime birthday,
      double babysittingExperience, double paymentPerHour, String mainLanguage,
      boolean hasFirstAidCertificate) throws SQLException;
//  Parent getParent(String firstName, String lastName,
//      String userName, String email, String password, boolean hasPets) throws SQLException;
  Babysitter getBabysitter(String email) throws SQLException;

  void removeParent(Parent parent) throws SQLException;
  void removeBabysitter(Babysitter babysitter) throws SQLException;
}
