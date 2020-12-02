package pl.jaskot.portalfordrivinginstructor.Backend.entity;

import java.util.ArrayList;
import java.util.List;

public class QuestionsForAll {

    private List<String> questionsList;
    private List<List<String>> answersList;


    public QuestionsForAll(){
        questionsList = new ArrayList<>();
        answersList = new ArrayList<>();
        createQuestions();
    }

    public List<String> getQuestionsList(){return questionsList;}

    public List<List<String>> getAnswersList(){return answersList;}

    private void createQuestions() {

        questionsList = new ArrayList<>();
        answersList = new ArrayList<>();

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
