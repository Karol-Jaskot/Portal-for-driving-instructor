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
import java.util.Date;
import java.util.List;

public class ArticleView extends VerticalLayout{

    @Autowired
    ArticleManager articleManager;
    private Accordion accordion;
    private List<Article> articleList;
    private H1 title;

    public ArticleView(ArticleManager articleManager) {
        this.articleManager = articleManager;
        setSizeFull();
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        addClassName("article-view");

        //putSomeData();
        createElements();
        setArticleToGrid();
        add(title ,accordion);
    }

    private void putSomeData() {
        Article a1 = new Article();
        a1.setTitle("Wieści");
        a1.setMessage("Treść i info");
        a1.setAuthor("Stefan");
        a1.setCreateTime(java.util.Calendar.getInstance().getTime());

        articleManager.addArticle(a1);
    }

    private void setArticleToGrid() {
        for(Article article: articleList){
            VerticalLayout thisArticle = new VerticalLayout();
            thisArticle.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
            thisArticle.add(
                    new Label(article.getMessage()),
                    new Label(article.getCreateTime().toString()),
                    new Label(article.getAuthor())
            );
            accordion.add(article.getTitle(),  thisArticle);
        }
    }

    private void createElements() {
        accordion = new Accordion();
        accordion.setWidthFull();

        articleList = new ArrayList();
        articleList.addAll(articleManager.getArticles());

        title = new H1("Nowe ogłoszenia");
        title.getElement().getThemeList().add("dark");
    }
}


