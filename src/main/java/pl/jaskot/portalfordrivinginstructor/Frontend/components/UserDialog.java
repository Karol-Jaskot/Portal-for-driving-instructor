package pl.jaskot.portalfordrivinginstructor.Frontend.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import pl.jaskot.portalfordrivinginstructor.Backend.MainManager;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.User;

public class UserDialog extends Dialog {

    private MainManager mainManager;
    private TextField firstName, lastName, phone;
    private EmailField email;
    private PasswordField passwordField;
    private Button confirmButton;
    private Button cancelButton;

    public UserDialog(MainManager mainManager){
        this.mainManager = mainManager;
        setCloseOnEsc(false);
        setCloseOnOutsideClick(false);

        createContent();

        add(firstName, lastName, phone, email, passwordField);
        add(confirmButton,cancelButton);
    }

    private void createContent() {

        firstName = new TextField("Imie");
        lastName = new TextField("Nazwisko");
        phone = new TextField("Telefon");
        email = new EmailField("Email");
        passwordField = new PasswordField("Hasło");


        confirmButton = new Button("Zapisz", event -> {
            createUser();
            close();
        });
        cancelButton = new Button("Anuluj", event -> {
            close();
        });
    }

    private void createUser() {
        User user = new User();
        user.setFirstName(firstName.getValue());
        user.setLastName(lastName.getValue());
        user.setEmail(email.getValue());
        user.setPassword(passwordField.getValue());
        user.setPhoneNumber(phone.getValue());
        if(!mainManager.getUsersManager().addUser(user)){
            MyMessage.pushInfoMessage("Podany email już istnieje w bazie");
        }
    }

}
