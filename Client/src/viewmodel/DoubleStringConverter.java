package viewmodel;

import javafx.util.StringConverter;

public class DoubleStringConverter extends StringConverter<Number>
{
  @Override public String toString(Number n)
  {
    if (n == null || n.intValue() == 0)
      return "";
    else
      return n.toString();
  }

  @Override public Number fromString(String s)
  {
    try
    {
      return Double.parseDouble(s);
    }
    catch (Exception e)
    {

      throw new IllegalArgumentException("Input a value");

    }
  }
}