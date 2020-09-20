package pl.jaskot.portalfordrivinginstructor.Frontend.view;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.time.LocalDate;


public class GraphicView extends VerticalLayout {

    private H1 title;
    private Label subTitle;
    private DatePicker valueDatePicker;
    private HorizontalLayout dataLayout;

    public GraphicView() {
        setSizeFull();
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        addClassName("graphic-view");

        createElements();
        add(title, dataLayout);
    }

    private void createElements() {
        title = new H1("Grafik godzinowy");

        valueDatePicker = new DatePicker();
        LocalDate now = LocalDate.now();
        valueDatePicker.setValue(now);


        subTitle = new Label("Wybrana data: ");
        valueDatePicker.addValueChangeListener(event -> {
            if (event.getValue() == null) {
                subTitle.setText("Brak wybranej daty");
            } else {
                subTitle.setText("Wybrana data: " );
            }
        });

        dataLayout = new HorizontalLayout();
        dataLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        dataLayout.add(subTitle, valueDatePicker);
    }
}