package pl.jaskot.portalfordrivinginstructor.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jaskot.portalfordrivinginstructor.Backend.managers.ArticleManager;
import pl.jaskot.portalfordrivinginstructor.Backend.repository.ArticleRepo;

import javax.annotation.ManagedBean;

@Service
public class MainManager {

    @Autowired
    ArticleManager articleManager;

    public MainManager(ArticleManager articleManager) {
        this.articleManager = articleManager;
    }

    public ArticleManager getArticleManager() {
        return articleManager;
    }
}
