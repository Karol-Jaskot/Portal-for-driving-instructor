package pl.jaskot.portalfordrivinginstructor.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jaskot.portalfordrivinginstructor.Backend.managers.ArticlesManager;
import pl.jaskot.portalfordrivinginstructor.Backend.managers.MaterialsManager;
import pl.jaskot.portalfordrivinginstructor.Backend.managers.QuestionsManager;
import pl.jaskot.portalfordrivinginstructor.Backend.managers.UsersManager;

@Service
public class MainManager {

    @Autowired
    ArticlesManager articleManager;

    @Autowired
    QuestionsManager questionsManager;

    @Autowired
    MaterialsManager materialsManager;

    @Autowired
    UsersManager usersManager;

    public MainManager(ArticlesManager articleManager, QuestionsManager questionsManager, MaterialsManager materialsManager, UsersManager usersManager) {
        this.articleManager = articleManager;
        this.questionsManager = questionsManager;
        this.materialsManager = materialsManager;
        this.usersManager = usersManager;
    }

    public ArticlesManager getArticleManager() {
        return articleManager;
    }

    public QuestionsManager getQuestionsManager(){return questionsManager;}

    public MaterialsManager getMaterialsManager(){return materialsManager;}

    public UsersManager getUsersManager(){return usersManager;}

    public boolean isAdmin(){
        return usersManager.isAdmin();
    }

    public boolean isActive(){
        return usersManager.isActive();
    }
}
