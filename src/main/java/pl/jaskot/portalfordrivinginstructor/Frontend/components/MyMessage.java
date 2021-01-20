package pl.jaskot.portalfordrivinginstructor.Frontend.components;

import com.vaadin.flow.component.notification.Notification;

public class MyMessage extends Notification {

    public static void pushInfoMessage(String text){
        Notification notification = new MyMessage();
        notification.setPosition(Position.BOTTOM_END);
        notification.setText(text);
        notification.setDuration(3000);
        notification.open();
    }

}
