package view;

import javafx.scene.layout.Region;
import viewmodel.ViewModelFactory;

public abstract class ViewController
{
  private Region root;
  private ViewHandler viewHandler;
  private ViewModelFactory viewModelFactory;

  protected abstract void init();

  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModelFactory = viewModelFactory;
    this.root = root;
    init();
  }

  public abstract void reset();

  public Region getRoot()
  {
    return root;
  }

  public ViewModelFactory getViewModelFactory()
  {
    return viewModelFactory;
  }

  public ViewHandler getViewHandler()
  {
    return viewHandler;
  }

}
