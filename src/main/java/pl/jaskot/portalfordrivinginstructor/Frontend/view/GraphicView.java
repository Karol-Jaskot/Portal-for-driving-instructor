package pl.jaskot.portalfordrivinginstructor.Frontend.view;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.jaskot.portalfordrivinginstructor.Backend.MainManager;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.MyDay;
import pl.jaskot.portalfordrivinginstructor.Backend.managers.CalendarManager;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;

public class GraphicView extends VerticalLayout {

    private H1 title, message;
    private Label subTitle;
    private DatePicker valueDatePicker;
    private HorizontalLayout dataLayout;
    private MainManager mainManager;
    private CalendarManager calendarManager;
    private MyDay myDay;
    private LocalDate choiceTime;

    public GraphicView(MainManager mainManager) {
        setSizeFull();
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        addClassName("graphic-view");
        this.mainManager = mainManager;
        calendarManager = mainManager.getCalendarManager();

        createContent();
        add(title, dataLayout,message, subTitle);
    }

    private void createContent() {

        title = new H1("Grafik godzinowy");
        message = new H1("Wiadomość");

        valueDatePicker = new DatePicker();
        LocalDate nowTime = LocalDate.now();
        valueDatePicker.setValue(nowTime);
        subTitle = new Label();

        if(mainManager.isActive()){
            subTitle = new Label("Proszę wybrać datę");
            choiceTime = valueDatePicker.getValue();
            checkDayOfWeek();

            valueDatePicker.addValueChangeListener(event -> {
                choiceTime = valueDatePicker.getValue();
                if (event.getValue() == null) {
                    subTitle.setText("Brak wybranej daty");
                } else {
                    checkDayOfWeek();
                }
            });
        }else {
            subTitle.setText("Grafik dostępny tylko dla dla osób zalogowanych");
        }





        dataLayout = new HorizontalLayout();
        dataLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        dataLayout.add(subTitle, valueDatePicker);
    }

    private void checkDayOfWeek() {
        if (choiceTime.getDayOfWeek() == DayOfWeek.SUNDAY) {
            setDayInfo(1);
        } else if(choiceTime.getDayOfWeek() == DayOfWeek.SATURDAY){
            if (calendarManager.getWorkingSaturday()) {
                setDayInfo(3);
            }else {
                setDayInfo(2);
            }
        }else {
            setDayInfo(3);
        }
    }

    private void setDayInfo(int i) {
        if(i == 1){
            subTitle.setText("Niedziela to dzień wolny od pracy");
        } else if(i==2){
            subTitle.setText("Instruktor ustawił soboty jako dni wolne od pracy");
        } else if(i==3){
            myDay = calendarManager.getMyDay(choiceTime);
            subTitle.setText("Wybrana data: " +myDay.getMyDate()+"  Godzina rozpoczęcia: "+myDay.getStartHour()+"  Godzina zakończenia: "+myDay.getEndHour());
        }
    }

}