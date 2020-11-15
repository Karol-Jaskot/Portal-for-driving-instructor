package pl.jaskot.portalfordrivinginstructor.Frontend.view;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.jaskot.portalfordrivinginstructor.Backend.MainManager;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.Day;
import pl.jaskot.portalfordrivinginstructor.Backend.managers.DaysManager;

import java.time.LocalDate;

public class GraphicView extends VerticalLayout {

    private H1 title, message;
    private Label subTitle;
    private DatePicker valueDatePicker;
    private HorizontalLayout dataLayout;

    private DaysManager daysManager;
    private Day choiceDay;

    public GraphicView(MainManager mainManager) {
        this.daysManager = mainManager.getDaysManager();

        setSizeFull();
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        addClassName("graphic-view");

        createContent();
        add(title, dataLayout,message);
    }

    private void createContent() {

        title = new H1("Grafik godzinowy");
        message = new H1("Wiadomość");

        valueDatePicker = new DatePicker();

        LocalDate nowTime = LocalDate.now();

        valueDatePicker.setValue(nowTime);


        subTitle = new Label("Wybrana data: ");
        valueDatePicker.addValueChangeListener(event -> {
            if (event.getValue() == null) {
                subTitle.setText("Brak wybranej daty");
            } else {
                subTitle.setText("Wybrana data: " );

                LocalDate choiceTime = valueDatePicker.getValue();
                int year = choiceTime.getYear();
                int month = choiceTime.getMonthValue();
                int day = choiceTime.getDayOfMonth();
                choiceDay = daysManager.getDayByDate(year,month,day);

                message.setText("Wybrany dzień: "+choiceDay.getDate() +"Start pracy: "+choiceDay.getMinHour()+" Koniec pracy: "+choiceDay.getMaxHour());
            }
        });

        dataLayout = new HorizontalLayout();
        dataLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        dataLayout.add(subTitle, valueDatePicker);
    }


}