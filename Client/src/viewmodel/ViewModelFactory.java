package viewmodel;

import model.LocalModel;

public class ViewModelFactory
{
  private RegisterBabysitterViewModel registerBabysitterViewModel;
  private LoginViewModel loginViewModel;
  private BabysitterProfileViewModel babysitterProfileViewModel;
  private RegisterParentViewModel registerParentViewModel;
  private ParentProfileViewModel parentProfileViewModel;
  private ViewState viewState;

  public ViewModelFactory(LocalModel localModel)
  {
    this.viewState = new ViewState();
    this.registerBabysitterViewModel = new RegisterBabysitterViewModel(
        localModel);
    this.registerParentViewModel = new RegisterParentViewModel(localModel);
    this.loginViewModel = new LoginViewModel(localModel, viewState);
    this.babysitterProfileViewModel = new BabysitterProfileViewModel(localModel,
        viewState);
    this.parentProfileViewModel = new ParentProfileViewModel(localModel,
        viewState);
  }

  public RegisterBabysitterViewModel getRegisterBabysitterViewModel()
  {
    return registerBabysitterViewModel;
  }

  public LoginViewModel getLoginViewModel()
  {
    return loginViewModel;
  }

  public BabysitterProfileViewModel getBabysitterProfileViewModel()
  {
    return babysitterProfileViewModel;
  }

  public RegisterParentViewModel getRegisterParentViewModel()
  {
    return registerParentViewModel;
  }

  public ParentProfileViewModel getParentProfileViewModel()
  {
    return parentProfileViewModel;
  }
}
