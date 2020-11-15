package pl.jaskot.portalfordrivinginstructor.Backend.managers;

import org.springframework.stereotype.Service;
import pl.jaskot.portalfordrivinginstructor.Backend.configuration.CalendarSettings;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.Day;
import pl.jaskot.portalfordrivinginstructor.Backend.repository.DaysRepo;

import java.util.Date;
import java.util.List;

@Service
public class DaysManager {

    private DaysRepo daysRepo;
    public HoursManager hoursManager;

    public DaysManager(DaysRepo daysRepo){
        this.daysRepo = daysRepo;
    }

    public void addDay(Day day){
        daysRepo.save(day);
    }

    public void removeDay(Day day){
        daysRepo.delete(day);
    }

    public List<Day> getDays(){
        return (List<Day>) daysRepo.findAll();
    }

    public Day getDayByDate(int year, int month, int day){
        List<Day> days = (List<Day>) daysRepo.findAll();
        Day newDay = null;
        for (Day nextDay: days) {
            if(nextDay.getYear() == year){
                if(nextDay.getMonth() == month){
                    if(nextDay.getDay() == day){
                        newDay = nextDay;
                    }
                }
            }
        }
        if(newDay == null){
            newDay = new Day();
            newDay.setDate(year,month,day);
            newDay.setMinHour(CalendarSettings.minHour);
            newDay.setMaxHour(CalendarSettings.maxHour);
            addDay(newDay);
        }
        return newDay;
    }

}
