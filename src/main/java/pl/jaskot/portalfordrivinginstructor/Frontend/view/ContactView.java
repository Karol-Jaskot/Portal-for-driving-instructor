package pl.jaskot.portalfordrivinginstructor.Frontend.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ContactView extends VerticalLayout {

    private H1 title;
    private H2 author,info;

    public ContactView() {
        setSizeFull();
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        addClassName("contact-view");

        title = new H1("Dane kontaktowe");
        info = new H2("Niniejsza aplikacja stanowi projekt zrealizowany w ramach pracy inżynierskiej, której celem jest potwierdzenie umiejętności w zakresie tworzenia aplikacji webowych. Projekt zrealizowany został podczas roku akademickiego 2020/2021 w Państwowej Wyższej Szkołe Zawodowej  im. Witelona w Legnicy." );
        author = new H2("Autor aplikacji: Karol Jaskot");
        add(title);

        add(info);
        add(author);
    }
}