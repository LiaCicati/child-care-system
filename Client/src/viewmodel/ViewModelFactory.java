package viewmodel;

import model.LocalModel;

public class ViewModelFactory
{
  private RegisterBabysitterViewModel registerBabysitterViewModel;
  private LoginViewModel loginViewModel;

  public ViewModelFactory(LocalModel localModel)
  {
    this.registerBabysitterViewModel = new RegisterBabysitterViewModel(
        localModel);
    this.loginViewModel = new LoginViewModel(localModel);
  }

  public RegisterBabysitterViewModel getRegisterBabysitterViewModel()
  {
    return registerBabysitterViewModel;
  }

  public LoginViewModel getLoginViewModel()
  {
    return loginViewModel;
  }
}
