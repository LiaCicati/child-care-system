package Model;

public class Test
{
  public static void main(String[] args)
  {

    MyDateTime dateTime = new MyDateTime(2,5,2021);
    System.out.println(dateTime.toString());

    Babysitter babysitter = new Babysitter("Loredana", 13, 2, 2001, 56,"English", 3, false);
    babysitter.addLanguage("Danish");
    System.out.println(babysitter);
    System.out.println(babysitter.getLanguages());
    System.out.println(babysitter.getMainLanguage());
    System.out.println(babysitter.getAge(babysitter.getDateOfBirth()));

  }
}
