package dao;

import model.*;

import java.sql.SQLException;

public interface AccountDAO extends DAO
{
  void create(Account account) throws SQLException;
  void update(Account account) throws SQLException;
  void delete(Account account) throws SQLException;
  AccountList allParents() throws SQLException;
  AccountList allBabysitters() throws SQLException;
  void createParent(String email, boolean hasPets) throws SQLException;
  void createBabysitter(String email, MyDateTime birthday,
      double babysittingExperience, double paymentPerHour, String mainLanguage,
      boolean hasFirstAidCertificate) throws SQLException;
  Babysitter getBabysitter(String email) throws SQLException;
  void removeParent(Parent parent) throws SQLException;
  void removeBabysitter(Babysitter babysitter) throws SQLException;
  AccountList getAll() throws SQLException;
  Account readBabysitterByEmail(String email) throws SQLException;
  Account readParentByEmail(String email) throws SQLException;
}
