package controllers;

import models.GameObject;
import views.GameView;

/**
 * Created by apple on 10/9/16.
 */
public class BulletController extends SingleController {

    public static final int SPEED = 5;

    public BulletController(GameObject gameObject, GameView gameView) {
        super(gameObject, gameView);
    }


    public void run() {
        // fly
        gameObject.move(0,-SPEED);
    }


}
