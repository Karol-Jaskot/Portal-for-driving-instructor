package pl.jaskot.portalfordrivinginstructor.Backend.configuration;

import pl.jaskot.portalfordrivinginstructor.Backend.MainManager;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.Article;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.Material;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.User;

import java.time.LocalDateTime;

public class TestData {

    private static boolean isTest = true;
    private static boolean isMaterials = true;
    private static boolean isArticles = true;

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
            isTest = false;
        }

        if(isArticles){
            Article a1 = new Article();
            a1.setTitle("Wieści");
            a1.setMessage("Treść i info");
            //a1.setAuthor("Stefan");
            a1.setPublic(true);
            a1.setCreateTime(LocalDateTime.now());
            mainManager.getArticleManager().addArticle(a1);

            Article a2 = new Article();
            a2.setTitle("Wieści 2");
            a2.setMessage("Treść i info");
            //a1.setAuthor("Stefan");
            a2.setPublic(true);
            a2.setCreateTime(LocalDateTime.now());

            mainManager.getArticleManager().addArticle(a2);
            isArticles = false;
        }

        if (isMaterials) {
            Material a1 = new Material();
            a1.setTitle("Książka 1");
            a1.setDescription("Treść i info");
            a1.setLinkToFile("https://vaadin.com/components/vaadin-anchor");
            a1.setPublic(true);
            mainManager.getMaterialsManager().addMaterial(a1);

            Material a2 = new Material();
            a2.setTitle("Książka 2");
            a2.setDescription("Treść i info");
            a2.setLinkToFile("https://vaadin.com/components/vaadin-anchor");
            a2.setPublic(true);
            mainManager.getMaterialsManager().addMaterial(a2);
            isMaterials = false;
        }

    }
}
