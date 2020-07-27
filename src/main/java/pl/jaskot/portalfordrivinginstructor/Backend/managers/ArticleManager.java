package pl.jaskot.portalfordrivinginstructor.Backend.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.Article;
import pl.jaskot.portalfordrivinginstructor.Backend.repository.ArticleRepo;

import javax.annotation.ManagedBean;
import java.util.List;

@Service
public class ArticleManager {

    @Autowired
    ArticleRepo articleRepo;

    public ArticleManager(ArticleRepo articleRepo){
        this.articleRepo = articleRepo;
    }

    public void addArticle(Article article){
        articleRepo.save(article);
    }

    public List<Article> getArticles(){
        return (List<Article>) articleRepo.findAll();
    }

}
