package pl.jaskot.portalfordrivinginstructor.Frontend.view;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.H1;

public class TestView extends VerticalLayout {

    private H1 title;

    public TestView(){
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        addClassName("test-view");

        createElements();
        add(title);
    }

    private void createElements() {
        title = new H1("Testy online");
    }


}
