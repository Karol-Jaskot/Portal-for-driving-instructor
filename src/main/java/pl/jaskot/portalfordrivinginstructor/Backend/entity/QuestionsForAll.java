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
        ans2.add("Przyjemna i spokojna atmosfera podczas nauki ");
        ans2.add("Czas do uzyskania prawa jazdy");
        ans2.add("Zdobycie jak największego doświadczenia w kierowaniu pojazdem");
        answersList.add(ans2);

        // pytanie 3
        questionsList.add("Dlaczego postanowiłeś skorzystać z oferty obecnego instruktora?");
        List<String> ans3 = new ArrayList<>();
        ans3.add("Niewielka odległość od miejsca zamieszkania");
        ans3.add("Niski koszt kursu");
        ans3.add("Polecenie instruktora przez znajomych");
        ans3.add("Pozytywne opinie w mediach społecznościowych");
        answersList.add(ans3);


    }
}
