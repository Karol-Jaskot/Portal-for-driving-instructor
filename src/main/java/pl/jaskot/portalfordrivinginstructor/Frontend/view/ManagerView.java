package pl.jaskot.portalfordrivinginstructor.Frontend.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import pl.jaskot.portalfordrivinginstructor.Backend.MainManager;
import pl.jaskot.portalfordrivinginstructor.Frontend.components.ArticleDialog;

public class ManagerView extends VerticalLayout {

    private H1 title;

    @Autowired
    private MainManager mainManager;
    private Button addArticleButton;

    ArticleDialog articleDialog;

    public ManagerView(MainManager mainManager) {
        this.mainManager = mainManager;
        setSizeFull();
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        addClassName("manager-view");

        createContent();
        add(title, addArticleButton);
    }

    private void createContent() {
        title = new H1("Zarządzanie aplikacją");

        articleDialog = new ArticleDialog(mainManager.getArticleManager());
        addArticleButton = new Button("Dodaj wiadomość", event -> {articleDialog.open();});
    }
}
