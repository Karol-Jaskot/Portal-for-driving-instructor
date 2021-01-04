package pl.jaskot.portalfordrivinginstructor.Backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private boolean isAdmin = false;
    private int lessonLimit;
    private int lessons;
    private boolean examPassed = false;
    @OneToMany
    private List<ExamScore> examScores;

}