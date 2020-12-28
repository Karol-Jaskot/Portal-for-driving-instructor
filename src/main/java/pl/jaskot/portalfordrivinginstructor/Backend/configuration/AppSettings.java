package pl.jaskot.portalfordrivinginstructor.Backend.configuration;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AppSettings {

    private static int reservationLimit = 4;
    private static int  startHour = 8, endHour = 16;
    private static List<Integer> closeHour = new ArrayList<Integer>() {{
        add(10);
    }};
    private static boolean workingSaturday;
    private static int usersLessonHours = 2;

    public static int getStartHour() { return startHour; }

    public static int getEndHour() { return endHour; }

    public static boolean isWorkingSaturday() { return workingSaturday; }

    public static List<Integer> getCloseHour() { return closeHour; }

    public static boolean checkCloseHour(int i){
        return closeHour.contains(i);
    }

    public static int getReservationLimit(){return reservationLimit;}
}
