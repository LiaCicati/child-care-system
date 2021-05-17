package view;

public enum View
{
  LOGIN_VIEW("LoginView.fxml"),
  REGISTER_BABYSITTER_VIEW("RegisterBabysitterView.fxml"),
  REGISTER_PARENT_VIEW("RegisterParentView.fxml"),
  BABYSITTER_PROFILE_VIEW("BabysitterProfileView.fxml"),
  PARENT_PROFILE_VIEW("ParentProfileView.fxml"),
  ACCOUNT_TYPE_VIEW("AccountTypeView.fxml"),
  EDIT_BABYSITTER_PROFILE_VIEW("EditBabysitterProfileView.fxml"),
  ADD_EDIT_KID_DATA_VIEW("AddEditKidDataView.fxml"),
  EDIT_PARENT_DATA_VIEW("EditParentProfileView.fxml"),
  BOOKING_BABYSITTERS_VIEW("BookingBabysitterView.fxml");


  private String fxmlFile;

  View(String fxmlFile)
  {
    this.fxmlFile = fxmlFile;
  }

  public String getFxmlFile()
  {
    return fxmlFile;
  }
}
