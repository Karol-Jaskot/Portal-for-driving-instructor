package pl.jaskot.portalfordrivinginstructor.Backend.entity;

import lombok.Data;


import javax.persistence.*;

@Data
@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int hour;
    private long userId;
    private boolean isBlocked;

}
