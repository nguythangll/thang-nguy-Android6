package controllers.manager;

import controllers.EnemyPlaneController;
import controllers.EnemyPlaneType;
import models.EnemyPlane;

/**
 * Created by apple on 10/11/16.
 */
public class EnemyPlaneControllerManager extends ControllerManager {
    public static final int delay = 50;
    private int count = 50;
    public EnemyPlaneControllerManager() {
        super();


    }

    @Override
    public void run() {
        super.run();

        count++;
        if(count >= delay){
            count=0;
            for (int i = 0; i < 6; i++) {
                int y = 60;
                int x = i * (EnemyPlane.ENEMY_PLANE_WIDTH + 5);
                EnemyPlaneController enemyPlaneController =
                        EnemyPlaneController.create(x, y, EnemyPlaneType.GRAY);
                baseControllers.add(enemyPlaneController);
            }

            for (int i = 0; i < 5; i++) {
                int y = 100;
                int x = i * (EnemyPlane.ENEMY_PLANE_WIDTH + 5);
                EnemyPlaneController enemyPlaneController =
                        EnemyPlaneController.create(x, y, EnemyPlaneType.RED);
                baseControllers.add(enemyPlaneController);
            }

        }

    }
}
