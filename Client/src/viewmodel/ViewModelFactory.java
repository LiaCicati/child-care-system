package viewmodel;

import model.Model;

public class ViewModelFactory
{
  private RegisterBabysitterViewModel registerBabysitterViewModel;
  private LoginViewModel loginViewModel;

  public ViewModelFactory(Model model)
  {
    this.registerBabysitterViewModel = new RegisterBabysitterViewModel(model);
this.loginViewModel = new LoginViewModel(model);
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
