package pl.jaskot.portalfordrivinginstructor.Frontend.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;
import pl.jaskot.portalfordrivinginstructor.Frontend.TestView;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route("")
@PWA(name = "MyApp", shortName = "App")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")

@Theme(value = Material.class, variant = Material.DARK)
public class MainView extends AppLayout {

    private Tabs tabs;

    public MainView(){
        Label title = new Label("Portal dla instruktora nauki jazdy");
        addToNavbar(new DrawerToggle(), title);

        createTabs();
        addToDrawer(createImage() ,tabs);

    }

    private void createTabs() {
        Tab tab1 = new Tab("Ogłoszenia");
        Div page1 = new Div();
        page1.setText("Page#1");

        Tab tab2 = new Tab("Grafik");
        Div page2 = new Div();
        page2.setText("Page#2");
        page2.setVisible(false);

        Tab tab3 = new Tab("Materiały");
        Div page3 = new Div();
        page3.setText("Page#3");
        page3.setVisible(false);

        Tab tab4 = new Tab("Testy online");
        Div page4 = new Div();
        page4.add(new TestView());
        page4.setVisible(false);

        Tab tab5 = new Tab("Kontakt");
        Div page5 = new Div();
        page5.add(new TestView());
        page5.setVisible(false);


        Tab tab6 = new Tab("Ankieta");
        Div page6 = new Div();
        page6.add(new TestView());
        page6.setVisible(false);


        Map<Tab, Component> tabsToPages = new HashMap<>();
        tabsToPages.put(tab1, page1);
        tabsToPages.put(tab2, page2);
        tabsToPages.put(tab3, page3);
        tabsToPages.put(tab4, page4);
        tabsToPages.put(tab5, page5);
        tabsToPages.put(tab6, page6);

        tabs = new Tabs(tab1, tab2, tab3, tab4, tab5, tab6);
        Div pages = new Div(page1, page2, page3, page4,page5, page6);
        Set<Component> pagesShown = Stream.of(page1)
                .collect(Collectors.toSet());

        tabs.addSelectedChangeListener(event -> {
            pagesShown.forEach(page -> page.setVisible(false));
            pagesShown.clear();
            Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
            selectedPage.setVisible(true);
            pagesShown.add(selectedPage);
            setContent(selectedPage);
        });

        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        setContent(page1);
    }

    Image createImage(){
        Image image = new Image("https://www.fototapety24.net/img/wlasne_foto/d/fototapeta_wschod_slonca_nad_polem_lawendy.jpg","Foto");
        image.setHeight("70px");
        image.setWidthFull();
        return image;
    }

}
