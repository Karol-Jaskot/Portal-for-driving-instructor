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
            ArticleDialog articleDialog = new ArticleDialog(mainManager.getArticleManager());
            articleDialog.open();});

        addMaterial = new Button("Dodaj materiał", event -> {
            MaterialDialog materialDialog = new MaterialDialog(mainManager.getMaterialsManager());
            materialDialog.open();});
    }

}
