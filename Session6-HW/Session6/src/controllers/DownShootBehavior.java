package controllers;

import models.GameObject;

/**
 * Created by apple on 10/18/16.
 */

public class DownShootBehavior implements ShootBehavior {

    @Override
    public void doShoot(GameObject gameObject, ControllerManager bulletControllerManager) {

        EnemyBulletController enemyBulletController =
                EnemyBulletController.create(
                        gameObject.getX(), gameObject.getY(),
                        new DownFlyBehavior(0,2));

        bulletControllerManager.add(enemyBulletController);
    }
}
