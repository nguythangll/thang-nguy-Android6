import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by NgT on 10/2/2016.
 */
public class GameWindow extends Frame implements Runnable {

    private static final int PLANE1_WIDTH = 70;
    private static final int PLANE1_HEIGHT = 51;
    private static final int APP_WIDTH = 800;
    private static final int APP_HEIGHT = 600;



    Image backgroundImage = null;
    Image backBufferImage;

    Plane plane1;
    Plane plane2;
    Plane background;
    Bullet bullet;

    private int plane3X = APP_WIDTH / 2 - PLANE1_HEIGHT / 2;
    private int plane3Y = APP_HEIGHT - PLANE1_WIDTH;
    private int plane4X = APP_WIDTH / 2 - 70 / 2;
    private int plane4Y = APP_HEIGHT - (62 * 2);

    public GameWindow() {
        backBufferImage = new BufferedImage(APP_WIDTH, APP_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        try {
            plane1 = new Plane(plane3X, plane3Y, ImageIO.read(
                    new File("resources/plane3.png")));
            plane2 = new Plane(plane4X, plane4Y, ImageIO.read(
                    new File("resources/plane4.png")));
            background = new Plane(0, 0, ImageIO.read(
                    new File("resources/background.png")));
            bullet = new Bullet(368, 250 - 30,
                    ImageIO.read(new File("resources/bullet.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setVisible(true);
        this.setSize(APP_WIDTH, APP_HEIGHT);

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {
                System.out.println("windowOpened");
            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.out.println("windowClosing");
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {
                System.out.println("windowClosed");
            }

            @Override
            public void windowIconified(WindowEvent windowEvent) {
                System.out.println("windowIconified");
            }

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {
                System.out.println("windowDeiconified");
            }

            @Override
            public void windowActivated(WindowEvent windowEvent) {
                System.out.println("windowActivated");
            }

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {
                System.out.println("windowDeactivated");
            }
        });
        this.setLocationRelativeTo(null);
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                plane1.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });
        //MouseListener create new plane control by mouse.

        this.addMouseListener(new MouseListener() {


            @Override
            public void mouseClicked(MouseEvent e) {

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
        });
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                plane2.mouseMoved(e);
            }
        });
        try {
            backgroundImage = ImageIO.read(
                    new File("resources/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

    }

    @Override
    public void update(Graphics g) {

        Graphics backBufferGraphics = backBufferImage.getGraphics();

        backBufferGraphics.drawImage(background.getImage(), 0, 0, APP_WIDTH, APP_HEIGHT, null);

        plane1.drawImage(backBufferGraphics);
        plane2.drawImage(backBufferGraphics);
        bullet.drawImage(backBufferGraphics);

        g.drawImage(backBufferImage, 0, 0, APP_WIDTH, APP_HEIGHT, null);

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(17);
                bullet.fly();
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Back Buffer : ban chat la image
     * Font Buffer : cai user nhin thay
     */


    /**
     *  Tao class bullet de cho may bay ban ra dan
     *  1 - ban ra mot vien moi lan an space hoat chuot trai
     *  2 - ban ra nhieu vien
     */
}
