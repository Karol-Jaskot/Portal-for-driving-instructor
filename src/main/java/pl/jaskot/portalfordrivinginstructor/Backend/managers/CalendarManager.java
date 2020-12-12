package pl.jaskot.portalfordrivinginstructor.Backend.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.MyDay;
import pl.jaskot.portalfordrivinginstructor.Backend.repository.MyDayRepo;

import java.time.LocalDate;


@Service
public class CalendarManager {

    private static int  startHour = 0, endHour = 23;
    private static boolean workingSaturday;

    @Autowired
    private MyDayRepo myDayRepo;

    public CalendarManager(MyDayRepo myDayRepo){
        this.myDayRepo = myDayRepo;
    }

    public MyDay getMyDay(LocalDate date){
        MyDay myDay = null;
        try{
            myDay = myDayRepo.findBymyDate(date);
        }catch (Exception e){}
        if (myDay == null){
            myDay = createNewDay(date);
        }
        return myDay;
    }

    private MyDay createNewDay(LocalDate date) {
        MyDay myDay = new MyDay();
        myDay.setStartHour(startHour);
        myDay.setEndHour(endHour);
        myDay.setMyDate(date);
        myDayRepo.save(myDay);
        return myDay;
    }

    public void updateMyDay(MyDay myDay){
        myDayRepo.save(myDay);
    }

    public boolean getWorkingSaturday(){return workingSaturday;}

}
