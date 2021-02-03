package pl.jaskot.portalfordrivinginstructor.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jaskot.portalfordrivinginstructor.Backend.managers.*;

@Service
public class MainManager {

    @Autowired
    ArticlesManager articleManager;

    @Autowired
    QuestionsManager questionsManager;

    @Autowired
    MaterialsManager materialsManager;

    @Autowired
    CalendarManager calendarManager;

    @Autowired
    UsersManager usersManager;

    @Autowired
    QuestionnaireResoultsManager questionnaireResoultsManager;

    public MainManager(ArticlesManager articleManager, QuestionsManager questionsManager, MaterialsManager materialsManager, UsersManager usersManager,CalendarManager calendarManager, QuestionnaireResoultsManager questionnaireResoultsManager) {
        this.articleManager = articleManager;
        this.questionsManager = questionsManager;
        this.materialsManager = materialsManager;
        this.usersManager = usersManager;
        this.calendarManager = calendarManager;
        this.questionnaireResoultsManager = questionnaireResoultsManager;
    }

    public ArticlesManager getArticleManager() {
        return articleManager;
    }

    public QuestionsManager getQuestionsManager(){return questionsManager;}

    public MaterialsManager getMaterialsManager(){return materialsManager;}

    public UsersManager getUsersManager(){return usersManager;}

    public CalendarManager getCalendarManager(){return calendarManager;}

    public boolean isAdmin(){
        return usersManager.isAdmin();
    }

    public boolean isActive(){
        return usersManager.isActive();
    }

    public Long getUserId(){ return usersManager.getMainUser().getId(); }

    public QuestionnaireResoultsManager getQuestionnaireResoultsManager(){
        return questionnaireResoultsManager;
    }
}
