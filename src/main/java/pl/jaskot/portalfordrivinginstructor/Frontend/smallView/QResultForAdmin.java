package pl.jaskot.portalfordrivinginstructor.Frontend.smallView;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.jaskot.portalfordrivinginstructor.Backend.MainManager;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.Article;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.QuestionnaireResults;
import pl.jaskot.portalfordrivinginstructor.Backend.managers.QuestionnaireResoultsManager;
import pl.jaskot.portalfordrivinginstructor.Frontend.components.ArticleDialog;
import pl.jaskot.portalfordrivinginstructor.Frontend.components.MyMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QResultForAdmin extends VerticalLayout {
    
    private MainManager mainManager;
    private QuestionnaireResoultsManager qRM;
    private Accordion accordion;
    private List<QuestionnaireResults> questionnaireResultsList;
    private H1 title;

    public QResultForAdmin(MainManager mainManager) {
        this.mainManager = mainManager;
        this.qRM = mainManager.getQuestionnaireResoultsManager();
        setSizeFull();
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        addClassName("q-view");
        
        createContent();
        setQToGrid();

        add(title);
        add(accordion);
    }

    private void setQToGrid() {
        for(QuestionnaireResults questionnaireResults: questionnaireResultsList){
            createQ(questionnaireResults);
        }
    }

    private void createQ(QuestionnaireResults questionnaireResults) {
        VerticalLayout thisQ = new VerticalLayout();
        thisQ.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        for( int x=0; x < questionnaireResults.getQuestions().size(); x++){
            thisQ.add(
                    new Label(questionnaireResults.getQuestions().get(x)),
                    new Label(questionnaireResults.getAnswers().get(x))
            );
        }
        accordion.add("Ankieta nr: "+ questionnaireResults.getId()+ " Data: "+questionnaireResults.getFullDate() ,  thisQ);
    }

    private void createContent() {
        accordion = new Accordion();
        accordion.setWidthFull();

        questionnaireResultsList = new ArrayList();
        questionnaireResultsList.addAll(qRM.getQuestionnaireResoults());

        title = new H1("Lista raportów");
        title.getElement().getThemeList().add("dark");

    }
}
