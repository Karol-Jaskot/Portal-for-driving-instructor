package pl.jaskot.portalfordrivinginstructor.Frontend.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ContactView extends VerticalLayout {

    private final String
            title = "Dane kontaktowe",
            author = "Autor aplikacji: Karol Jaskot",
            info = "Niniejsza aplikacja stanowi projekt zrealizowany w ramach pracy inżynierskiej, której celem jest potwierdzenie umiejętności w zakresie tworzenia aplikacji webowych."
                + "Projekt zrealizowany został podczas roku akademickiego 2020/2021 w Państwowej Wyższej Szkołe Zawodowej  im. Witelona w Legnicy.";

    public ContactView() {
        ViewsConfig.setLayoutConfig(this, "contact-view");
        add(
                new H1(title),
                new H2(info),
                new H2(author)
        );
    }
}