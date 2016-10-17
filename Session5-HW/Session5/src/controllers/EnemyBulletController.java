package controllers;

import models.GameObject;
import views.GameView;

/**
 * Created by apple on 10/16/16.
 */
public class EnemyBulletController extends SingleController implements Contactable{
    private static final int SPEED = 4;

    public EnemyBulletController(GameObject gameObject, GameView gameView) {
        super(gameObject, gameView);
        CollisionPool.instance.register(this);
    }

    @Override
    public void run() {
        super.run();
        gameObject.move(0, SPEED);

    }
    public void onCollide(Contactable contactable) {
        if(contactable instanceof PlaneController) {
            this.getGameObject().changeHP(1);
        }
    }
}
