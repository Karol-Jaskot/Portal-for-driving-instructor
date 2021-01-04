package pl.jaskot.portalfordrivinginstructor.Frontend.components;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import pl.jaskot.portalfordrivinginstructor.Backend.MainManager;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.Article;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.User;
import pl.jaskot.portalfordrivinginstructor.Backend.managers.ArticlesManager;

public class RegistrationDialog extends Dialog {


    private MainManager mainManager;

    private Button confirmButton;
    private Button cancelButton;
    private TextField firstName;
    private TextField lastName;
    private TextField phoneNumber;
    private EmailField emailField;
    private PasswordField passwordField;
    private String author;


    public RegistrationDialog (MainManager mainManager){
        this.mainManager = mainManager;
        setCloseOnEsc(false);
        setCloseOnOutsideClick(false);

        createContent();
        add(firstName,lastName,phoneNumber, emailField, passwordField);
        add(confirmButton,cancelButton);
    }

    private void createContent() {
        author = "Admin";

        firstName = new TextField("Imie");
        lastName = new TextField("Nazwisko");
        phoneNumber = new TextField("Numer telefonu");

        emailField = new EmailField("Email");
        emailField.setErrorMessage("Niepoprawny adres email");

        passwordField = new PasswordField();
        passwordField.setLabel("Hasło");

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

        user.setAdmin(false);
        user.setFirstName(firstName.getValue());
        user.setLastName(lastName.getValue());
        user.setPhoneNumber(phoneNumber.getValue());
        user.setEmail(emailField.getValue());
        user.setPassword(passwordField.getValue());
        if(!mainManager.getUsersManager().addUser(user)){
            MyMessage.pushInfoMessage("Podany email już istnieje w bazie");
        }
    }

}
