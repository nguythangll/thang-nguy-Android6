package controllers;

import models.EnemyPlane;
import utils.Utils;
import views.GameView;

/**
 * Created by asus on 10/11/2016.
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
            for (int i = 0; i < 5; i++) {
                int y = 60;
                int x = i * (EnemyPlane.ENEMY_PLANE_WIDTH + 50);

                EnemyPlaneController enemyPlaneController = new EnemyPlaneController(
                        new EnemyPlane(x,y),
                        new GameView(Utils.loadImageFromRes("plane1.png"))
                );
                singleControllers.add(enemyPlaneController);
            }
        }
        for (int i = 0 ; i < singleControllers.size(); i++){
            if (singleControllers.get(i).gameObject.getY() >= 400){
                singleControllers.remove(i);
            }

            for(int j = 0 ; j < PlaneController.bulletControllers.size(); j++){
                BulletController bl = PlaneController.bulletControllers.get(j);
                if(bl.gameObject.getY() <= singleControllers.get(i).gameObject.getY() + singleControllers.get(i).gameObject.getHeight()
                        && bl.gameObject.getX()>= singleControllers.get(i).gameObject.getX()
                        && bl.gameObject.getX()<= singleControllers.get(i).gameObject.getX() +singleControllers.get(i).gameObject.getWidth()){
                    singleControllers.remove(i);
                    PlaneController.bulletControllers.remove(j);
                }
            }
        }
    }
}
