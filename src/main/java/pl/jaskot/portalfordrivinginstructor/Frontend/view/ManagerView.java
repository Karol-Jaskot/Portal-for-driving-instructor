package pl.jaskot.portalfordrivinginstructor.Frontend.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
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
import pl.jaskot.portalfordrivinginstructor.Frontend.components.LoginForm;
import pl.jaskot.portalfordrivinginstructor.Frontend.components.NextPasswordDialog;
import pl.jaskot.portalfordrivinginstructor.Frontend.components.RegistrationDialog;
import pl.jaskot.portalfordrivinginstructor.Frontend.smallView.*;

import java.util.HashMap;
import java.util.Map;


public class ManagerView extends VerticalLayout {

    private H1 title;
    private MainManager mainManager;
    private UsersManager usersManager;
    private Button login, logout, loginAminForTest;
    private Button registration;
    private Tab settings, users, questionares;
    private Tabs tabs;
    private Div page1, page2, page3, pages;

    public ManagerView(MainManager mainManager) {
        this.mainManager = mainManager;
        this.usersManager = mainManager.getUsersManager();
        ViewsConfig.setLayoutConfig(this, "manager-view");
        createContent();
        add(title);
        if(mainManager.isActive()){
            if(mainManager.isAdmin()){
                createAdminContent();
                setAdminPage();
            }else {
                add(new UserSettingView(mainManager));
            }
            add(logout);
        }else {
            add(login);
            //add(loginAminForTest);
        }
    }

    private void createContent() {
        title = new H1("Zarządzanie aplikacją");
        loginAminForTest = new Button("Zaloguj jako admin", new Icon(VaadinIcon.ACADEMY_CAP), event -> goLoginAdminForTest());
        login = new Button("Zaloguj", new Icon(VaadinIcon.KEY), event -> goLogin());
        logout = new Button("Wyloguj", event -> goLogout());
        registration = new Button("Dodaj użytkownika",event -> goRegistration());
    }

    private void createAdminContent(){

        settings = new Tab("Ustawienia");
        page1 = new Div();
        page1.add(new AdminSettingsView(mainManager));

         users = new Tab("Użytkownicy");
         page2 = new Div();
        page2.add(new UsersListView(mainManager));
        page2.setSizeFull();
        page2.setVisible(false);

         questionares = new Tab("Ankiety");
         page3 = new Div();
        page3.add(new QResultForAdmin(mainManager));
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
    }

    private void setAdminPage(){
        add(tabs, pages);
        tabs.setSizeFull();
        pages.setSizeFull();
        //settings.add(registration);
    }

    private void goLogin(){
        LoginForm loginForm = new LoginForm(mainManager);
        loginForm.open();
    }

    private void goRegistration(){
        RegistrationDialog rg = new RegistrationDialog(mainManager);
        rg.open();
    }

    private void goLogout(){
        usersManager.dropMainUser();
        UI.getCurrent().getPage().reload();
    }

    private void goLoginAdminForTest(){
        User user = new User();
        user.setAdmin(true);
        usersManager.putMainUser(user);
        UI.getCurrent().getPage().reload();
    }
}