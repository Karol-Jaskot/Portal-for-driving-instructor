package pl.jaskot.portalfordrivinginstructor.Frontend.smallView;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.jaskot.portalfordrivinginstructor.Backend.MainManager;
import pl.jaskot.portalfordrivinginstructor.Frontend.components.ArticleDialog;
import pl.jaskot.portalfordrivinginstructor.Frontend.components.MaterialDialog;

public class AdminSettingsView extends VerticalLayout {

    private MainManager mainManager;
    private Button addMaterial, addArticle;

    public AdminSettingsView(MainManager mainManager){
        this.mainManager = mainManager;
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        createContent();
        add(addArticle,addMaterial);
    }

    private void createContent() {

        addArticle = new Button("Dodaj wiadomość", event -> {
            new ArticleDialog(mainManager.getArticleManager()).open();
        });

        addMaterial = new Button("Dodaj materiał", event -> {
            new MaterialDialog(mainManager.getMaterialsManager()).open();
        });
    }

}
