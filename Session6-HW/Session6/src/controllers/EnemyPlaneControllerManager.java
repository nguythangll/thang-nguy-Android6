package controllers;

import models.EnemyPlane;
import utils.Utils;
import views.GameView;

/**
 * Created by apple on 10/11/16.
 */
public class EnemyPlaneControllerManager extends ControllerManager {

    public EnemyPlaneControllerManager() {
        super();

        for(int i = 0; i < 5; i++) {
            int y = 0;
            int x = i * (EnemyPlane.ENEMY_PLANE_WIDTH + 50);
            EnemyPlaneController enemyPlaneController =  EnemyPlaneController.create(x,y ,EnemyPlaneType.GRAY);
            baseControllers.add(enemyPlaneController);
        }

        for(int i = 0; i < 5; i++) {
            int y = 0;
            int x = i * (EnemyPlane.ENEMY_PLANE_WIDTH + 50);
            EnemyPlaneController enemyPlaneController = EnemyPlaneController.create(x,y ,EnemyPlaneType.RED);
            baseControllers.add(enemyPlaneController);
        }
    }
}
