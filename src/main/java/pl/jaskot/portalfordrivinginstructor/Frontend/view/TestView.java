package pl.jaskot.portalfordrivinginstructor.Frontend.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.QuestionList;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;

public class TestView extends VerticalLayout {

    private VerticalLayout examView;
    private QuestionList questionList;
    private List<String> question;
    private List<List<String>> questions;
    private RadioButtonGroup<String> radioGroup;
    private Button startExam;
    private Button nextQuestion;
    private Label scoreLabel;
    private String answer;
    private int score;

    private H1 title;

    public TestView() throws FileNotFoundException {
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        addClassName("test-view");

        createContent();
        add(title,startExam, examView);
    }

    private void createExamView() {
        startExam.setText("Zacznij od nowa");
        examView.removeAll();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        int random = new Random().nextInt(questions.size() - 1);
        question = questions.get(random);

        H2 questionText = new H2(question.get(1));

        radioGroup = new RadioButtonGroup<>();
        radioGroup.setItems(question.get(2), question.get(3), question.get(4));
        radioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        radioGroup.addValueChangeListener(event -> {
            if (event.getValue() != null) {
                answer= event.getValue();
            }
        });

        scoreLabel.setText("Wynik: "+score);
        examView.add(questionText,radioGroup,scoreLabel, nextQuestion);
    }

    private void createContent() throws FileNotFoundException {
        questionList = new QuestionList();
        questions = questionList.getQuestions();
        scoreLabel = new Label("Wynik: -");

        nextQuestion = new Button("Następne pytanie", event -> checkQuestion());

        examView = new VerticalLayout();
        startExam = new Button("Zacznij egzamin", event -> {
            score = 0;
            createExamView();
        }) ;


        title = new H1("Egzamin");
    }

    private void checkQuestion(){
        boolean index = question.get(5).contains("A");
        if(index){
            if(question.get(2) == answer)
                score++;
            index = false;
        }
        index = question.get(5).contains("B");
        if(index){
            if(question.get(3) == answer)
                score++;
            index = false;
        }
        index = question.get(5).contains("C");
        if(index){
           if(question.get(4) == answer)
                score++;
            index = false;
        }

        createExamView();
    }

}
