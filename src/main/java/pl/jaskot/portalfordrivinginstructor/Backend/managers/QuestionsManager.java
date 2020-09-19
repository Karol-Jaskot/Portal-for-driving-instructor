package pl.jaskot.portalfordrivinginstructor.Backend.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.Question;
import pl.jaskot.portalfordrivinginstructor.Backend.repository.QuestionsRepo;

import java.util.Collection;
import java.util.List;
import java.util.Random;

@Service
public class QuestionsManager {


    @Autowired
    QuestionsRepo questionsRepo;

    List<Question> questions;


    public List<Question> getQuestionsPack(int howMany){
        List<Question> randomQuestions = null;
        randomQuestions.addAll((Collection<? extends Question>) questionsRepo.findAll());

        for(int i = 0; i<howMany ; i++)
        {
            Random rand = new Random();
            int number = rand.nextInt(randomQuestions.size() - 1);
            questions.add(randomQuestions.get(number));
            randomQuestions.remove(number);
        }

        return questions;
    }




}
