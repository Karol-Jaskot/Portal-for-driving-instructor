package pl.jaskot.portalfordrivinginstructor.Frontend.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
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

    @Autowired
    private MaterialsManager materialsManager;

    public MaterialDialog(MaterialsManager materialsManager) {
        this.materialsManager = materialsManager;
        setCloseOnEsc(false);
        setCloseOnOutsideClick(false);

        createContent();
        add(matTitle,matDescription,matAdres);
        add(confirmButton,cancelButton);
    }

    private void createContent() {
        matTitle = new TextField("Tytuł");
        matDescription = new TextArea("Opis");
        matAdres = new TextField("Link do źródła");

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
        materialsManager.addMaterial(a1);
    }

}
