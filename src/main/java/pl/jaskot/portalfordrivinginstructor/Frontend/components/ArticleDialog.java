package pl.jaskot.portalfordrivinginstructor.Frontend.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.html.H2;
import org.springframework.beans.factory.annotation.Autowired;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.Article;
import pl.jaskot.portalfordrivinginstructor.Backend.managers.ArticlesManager;

import java.time.LocalDateTime;

public class ArticleDialog extends Dialog {

    ArticlesManager articleManager;

    private Label title;
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
        createPageView();
    }

    private void createPageView() {
        VerticalLayout layout = new VerticalLayout();
        HorizontalLayout hLayout = new HorizontalLayout();
        hLayout.add(confirmButton,cancelButton);
        layout.add(title,articleTitle,articleDescription,isPublic,hLayout);
        add(layout);
    }

    private void createContent() {
        title = new Label("Kreator nowej wiadomości");
        author = "Admin";

        articleTitle = new TextField("Tytuł:");
        articleDescription = new TextArea("Treść:");

        isPublic = new Checkbox();
        isPublic.setLabel("Dostępny publicznie");
        isPublic.setValue(true);

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
        MyMessage.pushInfoMessage("Nowe ogłoszenie zostało utworzone!");
    }

}
