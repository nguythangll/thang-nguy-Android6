package controllers;

import models.GameObject;

/**
 * Created by apple on 10/18/16.
 */
public class DownFlyBehavior implements FlyBehavior {

    private int speedX;
    private int speedY;

    public DownFlyBehavior(int speedX, int speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
    }

    @Override
    public void doFly(GameObject gameObject) {
        gameObject.move(speedX, speedY);
    }
}
