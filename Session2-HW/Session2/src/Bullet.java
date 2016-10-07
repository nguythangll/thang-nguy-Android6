import java.awt.*;

/**
 * Created by asus on 10/4/16.
 */
public class Bullet {
    public static final int BULLET_WIDTH = 13;
    public static final int BULLET_HEIGHT = 30;
    public static final int SPEED = 3;

    private int x;
    private int y;
    private Image image;



    public Bullet(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }


    public void drawImage(Graphics g) {
        g.drawImage(image, x, y, BULLET_WIDTH, BULLET_HEIGHT, null);
    }


    public void fly() {
        y -= SPEED;
    }
}
