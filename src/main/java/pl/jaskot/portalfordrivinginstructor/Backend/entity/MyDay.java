package pl.jaskot.portalfordrivinginstructor.Backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class MyDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate myDate;
    private int startHour, endHour;
    @ElementCollection
    private List<Integer> closeHour;
    @OneToMany
    private List<Lesson> lessons;

}
