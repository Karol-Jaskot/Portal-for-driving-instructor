package pl.jaskot.portalfordrivinginstructor.Backend.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jaskot.portalfordrivinginstructor.Backend.configuration.AppSettings;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.Lesson;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.MyDay;
import pl.jaskot.portalfordrivinginstructor.Backend.repository.LessonRepo;
import pl.jaskot.portalfordrivinginstructor.Backend.repository.MyDayRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class CalendarManager {

    @Autowired
    private MyDayRepo myDayRepo;

    @Autowired
    private LessonRepo lessonRepo;

    public CalendarManager(MyDayRepo myDayRepo, LessonRepo lessonRepo){
        this.myDayRepo = myDayRepo;
        this.lessonRepo = lessonRepo;
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
        myDay.setStartHour(AppSettings.getStartHour());
        myDay.setEndHour(AppSettings.getEndHour());
        myDay.setMyDate(date);
        myDay.setCloseHour(AppSettings.getCloseHour());

        List<Lesson> lessons = new ArrayList<>();
        for(int i = AppSettings.getStartHour(); i<AppSettings.getEndHour();i++){
            Lesson lesson = new Lesson();
            lesson.setHour(i);
            boolean blocked = false;
            if (AppSettings.checkCloseHour(i)){
                blocked = true;
            }
            lesson.setBlocked(blocked);
            lesson.setReserved(false);
            lessonRepo.save(lesson);
            lessons.add(lesson);
        }
        myDay.setLessons(lessons);
        myDayRepo.save(myDay);
        return myDay;
    }

    public void updateMyDay(MyDay myDay){
        myDayRepo.save(myDay);
    }

    public void updateLesson(Lesson lesson){lessonRepo.save(lesson);}

    public boolean getWorkingSaturday(){return AppSettings.isWorkingSaturday();}

}
