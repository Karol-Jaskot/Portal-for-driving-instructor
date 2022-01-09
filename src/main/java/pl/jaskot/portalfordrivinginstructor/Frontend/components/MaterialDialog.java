package pl.jaskot.portalfordrivinginstructor.Frontend.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.Material;
import pl.jaskot.portalfordrivinginstructor.Backend.managers.MaterialsManager;

public class MaterialDialog extends Dialog {

    private TextField matTitle;
    private TextField matAdres;
    private TextArea matDescription;
    private Checkbox isPublic;

    @Autowired
    private MaterialsManager materialsManager;

    public MaterialDialog(MaterialsManager materialsManager) {
        this.materialsManager = materialsManager;
        setCloseOnEsc(false);
        setCloseOnOutsideClick(false);
        createContent();
    }

    private void createContent() {
        matTitle = new TextField("Tytuł");
        matDescription = new TextArea("Opis");
        matAdres = new TextField("Link do źródła");

        isPublic = new Checkbox();
        isPublic.setLabel("Dostępny publicznie");
        isPublic.setValue(true);

        HorizontalLayout hLayout = new HorizontalLayout();
        hLayout.add(
                new Button("Zapisz", event -> {
                    createMaterial();
                    close();
                }), new Button("Anuluj", event -> {
                    close();
                }));

        add(
                new Label("Kreator nowego materiału"),
                matTitle, matDescription, matAdres, isPublic, hLayout);
    }

    private void createMaterial() {
        materialsManager.addMaterial(Material.builder()
                .title(matTitle.getValue())
                .description(matDescription.getValue())
                .linkToFile(matAdres.getValue())
                .isPublic(isPublic.getValue())
                .build());
        MyMessage.pushInfoMessage("Nowy materiał został utworzony!");
    }
}