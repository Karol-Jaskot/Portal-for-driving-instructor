package pl.jaskot.portalfordrivinginstructor.Backend.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.Article;
import pl.jaskot.portalfordrivinginstructor.Backend.repository.ArticlesRepo;

import java.util.List;

@Service
public class ArticlesManager {

    @Autowired
    ArticlesRepo articleRepo;

    public ArticlesManager(ArticlesRepo articleRepo){
        this.articleRepo = articleRepo;
    }

    public void addArticle(Article article){
        articleRepo.save(article);
    }

    public List<Article> getArticles(){
        return (List<Article>) articleRepo.findAll();
    }

}
