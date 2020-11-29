package pl.jaskot.portalfordrivinginstructor.Frontend.smallView;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import pl.jaskot.portalfordrivinginstructor.Backend.MainManager;

import java.util.ArrayList;
import java.util.List;

public class QuestionnaireForEmptyUserView extends VerticalLayout {

    private MainManager mainManager;
    private Button nextButton;

    private H2 questionText;
    private int questNumber = 0;
    private int lastNumber = 5;
    private List<String> questionsList;
    private List<List<String>> answersList;
    private RadioButtonGroup<String> radioGroup;
    private List<String> answers;

    public QuestionnaireForEmptyUserView(MainManager mainManager){
        this.mainManager = mainManager;
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSizeFull();

        createQuestions();
        createContent();
    }



    private void createContent() {
        try{
            removeAll();

            if(questNumber < lastNumber){
                nextButton = new Button("Dalej", event -> nextQuestion());
                questionText = new H2(questionsList.get(questNumber));

                radioGroup = new RadioButtonGroup<>();
                radioGroup.setItems(answersList.get(questNumber).get(0), answersList.get(questNumber).get(1),answersList.get(questNumber).get(2),answersList.get(questNumber).get(3));
                radioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);

                add(questionText, radioGroup, nextButton);
            }else {
                questionText = new H2("To już wszystkie pytania na tą chwilę. Dzięki Twoim odpowiedzią możemy lepiej dostosować ofertę nauki jazdy do potrzeb obecnych oraz przyszłych kursantów.");
                add(questionText);
            }
        }
        catch (Exception e){

        }

    }

    private void nextQuestion(){
        if(radioGroup.isEmpty()){

        }else {
            questNumber++;
            if(questNumber < lastNumber){

                answers.add(radioGroup.getValue());
            }
            createContent();
        }
    }

    private void createQuestions() {
        questionsList = new ArrayList<>();
        answersList = new ArrayList<>();
        answers = new ArrayList<>();

        // pytanie 1
        questionsList.add("Jak oceniasz rozpoznawalność wybranego przez Ciebie instruktora nauki jazdy?");
        List<String> ans1 = new ArrayList<>();
        ans1.add("Raczej wybrałbym innego instruktora");
        ans1.add("Przeciętny wizerunek na tle pozostałych instruktorów");
        ans1.add("Instruktor warty polecenia");
        ans1.add("Zdecydowanie najlepszy instruktor nauki jazdy");
        answersList.add(ans1);

        // pytanie 2
        questionsList.add("Który z aspektów jest dla Ciebie najważniejszy podczas przyszłej nauki jazdy?");
        List<String> ans2 = new ArrayList<>();
        ans2.add("Zdanie egzaminu teoretycznego oraz praktycznego za pierwszym razem");
        ans2.add("Jak najlepsze zrozumienie zasad oraz przepisów ruchu drogowego");
        ans2.add("");
        ans2.add("");
        answersList.add(ans2);

        // pytanie 3
        questionsList.add("");
        List<String> ans3 = new ArrayList<>();
        ans3.add("");
        ans3.add("");
        ans3.add("");
        ans3.add("");
        answersList.add(ans3);

        // pytanie 4
        questionsList.add("");
        List<String> ans4 = new ArrayList<>();
        ans4.add("");
        ans4.add("");
        ans4.add("");
        ans4.add("");
        answersList.add(ans4);

        // pytanie 5
        questionsList.add("");
        List<String> ans5 = new ArrayList<>();
        ans5.add("");
        ans5.add("");
        ans5.add("");
        ans5.add("");
        answersList.add(ans5);
    }

}
