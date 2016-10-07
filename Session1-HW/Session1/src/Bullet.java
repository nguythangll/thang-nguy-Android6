import java.awt.*;

/**
 * Created by asus on 10/6/2016.
 */
public class Bullet {
    private static final int BULLET_WIDTH = 13;
    private static final int BULLET_HEIGHT = 30;
    private static final int SPEED = 3;

    private int x;
    private int y;
    private Image image;


    public Bullet(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }


    public void drawImage(Graphics g) {


        g.drawImage(image, x, y,BULLET_WIDTH, BULLET_HEIGHT, null);


    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void fly() {

        y -= SPEED;
    }
}
