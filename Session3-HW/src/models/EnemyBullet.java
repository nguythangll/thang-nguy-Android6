package models;



/**
 * Created by asus on 10/9/16.
 */
public class EnemyBullet {
    public static final int BULLET_WIDTH = 13;
    public static final int BULLET_HEIGHT = 30;
    public static final int SPEED = 8;

    private int x;
    private int y;

    private boolean isAlive = true;

    public EnemyBullet(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public boolean alive() {
        return isAlive;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void fly() {
        this.y += SPEED;

    }

    public void checkAlive( int y){
        if(y == 400){
            isAlive = false;
        }
    }

}
