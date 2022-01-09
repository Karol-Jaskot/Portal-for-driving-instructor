package pl.jaskot.portalfordrivinginstructor.Frontend.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import pl.jaskot.portalfordrivinginstructor.Backend.MainManager;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.QuestionnaireResults;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.QuestionsForAll;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class QuestionnaireView extends VerticalLayout {

    private H1 title;
    private MainManager mainManager;
    private Button nextButton;
    private RadioButtonGroup<String> radioGroup;
    private H2 questionText;
    private QuestionnaireResults questionnaireResults;

    private int questNumber = 0;
    private int lastNumber = 0;
    private List<String> questionsList;
    private List<List<String>> answersList;
    private List<String> userAnswers;
    private List<String> answers;

    public QuestionnaireView(MainManager mainManager){
        this.mainManager = mainManager;
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSizeFull();
        addClassName("questionnaire-view");
        createContent();

        if(mainManager.isAdmin()){
            //admin
            questionText.setText("Wyniki ankiet dostępne są w zakładce ustawienia");
        }else {
            //niezalogowany
            QuestionsForAll questionsForAll = new QuestionsForAll();
            questionsList = questionsForAll.getQuestionsList();
            answersList = questionsForAll.getAnswersList();
            userAnswers = new ArrayList<>();
            lastNumber = questionsList.size();
        }
        createQuest();
    }

    private void createContent() {
        answers = new ArrayList<>();
        title = new H1("Ankieta");
        questionText = new H2();
        nextButton = new Button("Dalej", event -> nextQuestion());
        radioGroup = new RadioButtonGroup<>();
        questionnaireResults = new QuestionnaireResults();
    }

    private void createQuest() {
        try{
            removeAll();
            add(title);
            if(mainManager.isAdmin()){
                add(questionText);
                return;
            }
            if(questNumber < lastNumber){
                questionText.setText(questionsList.get(questNumber));
                radioGroup.setItems(answersList.get(questNumber).get(0), answersList.get(questNumber).get(1),answersList.get(questNumber).get(2),answersList.get(questNumber).get(3));
                radioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);

                add(questionText, radioGroup, nextButton);
            }else {
                questionnaireResults.setQuestions(questionsList);
                questionnaireResults.setAnswers(userAnswers);
                questionnaireResults.setDateTime(LocalDateTime.now());
                mainManager.getQuestionnaireResoultsManager().addQuestionsResult(questionnaireResults);
                questionText.setText("To już wszystkie pytania na tą chwilę. Dzięki Twoim odpowiedzią możemy lepiej dostosować ofertę nauki jazdy do potrzeb obecnych oraz przyszłych kursantów.");
                add(questionText);
            }
        }
        catch (Exception e){
                questionText.setText("Wystąpił chwilowy problem z wygenerowaniem ankiety");
        }

    }

    private void nextQuestion(){
        if(radioGroup.isEmpty()){
        }else {
            userAnswers.add(radioGroup.getValue());
            questNumber++;
            if(questNumber < lastNumber){
                answers.add(radioGroup.getValue());
            }
            createQuest();
        }
    }



}
