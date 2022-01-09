package pl.jaskot.portalfordrivinginstructor.Frontend.view;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import pl.jaskot.portalfordrivinginstructor.Backend.MainManager;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.Article;
import pl.jaskot.portalfordrivinginstructor.Backend.managers.ArticlesManager;
import pl.jaskot.portalfordrivinginstructor.Frontend.components.ArticleDialog;
import pl.jaskot.portalfordrivinginstructor.Frontend.components.MyMessage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ArticleView extends VerticalLayout{

    @Autowired
    private MainManager mainManager;
    private ArticlesManager articleManager;
    private static boolean isMessage = true;

    private Accordion accordion;
    private List<Article> articleList;
    private H1 title;
    private Button addArticleButton;

    public ArticleView(MainManager mainManager) {
        this.mainManager = mainManager;
        this.articleManager = mainManager.getArticleManager();
        setSizeFull();
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        addClassName("article-view");

        createContent();
        setArticleToGrid();

        add(title);
        if(mainManager.isAdmin()){
            add(addArticleButton);
        }
        add(accordion);
    }

    private void setArticleToGrid() {
        for(Article article: articleList){
            if(article.isPublic()){
                createArticle(article);
            }else {
                if(mainManager.isActive()){
                    createArticle(article);
                }
            }

        }
    }

    private void createArticle(Article article){
        VerticalLayout thisArticle = new VerticalLayout();
        thisArticle.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        thisArticle.add(
                new Label(article.getMessage()),
                new Label(article.getFullDate())
                //new Label(article.getAuthor())
        );
        if(mainManager.isAdmin()){
            thisArticle.add(new Button("Usuń wiadomość",
                    event -> { articleManager.deleteArticle(article);
                        MyMessage.pushInfoMessage("Wiadomość została usunięta!");
                    }));
        };
        accordion.add(article.getTitle(),  thisArticle);
    }

    private void createContent() {
        accordion = new Accordion();
        accordion.setWidthFull();

        articleList = new ArrayList();
        articleList.addAll(articleManager.getArticles());
        Collections.sort(articleList, (o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()));

        title = new H1("Nowe ogłoszenia");
        title.getElement().getThemeList().add("dark");

        addArticleButton = new Button("Dodaj wiadomość", event -> {
            ArticleDialog articleDialog = new ArticleDialog(articleManager);
            articleDialog.open();});
    }
}


