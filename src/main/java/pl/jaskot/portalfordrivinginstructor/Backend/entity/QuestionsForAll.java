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
        ans2.add("Odp nr 2");
        ans2.add("Odp nr 3");
        ans2.add("Odp nr 4");
        answersList.add(ans2);

        // pytanie 3
        questionsList.add("Pytanie nr 3");
        List<String> ans3 = new ArrayList<>();
        ans3.add("Odp nr 1");
        ans3.add("Odp nr 2");
        ans3.add("Odp nr 3");
        ans3.add("Odp nr 4");
        answersList.add(ans3);


    }
}
