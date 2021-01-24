package pl.jaskot.portalfordrivinginstructor.Frontend.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.Material;
import pl.jaskot.portalfordrivinginstructor.Backend.managers.MaterialsManager;

public class MaterialDialog extends Dialog {

    private Button confirmButton;
    private Button cancelButton;
    private TextField matTitle;
    private TextField matAdres;
    private TextArea matDescription;
    private Checkbox isPublic;
    private Label title;

    @Autowired
    private MaterialsManager materialsManager;

    public MaterialDialog(MaterialsManager materialsManager) {
        this.materialsManager = materialsManager;
        setCloseOnEsc(false);
        setCloseOnOutsideClick(false);
        createContent();
        createPageView();
    }

    private void createPageView() {
        VerticalLayout layout = new VerticalLayout();
        HorizontalLayout hLayout = new HorizontalLayout();
        hLayout.add(confirmButton,cancelButton);
        layout.add(title,matTitle,matDescription,matAdres, isPublic,hLayout);
        add(layout);
    }

    private void createContent() {
        title = new Label("Kreator nowego materiału");
        matTitle = new TextField("Tytuł");
        matDescription = new TextArea("Opis");
        matAdres = new TextField("Link do źródła");

        isPublic = new Checkbox();
        isPublic.setLabel("Dostępny publicznie");
        isPublic.setValue(true);

        confirmButton = new Button("Zapisz", event -> {
            createMaterial();
            close();
        });
        cancelButton = new Button("Anuluj", event -> {
            close();
        });
    }

    private void createMaterial(){
        Material a1 = new Material();
        a1.setTitle(matTitle.getValue());
        a1.setDescription(matDescription.getValue());
        a1.setLinkToFile(matAdres.getValue());
        a1.setPublic(isPublic.getValue());
        materialsManager.addMaterial(a1);
        MyMessage.pushInfoMessage("Nowy materiał został utworzony!");
    }

}
