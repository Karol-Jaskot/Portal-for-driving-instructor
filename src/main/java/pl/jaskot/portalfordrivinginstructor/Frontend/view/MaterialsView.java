package pl.jaskot.portalfordrivinginstructor.Frontend.view;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.jaskot.portalfordrivinginstructor.Backend.MainManager;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.Material;
import pl.jaskot.portalfordrivinginstructor.Backend.managers.MaterialsManager;
import pl.jaskot.portalfordrivinginstructor.Frontend.components.MaterialDialog;
import pl.jaskot.portalfordrivinginstructor.Frontend.components.MyMessage;

import java.util.ArrayList;
import java.util.List;

public class MaterialsView extends VerticalLayout {

    private MainManager mainManager;
    private Accordion accordion;
    private MaterialsManager materialsManager;
    private List<Material> materials;

    public MaterialsView(MainManager mainManager) {
        this.mainManager = mainManager;
        this.materialsManager = mainManager.getMaterialsManager();
        ViewsConfig.setLayoutConfig(this, "materials-view");

        createContent();
        setMaterialsToGrid();
    }

    private void createContent() {
        H1 title = new H1("Lista materiałów");
        title.getElement().getThemeList().add("dark");
        add(title);

        materials = new ArrayList();
        materials.addAll(materialsManager.getMaterials());

        if (mainManager.isAdmin()) {
            add(new Button("Dodaj materiał", event -> {
                new MaterialDialog(materialsManager).open();
            }));
        }

        accordion = new Accordion();
        accordion.setWidthFull();
        add(accordion);
    }

    private void setMaterialsToGrid() {
        for (Material material : materials) {
            if (material.isPublic() || (!material.isPublic() && mainManager.isActive())) {
                createMaterialLayout(material);
            }
        }
    }

    private void createMaterialLayout(Material material) {
        VerticalLayout thisMaterial = new VerticalLayout();
        thisMaterial.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        thisMaterial.add(
                new Label(material.getDescription()),
                new Anchor(material.getLinkToFile(), material.getLinkToFile())
        );
        if (mainManager.isAdmin()) {
            thisMaterial.add(new Button("Usuń materiał",
                    event -> {
                        materialsManager.deleteMaterial(material);
                        MyMessage.pushInfoMessage("Materiał został usunięty!");
                    }));
        };
        accordion.add(material.getTitle(), thisMaterial);
    }
}