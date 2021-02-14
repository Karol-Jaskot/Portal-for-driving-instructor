package pl.jaskot.portalfordrivinginstructor.Frontend.view;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
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
import pl.jaskot.portalfordrivinginstructor.Frontend.components.GraphicButtonsLayout;

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
    private Button freeDayButton;
    private Button refreshButton;
    private boolean buttonsFlag;

    public GraphicView(MainManager mainManager) {
        setSizeFull();
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        addClassName("graphic-view");
        this.mainManager = mainManager;
        calendarManager = mainManager.getCalendarManager();

        buttonsFlag = false;
        createContent();
        createPage();
    }

    private void createContent() {
        title = new H1("Grafik godzinowy");
        message = new H1();
        subTitle = new Label();
        accordion = new Accordion();
        refreshButton = new Button("Odśwież grafik",event -> createPage());
        freeDayButton = new Button("Ustaw cały dzień wolny", event -> setFreeDay());

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
            if(buttonsFlag){
                add(title,subTitle, dataLayout,message,accordion, refreshButton);
                if(mainManager.isAdmin()){
                    add(freeDayButton);
                }
            } else {
                add(title,subTitle, dataLayout,message,accordion);
            }

        }else {
            message = new H1("Grafik dostępny tylko dla dla osób zalogowanych");
            add(title,message);
        }
    }

    private void checkDayOfWeek() {
        LocalDate lt = LocalDate.now();
        if(mainManager.isAdmin()){
            if (choiceTime.getDayOfWeek() == DayOfWeek.SUNDAY) {
                buttonsFlag = false;
                setDayInfo(1);
            } else if(choiceTime.getDayOfWeek() == DayOfWeek.SATURDAY){
                if (calendarManager.getWorkingSaturday()) {
                    buttonsFlag = true;
                    setDayInfo(3);
                }else {
                    buttonsFlag = false;
                    setDayInfo(2);
                }
            }else {
                buttonsFlag = true;
                setDayInfo(3);
            }
        }else {
            if (choiceTime.getDayOfWeek() == DayOfWeek.SUNDAY) {
                buttonsFlag = false;
                setDayInfo(1);
            } else if(choiceTime.getDayOfWeek() == DayOfWeek.SATURDAY){
                if (calendarManager.getWorkingSaturday()) {
                    buttonsFlag = true;
                    setDayInfo(3);
                }else {
                    buttonsFlag = false;
                    setDayInfo(2);
                }
            }else if(choiceTime.isBefore(lt) ){
                buttonsFlag = false;
                setDayInfo(0);
            } else if(choiceTime.isAfter(lt.plusDays(14))) {
                buttonsFlag = false;
                setDayInfo(0);
            }
            else{
                    buttonsFlag = true;
                    setDayInfo(3);
                }

        }

    }

    private void setDayInfo(int i) {
        myDay = calendarManager.getMyDay(choiceTime);
        accordion = new Accordion();

        if(i == 0){
            message = new H1("Grafik niedostępny");
        } else if(i == 1){
            message = new H1("Niedziela to dzień wolny od pracy");
        } else if(i==2){
            if(mainManager.isAdmin()){
                message = new H1("Soboty zostały ustawione jako dni wolne od pracy, przejdź do ustawień aby to zmienić");
            } else {
                message = new H1("Instruktor ustawił soboty jako dni wolne od pracy");
            }
        } else if(i==3){
            subTitle.setText("Wybrana data: " +myDay.getMyDate());
            message = new H1();
            setHoursPage();
        }
    }

    private void setHoursPage(){
        for (Lesson lesson:myDay.getLessons()) {
            String title = "Godzina: "+lesson.getHour()+":00";

            AccordionPanel panel;

            if(lesson.isBlocked()){
                // breakHour
                panel = accordion.add(title+" - Przerwa instruktora",new GraphicButtonsLayout(lesson,mainManager));
                if(!mainManager.isAdmin()){
                    panel.setEnabled(false);
                }
            }else if(lesson.isReserved()){
                panel = accordion.add(title+" - Zarezerwowana", new GraphicButtonsLayout(lesson,mainManager));
                if(!mainManager.isAdmin()){
                    panel.setEnabled(false);
                }
            } else {
                // freeHour
                panel = accordion.add(title, new GraphicButtonsLayout(lesson,mainManager));
            }
            panel.addThemeVariants(DetailsVariant.REVERSE);
        }

    }

    private void setFreeDay(){
        for (Lesson lesson: myDay.getLessons()) {
            if(lesson.isReserved()){
                mainManager.getUsersManager().minusUserReservationLimit(lesson.getUserId());
            }
            lesson.setBlocked(true);
            calendarManager.updateLesson(lesson);
        }
        createPage();
    }

}