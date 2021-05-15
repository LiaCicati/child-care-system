package viewmodel;

import javafx.util.StringConverter;

public class StringIntegerConverter extends StringConverter<Number>
{
  private int emptyValue;

  public StringIntegerConverter(int emptyValue)
  {
    this.emptyValue = emptyValue;
  }

  @Override public String toString(Number n)
  {
    if (n == null || n.intValue() == emptyValue)
    {
      return "";
    }
    return n.toString();
  }

  @Override public Number fromString(String s)
  {
    try
    {
      return Integer.parseInt(s);
    }
    catch (Exception e)
    {
      return emptyValue;
    }
  }
}