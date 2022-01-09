package pl.jaskot.portalfordrivinginstructor.Frontend.view;

import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ViewsConfig {

    public static void setLayoutConfig(VerticalLayout layout, String viewName){
        layout.setSizeFull();
        layout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        layout.addClassName(viewName);
    }

}
