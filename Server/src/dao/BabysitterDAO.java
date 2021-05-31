package dao;

import model.AccountList;
import model.MyDateTime;

import java.sql.SQLException;

public interface BabysitterDAO extends DAO
{
  void createBabysitter(String firstName, String lastName,
      String userName, String email, String password, MyDateTime birthday,
      double babysittingExperience, double paymentPerHour, String mainLanguage,
      boolean hasFirstAidCertificate) throws SQLException;
  AccountList allBabysitters() throws SQLException;
}
