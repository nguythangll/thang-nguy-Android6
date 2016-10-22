package controllers;

import models.GameConfig;
import models.GameObject;

/**
 * Created by asus on 10/22/2016.
 */
public class RightLeftFlyBehavior implements FlyBehavior {
    private int speed;
    private boolean changeDirection;

    public RightLeftFlyBehavior(int speed) {
        this.speed = speed;
        this.changeDirection = true;
    }

    @Override
    public void doFly(GameObject gameObject) {
        if (changeDirection) {
            gameObject.move(speed, speed);
            if (gameObject.getX() >= GameConfig.instance.getScreenWidth() - gameObject.getWidth()) {
                changeDirection = false;
            }
        }
        else {
            gameObject.move(-speed, speed);
            if (gameObject.getX() <= 0) {
                changeDirection = true;
            }
        }
    }



}
