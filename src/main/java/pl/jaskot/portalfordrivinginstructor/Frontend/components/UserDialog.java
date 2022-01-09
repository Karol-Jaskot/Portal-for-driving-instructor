package pl.jaskot.portalfordrivinginstructor.Frontend.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import lombok.extern.java.Log;
import pl.jaskot.portalfordrivinginstructor.Backend.MainManager;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.User;

@Log
public class UserDialog extends Dialog {

    private MainManager mainManager;
    private TextField firstName, lastName, phone;
    private EmailField email;
    private PasswordField passwordField;

    public UserDialog(MainManager mainManager){
        this.mainManager = mainManager;
        setCloseOnEsc(false);
        setCloseOnOutsideClick(false);
        createContent();
    }

    private void createContent() {
        firstName = new TextField("Imie");
        lastName = new TextField("Nazwisko");
        phone = new TextField("Telefon");
        email = new EmailField("Email");
        passwordField = new PasswordField("Hasło");
        add(firstName, lastName, phone, email, passwordField,
                new Button("Zapisz", event -> {
                    createUser();
                    close();
                }),
                new Button("Anuluj", event -> {
                    close();
                }));
    }

    private void createUser() {
        try{
            if(!mainManager.getUsersManager().addUser(
                    User.builder()
                            .firstName(firstName.getValue())
                            .lastName(lastName.getValue())
                            .email(email.getValue())
                            .password(passwordField.getValue())
                            .phoneNumber(phone.getValue())
                            .build()
            )){
                MyMessage.pushInfoMessage("Podany email już istnieje w bazie");
            }
        }catch (Exception e){
            log.warning(e.toString());
        }
    }
}