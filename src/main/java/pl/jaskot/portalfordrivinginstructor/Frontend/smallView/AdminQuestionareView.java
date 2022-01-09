package pl.jaskot.portalfordrivinginstructor.Frontend.smallView;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.jaskot.portalfordrivinginstructor.Backend.MainManager;

public class AdminQuestionareView extends VerticalLayout {

    private MainManager mainManager;
    private final H1 title = new H1("Lista ankiet");

    public AdminQuestionareView(MainManager mainManager){
        this.mainManager = mainManager;
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(title);
    }
}