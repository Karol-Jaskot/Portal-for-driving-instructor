package pl.jaskot.portalfordrivinginstructor.Backend.configuration;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Data
public class AppSettings {

    @Getter
    private static int reservationLimit = 4;
    @Getter
    private static int  startHour = 8, endHour = 16;
    @Getter
    private static List<Integer> closeHour = new ArrayList<Integer>() {{
        add(10);
    }};
    @Getter
    private static boolean workingSaturday;
    private static int usersLessonHours = 2;

    public static boolean checkCloseHour(int i){
        return closeHour.contains(i);
    }

}
