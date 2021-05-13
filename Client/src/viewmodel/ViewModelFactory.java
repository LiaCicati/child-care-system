package viewmodel;

import model.LocalModel;

public class ViewModelFactory
{
  private RegisterBabysitterViewModel registerBabysitterViewModel;

  public ViewModelFactory(LocalModel localModel)
  {
    this.registerBabysitterViewModel = new RegisterBabysitterViewModel(
        localModel);
  }

  public RegisterBabysitterViewModel getRegisterBabysitterViewModel()
  {
    return registerBabysitterViewModel;
  }
}
