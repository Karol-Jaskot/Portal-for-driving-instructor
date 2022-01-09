package pl.jaskot.portalfordrivinginstructor.Backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder @NoArgsConstructor @AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private boolean isFirstLogin;
    private boolean isAdmin = false;
    private int lessonsLimit;
    private int lessons;
    private boolean isExamPassed = false;
    private boolean isFirstExam = true;
    @OneToMany
    private List<ExamScore> examScores;

}