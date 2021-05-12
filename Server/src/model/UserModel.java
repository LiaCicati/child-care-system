package model;

public interface UserModel
{
  Account login(String email, String password);
//  void registerBabysitter(String userName, String password, String email,
//      String firstName, String lastName, int birthDay, int birthMonth,
//      int birthYear, double paymentPerHour, String mainLanguage,
//      double babysittingExperience, boolean hasFirstAidCertificate);
  void registerBabysitter(String userName, String password, String email,
      String firstName, String lastName);
  void registerParent(String userName, String password, String email,
      String firstName, String lastName, boolean hasPets);
  void logout(Account user);
  AccountList getAccountList();
  AccountList getBabysitterList();
  AccountList getParentList();


}
