package pl.jaskot.portalfordrivinginstructor.Backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Hour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int Hour;
    private Date date;
    @OneToOne
    private User user;
    private boolean blocked;
    private boolean breakTime;


}
