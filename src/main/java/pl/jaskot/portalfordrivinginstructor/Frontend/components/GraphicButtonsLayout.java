package pl.jaskot.portalfordrivinginstructor.Frontend.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.jaskot.portalfordrivinginstructor.Backend.MainManager;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.Lesson;

public class GraphicButtonsLayout extends VerticalLayout{

    private MainManager mainManager;
    private Lesson lesson;

    public GraphicButtonsLayout(Lesson lesson, MainManager mainManager){
        this.mainManager = mainManager;
        this.lesson = lesson;
        Button button = new Button();

        if(mainManager.isAdmin()){
            if(lesson.isBlocked()){
                button.setText("Wycofaj przerwę");
                button.addClickListener(event -> setBreakHour(false));

            } else if(lesson.isReserved()){
                button.setText("Wycofaj rezerwację");
                button.addClickListener(event -> setReservationHour(false));
            } else {
                button.setText("Dodaj przerwę");
                button.addClickListener(event -> setBreakHour(true));
            }
        } else {
            if(lesson.isReserved()){
                //user - remove reservation
                button.setText("Wycofaj rezerwacje");
                button.addClickListener(event -> setReservationHour(false));
            }else {
                //user - add reservation
                button.setText("Dodaj rezerwacje");
                button.addClickListener(event -> setReservationHour(true));
            }
        }

        add(button);
    }

    private void setBreakHour(boolean breakHour){
        lesson.setBlocked(breakHour);
        updateLesson();
    }

    private void setReservationHour(boolean reservationHour){
        lesson.setReserved(reservationHour);
        if(reservationHour){
            if(!mainManager.isAdmin()){
                mainManager.getUsersManager().addMainUserReservation();
            }
        }
        else {
            if(mainManager.isAdmin()){
                mainManager.getUsersManager().minusUserReservationLimit(lesson.getUserId());
            } else {
                mainManager.getUsersManager().minusMainUserReservation();
            }
        }
        updateLesson();
    }

    private void updateLesson(){
        mainManager.getCalendarManager().updateLesson(lesson);
    }

}
