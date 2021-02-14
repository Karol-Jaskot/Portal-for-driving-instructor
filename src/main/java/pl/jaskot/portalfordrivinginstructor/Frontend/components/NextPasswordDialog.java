package pl.jaskot.portalfordrivinginstructor.Frontend.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import pl.jaskot.portalfordrivinginstructor.Backend.MainManager;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.User;
import pl.jaskot.portalfordrivinginstructor.Backend.managers.UsersManager;

public class NextPasswordDialog extends Dialog {

    private UsersManager usersManager;
    private Label title;
    private Button confirmButton;
    private PasswordField oldPass, newPassOne, newPassTwo;

    public NextPasswordDialog(MainManager mainManager){
        this.usersManager = mainManager.getUsersManager();
        setCloseOnEsc(false);
        setCloseOnOutsideClick(false);
        createContent();
        createPageView();
    }

    private void createPageView() {
        VerticalLayout layout = new VerticalLayout();
        layout.add(title,oldPass,newPassOne,newPassTwo,confirmButton);
        add(layout);
    }

    private void createContent() {
        title = new Label("Aktualizacja hasła");
        oldPass = new PasswordField("Obecne hasło:");
        newPassOne = new PasswordField("Podaj nowe hasło:");
        newPassTwo = new PasswordField("Powtórz hasło:");

        confirmButton = new Button("Zapisz", event -> {
            if(savePassword()){
                User user = usersManager.getMainUser();
                user.setPassword(newPassOne.getValue());
                user.setFirstLogin(false);
                usersManager.updateUser(user);
                close();
            }
        });
    }

    private boolean savePassword() {
        if(oldPass.isEmpty()||oldPass.equals(usersManager.getMainUser().getPassword())){
            MyMessage.pushInfoMessage("Poprzednie hasło jest nieprawidłowe!");
            return false;
        }else {
            if(newPassOne.getValue().isEmpty()){
                MyMessage.pushInfoMessage("Należy podać prawidłowe hasło!");
                return false;
            }else {
                if(newPassOne.getValue().equals(newPassTwo.getValue())){
                    MyMessage.pushInfoMessage("Hasło zmienione!");
                    return true;
                } else {
                    MyMessage.pushInfoMessage("Niepoprawnie powtórzone hasło!");
                    return false;
                }
            }
        }
    }

}
