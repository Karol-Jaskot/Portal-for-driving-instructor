package pl.jaskot.portalfordrivinginstructor.Frontend.components;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import pl.jaskot.portalfordrivinginstructor.Backend.MainManager;

public class LoginForm extends Dialog {

    private Label title;
    private EmailField email;
    private PasswordField passwordField;
    private Button confirmButton;
    private Button cancelButton;
    private MainManager mainManager;

    public LoginForm(MainManager mainManager) {
        this.mainManager = mainManager;
        setCloseOnEsc(false);
        setCloseOnOutsideClick(false);

        createContent();
        add(title, email, passwordField);
        add(confirmButton,cancelButton);
    }

    private void createContent() {

        title = new Label("Panel logowania");
        email = new EmailField("Email");
        passwordField = new PasswordField("Hasło");


        confirmButton = new Button("Zaloguj", event -> {
            goLogin();
        });
        cancelButton = new Button("Anuluj", event -> {
            close();
        });
    }

    private void goLogin(){
        if(email.isEmpty()){
            MyMessage.pushInfoMessage("Brak emaila");
        } else if(passwordField.isEmpty()){
            MyMessage.pushInfoMessage("Brak hasła");
        }else if(mainManager.getUsersManager().checkUserData(email.getValue(),passwordField.getValue())){
            UI.getCurrent().getPage().reload();
        }else {
            MyMessage.pushInfoMessage("Logowanie nieprawidłowe");
        }
    }
}