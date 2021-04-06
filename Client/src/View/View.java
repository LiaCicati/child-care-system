package View;

public enum View
{
  LOGIN_VIEW("LoginView.fxml");

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
