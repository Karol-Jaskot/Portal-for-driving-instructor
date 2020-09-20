package pl.jaskot.portalfordrivinginstructor.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jaskot.portalfordrivinginstructor.Backend.managers.ArticlesManager;
import pl.jaskot.portalfordrivinginstructor.Backend.managers.MaterialsManager;
import pl.jaskot.portalfordrivinginstructor.Backend.managers.QuestionsManager;

@Service
public class MainManager {

    @Autowired
    ArticlesManager articleManager;

    @Autowired
    QuestionsManager questionsManager;

    @Autowired
    MaterialsManager materialsManager;

    public MainManager(ArticlesManager articleManager, QuestionsManager questionsManager, MaterialsManager materialsManager) {
        this.articleManager = articleManager;
        this.questionsManager = questionsManager;
        this.materialsManager = materialsManager;
    }

    public ArticlesManager getArticleManager() {
        return articleManager;
    }

    public QuestionsManager getQuestionsManager(){return questionsManager;}

    public MaterialsManager getMaterialsManager(){return materialsManager;}
}
