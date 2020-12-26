package pl.jaskot.portalfordrivinginstructor.Backend.configuration;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AppSettings {

    private static int  startHour = 8, endHour = 16;
    private static List<Integer> closeHour =closeHour = new ArrayList<>(10);
    private static boolean workingSaturday;
    private static int usersLessonHours = 2;

    public static int getStartHour() { return startHour; }

    public static int getEndHour() { return endHour; }

    public static boolean isWorkingSaturday() { return workingSaturday; }

    public static List<Integer> getCloseHour() { return closeHour; }
}
