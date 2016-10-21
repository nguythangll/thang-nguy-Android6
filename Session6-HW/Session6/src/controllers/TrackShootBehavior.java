package controllers;

import models.GameObject;

/**
 * Created by asus on 10/21/2016.
 */
public class TrackShootBehavior implements ShootBehavior {
    private static final int BULLET_SPEED = 3;
    private int speedX;
    private int speedY;


    @Override
    public void doShoot(GameObject gameObject, ControllerManager bulletControllerManager) {

        int dx = PlaneController.planeController.getGameObject().getX() -
                gameObject.getX();
        int dy = PlaneController.planeController.getGameObject().getY() -
                gameObject.getY();

        if (dy > 0) {
            double ratio = Math.sqrt(dx * dx + dy * dy) / BULLET_SPEED;

            speedY = (int) (dy / ratio);
            speedX = (int) (dx / ratio);

        }


        EnemyBulletController enemyBulletController =
                EnemyBulletController.create(
                        gameObject.getX(), gameObject.getY(),
                        new DownFlyBehavior(speedX, speedY));

        bulletControllerManager.add(enemyBulletController);
    }
}
