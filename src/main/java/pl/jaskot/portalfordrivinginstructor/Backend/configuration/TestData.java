package pl.jaskot.portalfordrivinginstructor.Backend.configuration;

import pl.jaskot.portalfordrivinginstructor.Backend.MainManager;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.User;

public class TestData {

    private static boolean isTest = true;

    public TestData(MainManager mainManager){
        if(isTest){
            //TODO poprawić na wczytywanie z bazy
            User user = new User();
            user.setFirstName("Stefan");
            user.setLastName("Kornik");
            user.setEmail("stefan@wp.pl");
            user.setPhoneNumber("123");
            user.setPassword("Stefan15");
            user.setFirstLogin(true);

            User user2 = new User();
            user2.setFirstName("Marcin");
            user2.setLastName("Czwarty");
            user2.setEmail("mmmcz@wp.pl");
            user2.setPhoneNumber("123");

            mainManager.getUsersManager().addUser(user);
            mainManager.getUsersManager().addUser(user2);
        }

        isTest = false;
    }
}
