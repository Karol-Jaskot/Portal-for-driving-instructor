package pl.jaskot.portalfordrivinginstructor.Frontend.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import org.springframework.beans.factory.annotation.Autowired;
import pl.jaskot.portalfordrivinginstructor.Backend.MainManager;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.User;
import pl.jaskot.portalfordrivinginstructor.Backend.managers.UsersManager;
import pl.jaskot.portalfordrivinginstructor.Frontend.components.ArticleDialog;
import pl.jaskot.portalfordrivinginstructor.Frontend.components.RegistrationDialog;

import java.util.HashMap;
import java.util.Map;


public class ManagerView extends VerticalLayout {

    private H1 title;
    private MainManager mainManager;
    private UsersManager usersManager;
    private Button login, logout;
    private Button registration;
    private Tab settings, users, questionares;
    private Tabs tabs;
    private Div page1, page2, page3, pages;



    private ArticleDialog articleDialog;

    public ManagerView(MainManager mainManager) {
        this.mainManager = mainManager;
        this.usersManager = mainManager.getUsersManager();
        setSizeFull();
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        addClassName("manager-view");

        createContent();
        add(title);
        if(mainManager.isActive()){
            if(mainManager.isAdmin()){
                createAdminContent();
                setAdminPage();
            }
            add(logout);
        }else {
            add(login);
        }
    }

    private void createContent() {
        title = new H1("Zarządzanie aplikacją");

        login = new Button("Zaloguj", event -> goLogin());
        logout = new Button("Wyloguj", event -> goLogout());
        registration = new Button("Dodaj użytkownika",event -> goRegistration());
    }

    private void createAdminContent(){

        settings = new Tab("Ustawienia");
        page1 = new Div();
        page1.setText("Page#1");

         users = new Tab("Użytkownicy");
         page2 = new Div();
        page2.setText("Page#2");
        page2.setVisible(false);

         questionares = new Tab("Ankiety");
         page3 = new Div();
        page3.setText("Page#3");
        page3.setVisible(false);

        Map<Tab, Component> tabsToPages = new HashMap<>();
        tabsToPages.put(settings, page1);
        tabsToPages.put(users, page2);
        tabsToPages.put(questionares, page3);
        tabs = new Tabs(settings, users, questionares);
        pages = new Div(page1, page2, page3);

        tabs.addSelectedChangeListener(event -> {
            tabsToPages.values().forEach(page -> page.setVisible(false));
            Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
            selectedPage.setVisible(true);
        });
        tabs.setSizeFull();
        tabs.setFlexGrowForEnclosedTabs(1);












    }

    private void setAdminPage(){
        add(tabs, pages);

        //settings.add(registration);
    }

    private void goLogin(){
        User user = new User();
        user.setAdmin(true);
        usersManager.putMainUser(user);
        UI.getCurrent().getPage().reload();
    }

    private void goRegistration(){
        RegistrationDialog rg = new RegistrationDialog(mainManager);
        rg.open();
    }

    private void goLogout(){
        usersManager.dropMainUser();
        UI.getCurrent().getPage().reload();
    }
}
