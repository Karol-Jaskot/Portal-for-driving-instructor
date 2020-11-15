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

    HoursManager hoursManager;

    DaysManager daysManager;

    @Autowired
    UsersManager usersManager;

    public MainManager(ArticlesManager articleManager, QuestionsManager questionsManager, MaterialsManager materialsManager, UsersManager usersManager, HoursManager hoursManager, DaysManager daysManager) {
        this.articleManager = articleManager;
        this.questionsManager = questionsManager;
        this.materialsManager = materialsManager;
        this.usersManager = usersManager;
        this.hoursManager = hoursManager;
        this.daysManager = daysManager;
        daysManager.hoursManager = hoursManager;

    }

    public ArticlesManager getArticleManager() {
        return articleManager;
    }

    public QuestionsManager getQuestionsManager(){return questionsManager;}

    public MaterialsManager getMaterialsManager(){return materialsManager;}

    public UsersManager getUsersManager(){return usersManager;}

    public HoursManager getHourManager(){return hoursManager;}

    public DaysManager getDaysManager(){return daysManager;}

    public boolean isAdmin(){
        return usersManager.isAdmin();
    }

    public boolean isActive(){
        return usersManager.isActive();
    }
}
