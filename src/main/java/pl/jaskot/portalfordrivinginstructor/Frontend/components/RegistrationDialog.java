package pl.jaskot.portalfordrivinginstructor.Frontend.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import pl.jaskot.portalfordrivinginstructor.Backend.MainManager;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.User;

public class RegistrationDialog extends Dialog {

    private MainManager mainManager;

    private TextField firstName, lastName, phoneNumber;
    private EmailField emailField;
    private PasswordField passwordField;

    public RegistrationDialog(MainManager mainManager) {
        this.mainManager = mainManager;
        setCloseOnEsc(false);
        setCloseOnOutsideClick(false);
        createContent();
    }

    private void createContent() {
        firstName = new TextField("Imie");
        lastName = new TextField("Nazwisko");
        phoneNumber = new TextField("Numer telefonu");

        emailField = new EmailField("Email");
        emailField.setErrorMessage("Niepoprawny adres email");

        passwordField = new PasswordField("Hasło");

        add(firstName, lastName, phoneNumber, emailField, passwordField,
                new Button("Zapisz", event -> {
                    createUser();
                    close();
                }), new Button("Anuluj", event -> {
                    close();
                }));

    }

    private void createUser() {
        if (!mainManager.getUsersManager().addUser(User.builder()
                .isAdmin(false)
                .firstName(firstName.getValue())
                .lastName(lastName.getValue())
                .phoneNumber(phoneNumber.getValue())
                .email(emailField.getValue())
                .password(passwordField.getValue())
                .build())) {
            MyMessage.pushInfoMessage("Podany email już istnieje w bazie");
        }
    }

}
