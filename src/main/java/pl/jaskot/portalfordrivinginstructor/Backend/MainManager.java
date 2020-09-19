package pl.jaskot.portalfordrivinginstructor.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jaskot.portalfordrivinginstructor.Backend.managers.ArticleManager;
import pl.jaskot.portalfordrivinginstructor.Backend.managers.QuestionsManager;

@Service
public class MainManager {

    @Autowired
    ArticleManager articleManager;

    @Autowired
    QuestionsManager questionsManager;

    public MainManager(ArticleManager articleManager, QuestionsManager questionsManager) {
        this.articleManager = articleManager;
        this.questionsManager = questionsManager;
    }

    public ArticleManager getArticleManager() {
        return articleManager;
    }

    public QuestionsManager getQuestionsManager(){return questionsManager;}
}
