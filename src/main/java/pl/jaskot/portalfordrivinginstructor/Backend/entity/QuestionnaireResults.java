package pl.jaskot.portalfordrivinginstructor.Backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class QuestionnaireResults {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long authorId;
    @ElementCollection
    private List<String> Questions;
    @ElementCollection
    private List<String> Answers;

}
