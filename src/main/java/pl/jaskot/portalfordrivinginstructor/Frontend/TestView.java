package pl.jaskot.portalfordrivinginstructor.Frontend;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.H1;

public class TestView extends VerticalLayout {

    public TestView(){
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        setTitle();

    }

    private void setTitle() {
        add(new H1("Testy online"));
    }


}
