import java.awt.*;

/**
 * Created by asus on 10/8/2016.
 */
public class PlaneEnemy {

    public static final int PLANE_WIDTH = 50;
    public static final int PLANE_HEIGHT = 35;
    public static final int SPEED = 1;
    private boolean isAlive = true;
    private int x;
    private int y;
    private Image image;
    private int delay = 100;

    public PlaneEnemy(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean alive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }


    public void drawImage(Graphics g) {
        g.drawImage(image, x, y, PLANE_WIDTH, PLANE_HEIGHT, null);
    }


    public void fly() {
        y += SPEED;
        delay++;
        if(delay >= 100){
            GameWindow.createBulletEnemy(x + PLANE_WIDTH/2 - Bullet.BULLET_WIDTH / 2, y + Bullet.BULLET_HEIGHT );
            delay = 0;
        }
    }
}
