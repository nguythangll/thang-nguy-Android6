package controllers;

import java.util.Vector;

/**
 * Created by asus on 10/25/2016.
 */
public class NotificationCenter {
    private Vector<Subcriber> subcriberVector;

    public NotificationCenter() {
        subcriberVector = new Vector<>();
    }

    public void onEvent(EventType eventType, SingleController sender) {
        for (Subcriber subcriber :
                subcriberVector) {
            subcriber.onEvent(eventType, sender);
        }
    }

    public void register(Subcriber subcriber) {
        subcriberVector.add(subcriber);
    }

    public void unregister(Subcriber subcriber) {
        subcriberVector.remove(subcriber);
    }
    public void reset(){
        this.subcriberVector.setSize(0);
    }
    public static final NotificationCenter instance = new NotificationCenter();
}
