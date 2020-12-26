package pl.jaskot.portalfordrivinginstructor.Frontend.view;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.jaskot.portalfordrivinginstructor.Backend.MainManager;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.Lesson;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.MyDay;
import pl.jaskot.portalfordrivinginstructor.Backend.managers.CalendarManager;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class GraphicView extends VerticalLayout {

    private H1 title, message;
    private Label subTitle;
    private DatePicker valueDatePicker;
    private HorizontalLayout dataLayout;
    private MainManager mainManager;
    private CalendarManager calendarManager;
    private MyDay myDay;
    private LocalDate choiceTime;
    private Accordion accordion;

    public GraphicView(MainManager mainManager) {
        setSizeFull();
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        addClassName("graphic-view");
        this.mainManager = mainManager;
        calendarManager = mainManager.getCalendarManager();

        createContent();
        createPage();
    }

    private void createContent() {
        title = new H1("Grafik godzinowy");
        message = new H1();
        subTitle = new Label();
        accordion = new Accordion();
        accordion.setSizeFull();

        valueDatePicker = new DatePicker();
        LocalDate nowTime = LocalDate.now();
        valueDatePicker.setValue(nowTime);
        valueDatePicker.addValueChangeListener(event -> {
            createPage();
        });
        subTitle = new Label();

        dataLayout = new HorizontalLayout();
        dataLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        dataLayout.add(subTitle, valueDatePicker);
    }

    private void createPage(){
        removeAll();
        if(mainManager.isActive()){
            subTitle.setText("Proszę wybrać datę");
            choiceTime = valueDatePicker.getValue();
            checkDayOfWeek();
            add(title,subTitle, dataLayout,message,accordion);
        }else {
            message = new H1("Grafik dostępny tylko dla dla osób zalogowanych");
            add(title,message);
        }
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
            message = new H1("Niedziela to dzień wolny od pracy");
        } else if(i==2){
            message = new H1("Instruktor ustawił soboty jako dni wolne od pracy");
        } else if(i==3){
            setHoursPage();
            subTitle.setText("Wybrana data: " +myDay.getMyDate()+"  Godzina rozpoczęcia: "+myDay.getStartHour()+"  Godzina zakończenia: "+myDay.getEndHour());
        }
    }

    private void setHoursPage(){
        myDay = calendarManager.getMyDay(choiceTime);

        for (Lesson lesson:myDay.getLessons()) {
            String title = "Godzina: "+lesson.getHour()+":00";

            AccordionPanel panel;

            if(lesson.isBlocked()==false){
                if(mainManager.getUserId() == null){
                    // breakHour
                    panel = accordion.add(title+" Przerwa instruktora", new Span("Panel content"));
                    panel.setEnabled(false);
                } else if(lesson.getUserId() == mainManager.getUserId()){
                    // userHour
                    panel = accordion.add(title+" Zarezerwowana przez obecnego kursanta", new Span("Panel content"));
                }else {
                    // otherUserHour
                    panel = accordion.add(title+" Zarezerwowana przez innego kursanta", new Span("Panel content"));
                    panel.setEnabled(false);
                }
            }else {
                // freeHour
                panel = accordion.add(title, new Span("Panel content"));
            }
            panel.addThemeVariants(DetailsVariant.REVERSE);
        }

    }

}