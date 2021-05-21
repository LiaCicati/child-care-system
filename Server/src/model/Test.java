package model;

public class Test
{
  public static void main(String[] args)
  {

    MyDateTime dateTime = new MyDateTime(1,5,2021,3,45);
    System.out.println(dateTime.toString());

    Babysitter babysitter = new Babysitter("lori", "lialialia", "lori@mail.ru", "Loredana",
        "Cicati", new MyDateTime(13, 2, 2034),2,  30, "English", true);
//    babysitter.addLanguage("Danish");
//    System.out.println(babysitter);
//    System.out.println(babysitter.getLanguages());
//    System.out.println(babysitter.getMainLanguage());
//    System.out.println(babysitter.getAge(babysitter.getDateOfBirth()));
MyDateTime dateTime1 = new MyDateTime(1,5,2034,3,44);
    MyDateTime dateTime3 = new MyDateTime(3,5,2034,3,45);
    System.out.println("age: " + babysitter.getAge());
    System.out.println(babysitter.getDateOfBirth());
    System.out.println(dateTime.isBefore(dateTime1));


  }
}
