import controllers.EnemyBulletController;
import controllers.EnemyPlaneController;
import controllers.PlaneController;
import models.Bullet;
import models.EnemyBullet;
import models.EnemyPlane;
import models.Plane;
import utils.Utils;
import views.EnemyPlaneView;
import views.PlaneView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

/**
 * Created by asus on 10/2/16.
 */
public class GameWindow extends Frame implements Runnable {

    public static final int BACKGROUND_WIDTH = 600;
    public static final int BACKGROUND_HEIGHT = 400;
    private Random in = new Random();
    Image backgroundImage = null;
    Image backBufferImage;

    PlaneController planeController;
    PlaneController planeController2;
    Vector<Bullet> bulletVector;
    Vector<EnemyPlaneController> enemyPlaneControllerVector;
    Vector<EnemyBulletController> enemyBulletControllerVector;
    private int respawnTime = 50;

    public GameWindow() {
        planeController = new PlaneController(
                new Plane(BACKGROUND_WIDTH/2,  BACKGROUND_HEIGHT - Plane.PLANE_HEIGHT - 10),
                new PlaneView(Utils.loadImageFromRes("plane3.png")));
        planeController2 = new PlaneController(
                new Plane(BACKGROUND_WIDTH/2,  BACKGROUND_HEIGHT - Plane.PLANE_HEIGHT*2 - 10 ),
                new PlaneView(Utils.loadImageFromRes("plane4.png")));
        backBufferImage = new BufferedImage(BACKGROUND_WIDTH,
                BACKGROUND_HEIGHT, BufferedImage.TYPE_INT_ARGB);

        bulletVector = new Vector<>();
        enemyPlaneControllerVector = new Vector<>();
        enemyBulletControllerVector = new Vector<>();


        this.setVisible(true);
        this.setSize(BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
        this.setLocationRelativeTo(null);


        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("windowOpened");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("windowClosing");
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("windowClosed");
            }

            @Override
            public void windowIconified(WindowEvent e) {
                System.out.println("windowIconified");
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                System.out.println("windowDeiconified");
            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                planeController2.mouseMoved(e);
            }
        });

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("keyTyped");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("keyPressed");
                planeController.keyPressed(e);

            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("keyReleased");
                planeController.keyReleased(e);
            }

        });
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                planeController2.mouseClicked(e);
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

        try {
            backgroundImage = ImageIO.read (
                    new File("resources/background.png"));
            System.out.println("Loaded backgroundImage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("draw background image");
    }

    @Override
    public void update(Graphics g) {

        Graphics backBufferGraphics = backBufferImage.getGraphics();

        backBufferGraphics.drawImage(backgroundImage,
                0, 0,
                BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null);

        planeController.draw(backBufferGraphics);
        planeController2.draw(backBufferGraphics);

        for(EnemyPlaneController enemyPlaneController : enemyPlaneControllerVector) {
            enemyPlaneController.draw(backBufferGraphics);
        }

        g.drawImage(backBufferImage,
                0, 0,
                BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null);
    }

    int count = 0;

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(17);
                repaint();
                planeController.run();
                planeController2.run();

                if (respawnTime >= 50) {
                    respawnTime = 0;
                    int y = 0;
                    int x = in.nextInt(15) * (EnemyPlane.ENEMY_PLANE_WIDTH + 5);
                    EnemyPlaneController enemyPlaneController = new EnemyPlaneController(
                            new EnemyPlane(x, y),
                            new EnemyPlaneView(Utils.loadImageFromRes("plane1.png"))
                    );
                    try {
                        enemyPlaneControllerVector.add(enemyPlaneController);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


                count++;
                if(count >= 25) {
                    count = 0;
                    for (EnemyPlaneController enemyPlaneController : enemyPlaneControllerVector) {
                        enemyPlaneController.createEnemyBullet();
                    }
                }
                for (EnemyPlaneController enemyPlaneController : enemyPlaneControllerVector) {
                    enemyPlaneController.run();
                }

                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            respawnTime++;
        }
    }
}
