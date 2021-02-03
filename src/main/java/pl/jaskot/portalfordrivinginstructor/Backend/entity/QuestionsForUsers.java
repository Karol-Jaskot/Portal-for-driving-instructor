package pl.jaskot.portalfordrivinginstructor.Backend.entity;

import pl.jaskot.portalfordrivinginstructor.Backend.MainManager;

import java.util.ArrayList;
import java.util.List;

public class QuestionsForUsers {

    private List<String> questionsList;
    private List<List<String>> answersList;
    private MainManager mainManager;

    public QuestionsForUsers(MainManager mainManager) {
        this.mainManager = mainManager;
        questionsList = new ArrayList<>();
        answersList = new ArrayList<>();
        createQuestions();
    }

    public List<String> getQuestionsList() {
        return questionsList;
    }

    public List<List<String>> getAnswersList() {
        return answersList;
    }

    private void createQuestions() {
        questionsList = new ArrayList<>();
        answersList = new ArrayList<>();

        // pytanie 1
        questionsList.add("Pytanie 1");
        List<String> ans1 = new ArrayList<>();
        ans1.add("Raczej wybrałbym innego instruktora");
        ans1.add("Przeciętny wizerunek na tle pozostałych instruktorów");
        ans1.add("Instruktor warty polecenia");
        ans1.add("Zdecydowanie najlepszy instruktor nauki jazdy");
        answersList.add(ans1);

        // pytanie 2
        questionsList.add("Pytanie 2");
        List<String> ans2 = new ArrayList<>();
        ans2.add("Zdanie egzaminu teoretycznego oraz praktycznego za pierwszym razem");
        ans2.add("Jak najlepsze zrozumienie zasad oraz przepisów ruchu drogowego");
        ans2.add("");
        ans2.add("");
        answersList.add(ans2);

        // pytanie 3
        questionsList.add("Pytanie 3");
        List<String> ans3 = new ArrayList<>();
        ans3.add("");
        ans3.add("");
        ans3.add("");
        ans3.add("");
        answersList.add(ans3);


    }
}