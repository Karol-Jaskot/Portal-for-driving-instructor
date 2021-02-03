package pl.jaskot.portalfordrivinginstructor.Backend.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.QuestionnaireResults;
import pl.jaskot.portalfordrivinginstructor.Backend.repository.QuestionnaireResultsRepo;

import java.util.List;

@Service
public class QuestionnaireResoultsManager {

    @Autowired
    QuestionnaireResultsRepo questionnaireResultsRepo;

    public QuestionnaireResoultsManager(QuestionnaireResultsRepo questionnaireResultsRepo){
        this.questionnaireResultsRepo = questionnaireResultsRepo;
    }

    public void addQuestonsResoult(QuestionnaireResults questionnaireResults){
        this.questionnaireResultsRepo.save(questionnaireResults);
    }

    public List<QuestionnaireResults> getQuestionnaireResoults(){
        return (List<QuestionnaireResults>) questionnaireResultsRepo.findAll();
    }
}
