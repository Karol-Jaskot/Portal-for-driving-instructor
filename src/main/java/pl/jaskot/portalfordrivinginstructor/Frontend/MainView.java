package pl.jaskot.portalfordrivinginstructor.Frontend;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.HtmlImport;
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
import org.springframework.beans.factory.annotation.Autowired;
import pl.jaskot.portalfordrivinginstructor.Backend.MainManager;
import pl.jaskot.portalfordrivinginstructor.Frontend.view.*;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route("")
@PWA(name = "MyApp", shortName = "App")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")

@HtmlImport("styles/shared-styles.html")
@Theme(value = Material.class, variant = Material.DARK)
public class MainView extends AppLayout {

    @Autowired
    private MainManager mainManager;

    private Tabs tabs;

    public MainView(MainManager mainManager) throws FileNotFoundException {
        this.mainManager = mainManager;
        addToNavbar(new DrawerToggle(),  new Label("Portal dla instruktora nauki jazdy"));

        createTabs();
        addToDrawer(createImage() ,tabs);
    }

    private void createTabs() throws FileNotFoundException {
        Tab tab1 = new Tab("Ogłoszenia");
        Div page1 = new Div();
        page1.add(new ArticleView(mainManager));

        Tab tab2 = new Tab("Grafik");
        Div page2 = new Div();
        page2.add(new GraphicView(mainManager));
        page2.setVisible(false);

        Tab tab3 = new Tab("Materiały");
        Div page3 = new Div();
        page3.add(new MaterialsView(mainManager));
        page3.setVisible(false);

        Tab tab4 = new Tab("Testy online");
        Div page4 = new Div();
        page4.add(new TestView(mainManager));
        page4.setVisible(false);

        Tab tab5 = new Tab("Kontakt");
        Div page5 = new Div();
        page5.add(new ContactView());
        page5.setVisible(false);


        Tab tab6 = new Tab("Ankieta");
        Div page6 = new Div();
        page6.add(new QuestionnaireView(mainManager));
        page6.setVisible(false);

        Tab tab7 = new Tab("Ustawienia");
        Div page7 = new Div();
        page7.add(new ManagerView(mainManager));
        page7.setVisible(false);

        Map<Tab, Component> tabsToPages = new HashMap<>();
        tabsToPages.put(tab1, page1);
        tabsToPages.put(tab2, page2);
        tabsToPages.put(tab3, page3);
        tabsToPages.put(tab4, page4);
        tabsToPages.put(tab5, page5);
        tabsToPages.put(tab6, page6);
        tabsToPages.put(tab7, page7);

        tabs = new Tabs(tab1, tab2, tab3, tab4, tab5, tab6, tab7);
        Div pages = new Div(page1, page2, page3, page4,page5, page6, page7);
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
