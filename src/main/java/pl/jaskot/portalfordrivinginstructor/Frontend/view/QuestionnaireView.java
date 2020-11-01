package pl.jaskot.portalfordrivinginstructor.Frontend.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.QuestionList;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;

public class QuestionnaireView extends VerticalLayout {

    private H1 title;


    public QuestionnaireView()  {
        setSizeFull();
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        addClassName("questionnaire-view");

        title = new H1("Ankieta");
        add(title);
    }
}