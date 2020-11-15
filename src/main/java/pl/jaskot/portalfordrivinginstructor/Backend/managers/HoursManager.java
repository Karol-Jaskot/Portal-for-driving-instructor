package pl.jaskot.portalfordrivinginstructor.Backend.managers;

import org.springframework.stereotype.Service;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.Hour;
import pl.jaskot.portalfordrivinginstructor.Backend.repository.HoursRepo;

import java.util.List;

@Service
public class HoursManager {

    private HoursRepo hoursRepo;

    public HoursManager(HoursRepo hoursRepo){
        this.hoursRepo = hoursRepo;
    }

    public void addHour(Hour hour){
        hoursRepo.save(hour);
    }

    public void removeHour(Hour hour){
        hoursRepo.delete(hour);
    }

    public List<Hour> getHours(){
        return (List<Hour>) hoursRepo.findAll();
    }



}
