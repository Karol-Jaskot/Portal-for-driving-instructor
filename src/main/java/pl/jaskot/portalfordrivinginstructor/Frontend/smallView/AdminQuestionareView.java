package pl.jaskot.portalfordrivinginstructor.Frontend.smallView;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.jaskot.portalfordrivinginstructor.Backend.MainManager;

public class AdminQuestionareView extends VerticalLayout {

    private MainManager mainManager;
    private H1 title;

    public AdminQuestionareView(MainManager mainManager){
        this.mainManager = mainManager;
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        createContent();
        add(title);
    }

    private void createContent() {
        title = new H1("Lista ankiet");
    }

}
