package controllers;

import models.GameObject;
import views.GameView;

/**
 * Created by asus on 10/12/2016.
 */
public class EnemyBulletController extends SingleController {
    public static final int SPEED = 5;

    public EnemyBulletController(GameObject gameObject, GameView gameView) {
        super(gameObject, gameView);
    }

    public void run() {
        gameObject.move(0, SPEED);

    }
}
