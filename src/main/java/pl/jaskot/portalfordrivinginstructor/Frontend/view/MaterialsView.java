package pl.jaskot.portalfordrivinginstructor.Frontend.view;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.Material;
import pl.jaskot.portalfordrivinginstructor.Backend.managers.MaterialsManager;
import pl.jaskot.portalfordrivinginstructor.Frontend.components.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

public class MaterialsView extends VerticalLayout {

    private H1 title;
    private Accordion accordion;
    private MaterialsManager materialsManager;
    private List<Material> materials;
    private Button addMaterialButton;

    public MaterialsView(MaterialsManager materialsManager) {
        this.materialsManager = materialsManager;
        setSizeFull();
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        addClassName("materials-view");

        title = new H1("Lista materiałów");

        putSomeData();
        createElements();
        setMaterialsToGrid();
        add(title,addMaterialButton, accordion);
    }


    private void putSomeData() {
        Material a1 = new Material();
        a1.setTitle("Książka 1");
        a1.setDescription("Treść i info");
        a1.setLinkToFile("https://vaadin.com/components/vaadin-anchor");
        materialsManager.addMaterial(a1);
    }


    private void createElements() {

        accordion = new Accordion();
        accordion.setWidthFull();

        materials = new ArrayList();
        materials.addAll(materialsManager.getMaterials());

        title = new H1("Lista materiałów");
        title.getElement().getThemeList().add("dark");

        addMaterialButton = new Button("Dodaj materiał", event -> {
            MaterialDialog materialDialog = new MaterialDialog(materialsManager);
            materialDialog.open();});
    }


    private void setMaterialsToGrid() {
        for(Material material: materials){
            VerticalLayout thisMaterial = new VerticalLayout();
            thisMaterial.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
            thisMaterial.add(
                    new Label(material.getDescription()),
                    new Anchor(material.getLinkToFile(),material.getLinkToFile()),
                    new Button("Usuń materiał", event -> {
                        materialsManager.deleteMaterial(material);
                }));
            accordion.add(material.getTitle(),  thisMaterial);
        }
    }
}
