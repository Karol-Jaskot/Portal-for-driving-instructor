package pl.jaskot.portalfordrivinginstructor.Frontend.view;

import com.vaadin.flow.component.button.Button;
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
    private List<List<String>> questions;
    private Button startExam;

    private H1 title;

    public TestView() throws FileNotFoundException {
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        addClassName("test-view");


        questionList = new QuestionList();
        questions = questionList.getQuestions();

        examView = new VerticalLayout();
        startExam = new Button("Zacznij egzamin", event -> {
            createExamView();
        }) ;

        title = new H1("Egzamin");
        add(title,startExam, examView);


    }

    private void createExamView() {
        startExam.setText("Zacznij od nowa");
        examView.removeAll();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        int random = new Random().nextInt(questions.size() - 1);
        List<String> question = questions.get(random);

        H2 questionText = new H2(question.get(1));

        RadioButtonGroup<String> radioGroup = new RadioButtonGroup<>();
        radioGroup.setItems(question.get(2), question.get(3), question.get(4));
        radioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);

        examView.add(questionText,radioGroup);
    }

}
