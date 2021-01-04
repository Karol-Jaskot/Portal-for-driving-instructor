package pl.jaskot.portalfordrivinginstructor.Frontend.smallView;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import pl.jaskot.portalfordrivinginstructor.Backend.MainManager;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.User;
import com.vaadin.flow.component.html.H1;
import pl.jaskot.portalfordrivinginstructor.Frontend.components.UserDialog;

import java.util.List;

public class UsersListView extends VerticalLayout {

    private MainManager mainManager;
    private List<User> users;
    private Grid<User> userGrid;
    private Button newUser, refresh;

    public UsersListView(MainManager mainManager){
        this.mainManager = mainManager;
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        addClassName("userList-view");

        createContent();
        reloadPage();
    }

    private void createContent() {
        newUser = new Button("Dodaj kursanta", event -> new UserDialog(mainManager).open());
        refresh = new Button("Refresh", event -> reloadPage());
    }

    private void reloadPage(){
        removeAll();
        createGrid();
        add(newUser, refresh);
        add(userGrid);
    }

    private void createGrid(){

        //TODO poprawić na wczytywanie z bazy
        User user = new User();
        user.setFirstName("Stefan");
        user.setLastName("Kornik");
        user.setEmail("dziki@wp.pl");
        user.setPhoneNumber("123");

        User user2 = new User();
        user2.setFirstName("Stefan");
        user2.setLastName("Kornik");
        user2.setEmail("dziki@wp.pl");
        user2.setPhoneNumber("123");

        mainManager.getUsersManager().addUser(user);
        mainManager.getUsersManager().addUser(user2);
        users = mainManager.getUsersManager().getUsers();

        userGrid = new Grid<>();
        userGrid.setItems(users);

        userGrid.removeAllColumns();
        userGrid.addColumn(User::getFirstName).setHeader("Imie")
                .setFooter("Ilość kursantów: " + users.size());
        userGrid.addColumn(User::getLastName).setHeader("Nazwisko");
        userGrid.addColumn(User::getEmail).setHeader("Email");
        userGrid.addColumn(User::getPhoneNumber).setHeader("Telefon");

        userGrid.addComponentColumn(item -> createRemoveButton(userGrid, item))
                .setHeader("Usuwanie");

        userGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
    }

    private Button createRemoveButton(Grid<User> grid, User item) {
        Button button = new Button("Usuń kursanta", clickEvent -> {
            ListDataProvider<User> dataProvider = (ListDataProvider<User>) grid
                    .getDataProvider();
            dataProvider.getItems().remove(item);
            mainManager.getUsersManager().deleteUser(item);
            dataProvider.refreshAll();
        });
        return button;
    }

}
