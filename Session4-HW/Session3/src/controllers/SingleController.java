package controllers;

import models.GameObject;
import views.GameView;

import java.awt.*;

/**
 * Created by asus on 10/11/2016.
 */
public class SingleController implements BaseController{
    public GameObject gameObject;
    protected GameView gameView;

    public SingleController(GameObject gameObject, GameView gameView) {
        this.gameObject = gameObject;
        this.gameView = gameView;
    }

    public void draw(Graphics g) {
        // View - Model
        gameView.drawImage(g, gameObject);
    }
    public void run(){

    }
}
