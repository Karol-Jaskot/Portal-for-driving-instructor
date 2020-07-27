package pl.jaskot.portalfordrivinginstructor.Frontend.view;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.Article;
import pl.jaskot.portalfordrivinginstructor.Backend.managers.ArticleManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ArticleView extends VerticalLayout{

    @Autowired
    ArticleManager articleManager;

    public ArticleView(ArticleManager articleManager) {
        this.articleManager = articleManager;
        setSizeFull();
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        addClassName("article-view");

        Accordion accordion = new Accordion();
        List<Article> articleList = new ArrayList();
        articleList.addAll(articleManager.getArticles());

        H1 title = new H1("Nowe ogłoszenia");
        title.getElement().getThemeList().add("dark");

        //tworzenie listy elementów
        for(Article article: articleList){
            VerticalLayout thisArticle = new VerticalLayout();
            thisArticle.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
            thisArticle.add(
                    new Label(article.getMessage()),
                    new Label(article.getAuthor())
            );
            accordion.add(article.getTitle(), thisArticle);
        }
        accordion.setWidthFull();
        add(title ,accordion);
    }
}


