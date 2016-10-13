package controllers;

import models.EnemyBullet;
import models.GameObject;
import utils.Utils;
import views.GameView;

import java.awt.*;
import java.util.Vector;

/**
 * Created by asus on 10/11/2016.
 */
public class EnemyPlaneController extends SingleController {
    public static final int SPEED = 2;
    public static final int CD = 50;
    private int i = 50;
    private Vector<EnemyBulletController> enemyBulletControllers;

    public EnemyPlaneController(GameObject gameObject, GameView gameView) {
        super(gameObject, gameView);
        enemyBulletControllers = new Vector<>();
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        for (EnemyBulletController enemyBulletController : enemyBulletControllers) {
            enemyBulletController.draw(g);
        }
    }

    @Override
    public void run() {
        gameObject.move(0, SPEED);
        if (i >= CD) {
            i = 0;
            EnemyBulletController enemyBulletController = new EnemyBulletController(
                    new EnemyBullet(
                            gameObject.getMiddleX() - EnemyBullet.BULLET_WIDTH / 2,
                            gameObject.getHeight() + gameObject.getY()
                    ),
                    new GameView(Utils.loadImageFromRes("enemy_bullet.png"))
            );
            enemyBulletControllers.add(enemyBulletController);
        }
        i++;
        for (int i = 0; i < enemyBulletControllers.size(); i++) {
            enemyBulletControllers.get(i).run();
            if (enemyBulletControllers.get(i).gameObject.getY() >= 400) {
                enemyBulletControllers.remove(i);
            }
        }
        System.out.println(enemyBulletControllers.size());
    }


}
