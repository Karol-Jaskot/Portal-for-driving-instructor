package pl.jaskot.portalfordrivinginstructor.Frontend.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.jaskot.portalfordrivinginstructor.Backend.MainManager;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.QuestionList;
import pl.jaskot.portalfordrivinginstructor.Frontend.smallView.QuestionnaireForEmptyUserView;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;

public class QuestionnaireView extends VerticalLayout {

    private H1 title;
    private MainManager mainManager;

    public QuestionnaireView(MainManager mainManager)  {
        this.mainManager = mainManager;
        setSizeFull();
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        addClassName("questionnaire-view");

        title = new H1("Ankieta");
        add(title);

        if (!mainManager.isActive()){
            setQuestionnaireForEmptyUser();
        }else {
            setQuestionnaireForUser();
        }
    }

    private void setQuestionnaireForUser() {

    }

    private void setQuestionnaireForEmptyUser() {
        add(new QuestionnaireForEmptyUserView(mainManager));
    }
}