import java.awt.*;

/**
 * Created by asus on 10/4/16.
 */
public class Bullet {
    public static final int BULLET_WIDTH = 13;
    public static final int BULLET_HEIGHT = 30;
    public static final int SPEED1 = 3;
    public static final int SPEED2 = 2;
    private boolean isAlive = true;

    private int x;
    private int y;
    private Image image;

    public void setAlive(boolean alive) {
        isAlive = alive;
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

    public Bullet(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }


    public void drawImage(Graphics g) {
        g.drawImage(image, x, y, BULLET_WIDTH, BULLET_HEIGHT, null);
    }


    public void fly() {
        y -= SPEED1;
    }
    public void fly2() {
        y += SPEED2;

    }
}
