package controllers;

import models.GameObject;
import views.GameView;

/**
 * Created by asus on 10/22/2016.
 */
public class DoubleBulletController extends SingleController implements Contactable{
    public DoubleBulletController(GameObject gameObject, GameView gameView) {
        super(gameObject, gameView);
        CollisionPool.instance.register(this);
    }

    @Override
    public void run() {
        gameObject.move(0, 3);
    }

    @Override
    public void onCollide(Contactable contactable) {
        if (contactable instanceof PlaneController) {
            this.destroy();
            ((PlaneController) contactable).doubleBullet = true;
        }
    }
}
