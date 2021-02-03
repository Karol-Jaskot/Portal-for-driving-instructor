package pl.jaskot.portalfordrivinginstructor.Backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@Entity
public class QuestionnaireResults {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long authorId;
    private LocalDateTime dateTime;
    @ElementCollection
    private List<String> Questions;
    @ElementCollection
    private List<String> Answers;

    public String getFullDate(){
        return dateTime.format(DateTimeFormatter.ofPattern("HH:mm   dd.MM.yyyy"));
    }
}
