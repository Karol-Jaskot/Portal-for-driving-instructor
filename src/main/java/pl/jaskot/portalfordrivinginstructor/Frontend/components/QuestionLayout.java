package pl.jaskot.portalfordrivinginstructor.Frontend.components;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.Question;

public class QuestionLayout extends VerticalLayout{

    private Question question;
    RadioButtonGroup<String> radioGroup;
    private H2 title;
    private boolean score = false;

    public QuestionLayout(Question question){
        this.question = question;
        title = new H2(question.getText());

        radioGroup = new RadioButtonGroup<>();
        radioGroup.setLabel("Odpowiedzi:");
        radioGroup.setItems(question.getAnswerA(), question.getAnswerB(), question.getAnswerC(), question.getAnswerD());

        add(title,radioGroup);
    }

    public boolean getScore(){
        radioGroup.addValueChangeListener(event -> {
            if(event.getValue().equals(question.getCorrectAnswer()))  {
                score = true;
            }
            else {
                score = false;
            }
        });
        return score;
    }

}
