package controllers;

import models.EnemyBullet;
import models.EnemyPlane;
import utils.Utils;
import views.EnemyBulletView;
import views.EnemyPlaneView;

import java.awt.*;
import java.util.Vector;

/**
 * Created by asus on 10/10/2016.
 */
public class EnemyPlaneController {
    Vector<EnemyBulletController> enemyBulletControllerVector;
    private EnemyPlane enemyPlane;
    private EnemyPlaneView enemyPlaneView;

    public EnemyPlaneController(EnemyPlane enemyPlane, EnemyPlaneView enemyPlaneView) {
        this.enemyPlane = enemyPlane;
        this.enemyPlaneView = enemyPlaneView;
        enemyBulletControllerVector = new Vector<>();
    }

    public void createEnemyBullet() {
        EnemyBulletController enemyBulletController = new EnemyBulletController(
                new EnemyBullet(enemyPlane.getMiddleX(), enemyPlane.getBottom()),
                new EnemyBulletView(Utils.loadImageFromRes("enemy_bullet.png"))
        );
        enemyBulletControllerVector.add(enemyBulletController);
    }

    public void run() {
        enemyPlane.fly();
        for (EnemyBulletController enemyBulletController : enemyBulletControllerVector) {
            enemyBulletController.run();
        }

    }


    public void draw(Graphics g) {
        enemyPlaneView.drawImage(g, enemyPlane);
        for (EnemyBulletController enemyBulletController : enemyBulletControllerVector) {
            enemyBulletController.draw(g);
        }
    }
}
