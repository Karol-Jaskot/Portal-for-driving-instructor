package pl.jaskot.portalfordrivinginstructor.Backend.entity;

import lombok.Data;

@Data
public class Question {

    private String text;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String corretAnswer;

}
