package pl.jaskot.portalfordrivinginstructor.Backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.MyDay;

import java.time.LocalDate;

@Repository
public interface MyDayRepo extends CrudRepository<MyDay, Long> {

    public MyDay findBymyDate(LocalDate date);

}
