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
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.Article;
import pl.jaskot.portalfordrivinginstructor.Backend.managers.ArticlesManager;

import java.time.LocalDateTime;

@Log
public class ArticleDialog extends Dialog {

    private ArticlesManager articleManager;
    private TextField articleTitle;
    private TextArea articleDescription;
    private Checkbox isPublic;

    public ArticleDialog (ArticlesManager articleManager){
        this.articleManager = articleManager;
        setCloseOnEsc(false);
        setCloseOnOutsideClick(false);
        createContent();
    }

    private void createContent() {
        articleTitle = new TextField("Tytuł:");
        articleDescription = new TextArea("Treść:");
        isPublic = new Checkbox("Dostępny publicznie");
        isPublic.setValue(true);

        HorizontalLayout hLayout = new HorizontalLayout();
        hLayout.add(
                new Button("Zapisz", event -> {
                    createArticle();
                    close();
        }),
                new Button("Anuluj", event -> {
                    close();
                }));

        add(
                new Label("Kreator nowej wiadomości"),
                articleTitle,articleDescription,isPublic,hLayout);
    }

    private void createArticle() {
        try {
            articleManager.addArticle(
                    Article.builder()
                            .title(articleTitle.getValue())
                            .message(articleDescription.getValue())
                            .isPublic(isPublic.getValue())
                            .createTime(LocalDateTime.now())
                            .build()
            );
            MyMessage.pushInfoMessage("Nowy artykuł został utworzony!");
        }catch (Exception e){
            log.warning(e.toString());
        }
    }
}