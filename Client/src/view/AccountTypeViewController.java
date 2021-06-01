package view;

public class AccountTypeViewController extends ViewController
{
  @Override protected void init()
  {

  }

  @Override public void reset()
  {

  }

  public void onLogIn()
  {
    getViewHandler().openView(View.LOGIN_VIEW);
  }

  public void onBabysitter()
  {
    getViewHandler().openView(View.REGISTER_BABYSITTER_VIEW);
  }

  public void onParent()
  {
    getViewHandler().openView(View.REGISTER_PARENT_VIEW);
  }
}
