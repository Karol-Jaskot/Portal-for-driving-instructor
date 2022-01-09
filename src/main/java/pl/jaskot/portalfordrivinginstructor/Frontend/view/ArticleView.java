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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArticleView extends VerticalLayout {

    @Autowired
    private MainManager mainManager;
    private ArticlesManager articleManager;

    private Accordion accordion;
    private List<Article> articleList;

    public ArticleView(MainManager mainManager) {
        this.mainManager = mainManager;
        this.articleManager = mainManager.getArticleManager();
        ViewsConfig.setLayoutConfig(this, "article-view");
        createContent();
        setArticleToGrid();
    }

    private void setArticleToGrid() {
        for (Article article : articleList) {
            if (article.isPublic() || (!article.isPublic() && mainManager.isActive())) {
                createArticle(article);
            }
        }
    }

    private void createArticle(Article article) {
        VerticalLayout thisArticle = new VerticalLayout();
        thisArticle.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        thisArticle.add(
                new Label(article.getMessage()),
                new Label(article.getFullDate())
                //new Label(article.getAuthor())
        );
        if (mainManager.isAdmin()) {
            thisArticle.add(new Button("Usuń wiadomość",
                    event -> {
                        articleManager.deleteArticle(article);
                        MyMessage.pushInfoMessage("Wiadomość została usunięta!");
                    }));
        };
        accordion.add(article.getTitle(), thisArticle);
    }

    private void createContent() {
        articleList = new ArrayList();
        articleList.addAll(articleManager.getArticles());
        Collections.sort(articleList, (o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()));

        H1 title = new H1("Nowe ogłoszenia");
        title.getElement().getThemeList().add("dark");
        add(title);

        if (mainManager.isAdmin()) {
            add(new Button("Dodaj wiadomość", event -> {
                ArticleDialog articleDialog = new ArticleDialog(articleManager);
                articleDialog.open();
            }));
        }

        accordion = new Accordion();
        accordion.setWidthFull();
        add(accordion);
    }
}