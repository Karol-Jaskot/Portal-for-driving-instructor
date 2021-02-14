package pl.jaskot.portalfordrivinginstructor.Frontend.smallView;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.jaskot.portalfordrivinginstructor.Backend.MainManager;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.User;

public class UserSettingView extends VerticalLayout {

    private MainManager mainManager;
    private User user;

    public UserSettingView(MainManager mainManager){
        this.mainManager = mainManager;
        this.user = mainManager.getUsersManager().getMainUser();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(new H2("Dane personalne:"));

        add(new Label("Imię: "+user.getFirstName()+" "+user.getLastName()));
        add(new Label("Email: "+user.getEmail()));
        add(new Label("Numer telefonu: "+user.getPhoneNumber()));

        if(user.getExamScores().isEmpty()){
            add(new Label("Brak ukończonych egzaminów!"));
        }else {
            add(new Label("Ilość ukończonych egzaminów: "+user.getExamScores().size()));
        }

        if(user.isExamPassed()){
            add(new Label("Egzamin teoretyczny ukończony sukcesem!"));
        }else {
            add(new Label("Egzamin teoretyczny ukończony niepowodzeniem!"));
        }
    }
}
