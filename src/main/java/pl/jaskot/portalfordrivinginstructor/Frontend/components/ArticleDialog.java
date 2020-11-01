package pl.jaskot.portalfordrivinginstructor.Frontend.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.Article;
import pl.jaskot.portalfordrivinginstructor.Backend.managers.ArticlesManager;

public class ArticleDialog extends Dialog {

    @Autowired
    ArticlesManager articleManager;

    private Button confirmButton;
    private Button cancelButton;
    private TextField articleTitle;
    private TextArea articleDescription;
    private String author;


    public ArticleDialog (ArticlesManager articleManager){
        this.articleManager = articleManager;
        setCloseOnEsc(false);
        setCloseOnOutsideClick(false);

        createContent();
        add(articleTitle,articleDescription);
        add(confirmButton,cancelButton);
    }

    private void createContent() {

        author = "Admin";

        articleTitle = new TextField("Tytuł");
        articleDescription = new TextArea("Opis");

        confirmButton = new Button("Zapisz", event -> {
            createArticle();
            close();
        });
        cancelButton = new Button("Anuluj", event -> {
            close();
        });
    }

    private void createArticle() {
        Article article = new Article();
        article.setTitle(articleTitle.getValue());
        article.setMessage(articleDescription.getValue());
        article.setAuthor(author);
        article.setCreateTime(java.util.Calendar.getInstance().getTime());
        articleManager.addArticle(article);
    }

    private void putSomeData() {
        Article a1 = new Article();
        a1.setTitle("Wieści");
        a1.setMessage("Treść i info");
        a1.setAuthor("Stefan");
        a1.setCreateTime(java.util.Calendar.getInstance().getTime());
        articleManager.addArticle(a1);

        Article article = new Article();
        article.setTitle(articleTitle.getValue());
        article.setMessage(articleDescription.getValue());
        article.setAuthor(author);
        article.setCreateTime(java.util.Calendar.getInstance().getTime());
        articleManager.addArticle(article);
    }

}
