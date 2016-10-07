import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by NgT on 10/4/2016.
 */
public class Plane {
    private int x;
    private int y;
    private Image image;
    public static final int PLANE2_WIDTH = 70;
    private static final int PLANE2_HEIGHT = 62;

    public Plane(int x, int y, Image image) {
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

    public Image getImage() {
        return image;
    }


    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                x += 10;
                break;
            case KeyEvent.VK_LEFT:
                x -= 10;
                break;
            case KeyEvent.VK_UP:
                y -= 10;
                break;
            case KeyEvent.VK_DOWN:
                y += 10;
                break;
        }
    }
    public void mouseMoved(MouseEvent e) {
        if (e.getX() != x && e.getY() != y) {
            x = e.getX() - PLANE2_WIDTH / 2;
            y = e.getY() - PLANE2_HEIGHT / 2;
        }

    }

    public  void drawImage(Graphics g){
        g.drawImage(image, x, y, PLANE2_WIDTH, PLANE2_HEIGHT, null);
    }

}
