package pl.jaskot.portalfordrivinginstructor.Frontend.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.Article;
import pl.jaskot.portalfordrivinginstructor.Backend.managers.ArticlesManager;

import java.time.LocalDateTime;

public class ArticleDialog extends Dialog {

    ArticlesManager articleManager;

    private Button confirmButton;
    private Button cancelButton;
    private TextField articleTitle;
    private TextArea articleDescription;
    private String author;
    private Checkbox isPublic;


    public ArticleDialog (ArticlesManager articleManager){
        this.articleManager = articleManager;
        setCloseOnEsc(false);
        setCloseOnOutsideClick(false);

        createContent();
        add(articleTitle,articleDescription,isPublic);
        add(confirmButton,cancelButton);
    }

    private void createContent() {
        author = "Admin";

        articleTitle = new TextField("Tytuł");
        articleDescription = new TextArea("Opis");

        isPublic = new Checkbox();
        isPublic.setLabel("Dostępny publicznie");
        isPublic.setValue(false);

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
        //article.setAuthor(author);
        article.setPublic(isPublic.getValue());
        article.setCreateTime(LocalDateTime.now());
        articleManager.addArticle(article);
    }

}
