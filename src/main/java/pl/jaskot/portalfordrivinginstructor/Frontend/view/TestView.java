package pl.jaskot.portalfordrivinginstructor.Frontend.view;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.H1;

public class TestView extends VerticalLayout {

    public TestView(){
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        addClassName("test-view");

        setTitle();

    }

    private void setTitle() {
        add(new H1("Testy online"));
    }



}
