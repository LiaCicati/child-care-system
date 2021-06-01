package model;

import java.sql.Types;

public class Test
{
  public static void main(String[] args)
  {
//
//    MyDateTime dateTime = new MyDateTime(1,5,2021,3,45);
//    System.out.println(dateTime.toString());

//    Babysitter babysitter = new Babysitter("lori", "lialialia", "lori@mail.ru", "Loredana",
//        "Cicati", new MyDateTime(13, 2, 2034),2,  30, "English", true);
//    babysitter.addLanguage("Danish");
//    System.out.println(babysitter);
//    System.out.println(babysitter.getLanguages());
//    System.out.println(babysitter.getMainLanguage());
//    System.out.println(babysitter.getAge(babysitter.getDateOfBirth()));
//MyDateTime dateTime1 = new MyDateTime(1,5,2034,3,44);
//    MyDateTime dateTime3 = new MyDateTime(3,5,2034,3,45);
//    System.out.println("age: " + babysitter.getAge());
//    System.out.println(babysitter.getDateOfBirth());
//    System.out.println(dateTime.isBefore(dateTime1));
    Babysitter babysitter2 = new Babysitter("Loredana", "Cicati", "lori",
        "lori@mail.ru", "lialialia", new MyDateTime(13, 2, 2001), 2, 15,
        "English", false);
    Parent parent1 = new Parent("Ana", "Peters", "ana", "ana@gmail.com",
        "password", false);
    Booking booking1 = new Booking(
        new TimeInterval(new MyDateTime(12, 6, 2021, 12, 5),
            new MyDateTime(12, 6, 2021, 14, 5)), parent1, babysitter2, null);
    booking1.setStatus("Accepted");
    System.out.println(booking1.getTime());
    System.out.println("HEREEE: " + booking1.getTime().getEndTime().getDateTime());



  }
}
