package pl.jaskot.portalfordrivinginstructor.Frontend.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import org.springframework.util.StopWatch;
import pl.jaskot.portalfordrivinginstructor.Backend.MainManager;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.ExamScore;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.MyTimer;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.Question;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.QuestionList;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class TestView extends VerticalLayout {

    private VerticalLayout examView;
    private QuestionList questionList;
    private List<String> question;
    private List<List<String>> questions;
    private RadioButtonGroup<String> radioGroup;
    private Button startLesson, startExam;
    private Button nextQuestion;
    private Label scoreLabel;
    private String answer;
    private int score;
    private boolean isUser, isExam;
    private MainManager mainManager;
    private int userQuest = 1;
    private int examQuestNumber = 32, examQuestNumberNow = 1;
    private MyTimer myTimer;
    private H2 questionText;

    private H1 title;

    public TestView(MainManager mainManager) throws FileNotFoundException {
        ViewsConfig.setLayoutConfig(this, "test-view");
        this.mainManager = mainManager;

        isUser = mainManager.isActive();
        isExam = true;
        createContent();
        add(title, startLesson , startExam, examView);
    }

    private void createLessonView() {
        cleanPage();
        int random = new Random().nextInt(questions.size() - 1);
        question = questions.get(random);
        questions.remove(random);

        createQuestion();
        scoreLabel.setText("Numer pytania: "+userQuest+"    Wynik: "+score);
        examView.add(questionText,radioGroup,scoreLabel, nextQuestion);
    }

    private void cleanPage(){
        if(isExam){
            startExam.setText("Rozpocznij egzamin od nowa");
        }else {
            startLesson.setText("Rozpocznij tryb nauki od nowa");
        }
        examView.removeAll();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }

    private void createExamView() {
        cleanPage();
        myTimer.start();
        // pobranie pytania
        question = questions.get(examQuestNumberNow);
        examQuestNumberNow++;

        createQuestion();
        scoreLabel.setText("Numer pytania: "+userQuest+"/32    "+myTimer.getValue());
        examView.add(questionText,radioGroup,scoreLabel, nextQuestion);

    }

    private void createQuestion(){
        questionText = new H2(question.get(1));
        radioGroup = new RadioButtonGroup<>();
        radioGroup.setItems(question.get(2), question.get(3), question.get(4));
        radioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        radioGroup.addValueChangeListener(event -> {
            if (event.getValue() != null) {
                answer= event.getValue();
            }
        });
    }

    private void resetSettings() throws FileNotFoundException {
        score = 0;
        examQuestNumber = 32;
        examQuestNumberNow= 1;
        userQuest = 1;
        myTimer.reset();
        if(isExam){
            startLesson.setText("Zacznij tryb nauki");
            questions = questionList.getQuestionsForExam();
        }else {
            startExam.setText("Zacznij egzamin");
            questions = questionList.getQuestions();
        }
    }

    private void createContent() throws FileNotFoundException {
        myTimer = new MyTimer();
        questionList = new QuestionList();
        scoreLabel = new Label("Wynik: -");

        nextQuestion = new Button("Następne pytanie", event -> checkQuestion());

        examView = new VerticalLayout();
        title = new H1("Testy online");

        startLesson = new Button("Zacznij tryb nauki", event -> {
            isExam = false;
            try {
                resetSettings();
            } catch (FileNotFoundException e) {}
            title.setText("Testy online - tryb swobodnej nauki");
            createLessonView();
        }) ;

        startExam = new Button("Zacznij egzamin", event -> {
            isExam = true;
            try {
                resetSettings();
            } catch (FileNotFoundException e) {}
            title.setText("Testy online - egzamin");
            createExamView();
        }) ;
    }

    private void checkQuestion(){
        boolean index = question.get(5).contains("A");
        if(index){
            if(question.get(2) == answer)
                addScore();
            index = false;
        }
        index = question.get(5).contains("B");
        if(index){
            if(question.get(3) == answer)
                addScore();
            index = false;
        }
        index = question.get(5).contains("C");
        if(index){
           if(question.get(4) == answer)
               addScore();
            index = false;
        }

        userQuest++;
        if(isExam){
            myTimer.stop();
            if(examQuestNumberNow>examQuestNumber || myTimer.check()){
                createExamResultView();
            }else {
                createExamView();
            }
        } else {
            createLessonView();
        }
    }

    private void createExamResultView(){
        cleanPage();
        boolean passed = score > 67;
        //TODO duży napis porażka/sukces
        if(passed){
            scoreLabel.setText(" Koniec egzaminu! Zakończony sukcesem!    Wynik: "+score);
        }else {
            scoreLabel.setText(" Koniec egzaminu! Zakończony niepowodzeniem!     Wynik: "+score);
        }
        examView.add(scoreLabel);
        if(mainManager.isActive()){
            ExamScore examScore = new ExamScore();
            examScore.setDate(LocalDateTime.now());
            examScore.setScore(score);
            examScore.setPassed(passed);
            mainManager.getUsersManager().saveExamScore(examScore);
        }
    }

    private void addScore(){
        if(isExam){
            score += Integer.parseInt(question.get(8));
        }else {
            score++;
        }
    }


}
