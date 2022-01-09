package pl.jaskot.portalfordrivinginstructor.Frontend.smallView;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.jaskot.portalfordrivinginstructor.Backend.MainManager;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.Lesson;
import pl.jaskot.portalfordrivinginstructor.Backend.managers.CalendarManager;

public class LessonsView extends VerticalLayout {

    private MainManager mainManager;
    private CalendarManager calendarManager;
    private Lesson lesson;
    private Checkbox checkbox;

    public LessonsView(MainManager mainManager, Lesson lesson) {
        this.mainManager = mainManager;
        this.calendarManager = mainManager.getCalendarManager();
        this.lesson = lesson;
        createContent();
    }

    private void createContent() {
        checkbox = new Checkbox();

        if (lesson.isBlocked() == false) {
            // free hour
            checkbox.setLabel("Rezerwacja danej godziny");
        } else {
            // close hour
        }
        add(checkbox);
    }
}