package controllers;

/**
 * Created by asus on 10/25/2016.
 */
public interface Subcriber {
    void onEvent(EventType eventType, SingleController singleController);
}
