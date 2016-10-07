import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by asus on 10/4/16.
 */
public class Plane implements KeyListener,MouseListener{
    public static final int PLANE_WIDTH = 50;
    public static final int PLANE_HEIGHT = 35;
    private int x;
    private int y;
    private Image image;

    private Set<Integer> keySet = new HashSet<>();
    public Plane(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed");
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                GameWindow.createBullet(x + PLANE_WIDTH/2 - Bullet.BULLET_WIDTH / 2, y - Bullet.BULLET_HEIGHT);

                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("Key right");
                this.x += 10;
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

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        x = e.getX() - (PLANE_WIDTH / 2);
        y = e.getY() - (PLANE_HEIGHT / 2);
    }

    public void drawImage(Graphics g) {
        g.drawImage(image, x, y, PLANE_WIDTH, PLANE_HEIGHT, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GameWindow.createBullet(x + PLANE_WIDTH/2 - Bullet.BULLET_WIDTH / 2, y - Bullet.BULLET_HEIGHT);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
