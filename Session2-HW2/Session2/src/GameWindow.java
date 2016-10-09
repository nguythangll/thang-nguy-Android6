import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by asus on 10/02/16.
 */
public class GameWindow extends Frame implements Runnable {

    private static final int BACKGROUND_WIDTH = 600;
    private static final int BACKGROUND_HEIGHT = 400;
    static ArrayList<Bullet> bullet = new ArrayList<>();
    static ArrayList<BulletEnemy> bulletEnemy = new ArrayList<>();
    Image backgroundImage = null;
    Image backBufferImage;
    Plane plane;
    Plane plane2;

    ArrayList<PlaneEnemy> planeEnemy = new ArrayList<>();
    Thread thread;
    private int respawnTime = 100;
    private Random in = new Random();

    public GameWindow() {

        backBufferImage = new BufferedImage(BACKGROUND_WIDTH,
                BACKGROUND_HEIGHT, BufferedImage.TYPE_INT_ARGB);

        try {
            plane = new Plane(350, 250,
                    ImageIO.read(new File("resources/plane3.png")));
            plane2 = new Plane(450, 250,
                    ImageIO.read(new File("resources/plane4.png")));


        } catch (IOException e) {
            e.printStackTrace();
        }

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
                plane2.mouseMoved(e);
            }
        });

        this.addKeyListener(plane);
        this.addMouseListener(plane2);

        try {
            backgroundImage = ImageIO.read(
                    new File("resources/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        thread = new Thread(this);
        thread.start();
    }

    public static void createBullet(int x, int y) {
        try {
            bullet.add(new Bullet(x, y,
                    ImageIO.read(new File("resources/bullet.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createBulletEnemy(int x, int y) {
        try {
            bulletEnemy.add(new BulletEnemy(x, y,
                    ImageIO.read(new File("resources/bulletEnemy.png"))));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    public void update(Graphics g) {

        Graphics backBufferGraphics = backBufferImage.getGraphics();

        backBufferGraphics.drawImage(backgroundImage,
                0, 0,
                BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null);

        plane.drawImage(backBufferGraphics);
//        plane2.drawImage(backBufferGraphics);
        for (PlaneEnemy enemy : planeEnemy) {
            enemy.drawImage(backBufferGraphics);
        }

        if (bullet != null) {
            for (Bullet b : bullet)
                b.drawImage(backBufferGraphics);

        }
        if (bulletEnemy != null) {
            for (BulletEnemy b : bulletEnemy)
                b.drawImage(backBufferGraphics);

        }

        g.drawImage(backBufferImage,
                0, 0,
                BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(17);
                if (respawnTime >= 100) {
                    respawnTime = 0;
                    try {
                        planeEnemy.add(new PlaneEnemy(80 * in.nextInt(7), 0,
                                ImageIO.read(new File("resources/enemy_plane_white_3.png"))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                if (bullet != null) {
                    for (Bullet b : bullet)
                        b.fly();
                }

                try {
                    for (int i = 0; i < planeEnemy.size(); i++) {
                        if (planeEnemy.get(i).getY() == BACKGROUND_HEIGHT) {
                            planeEnemy.get(i).setAlive(false);
                        }
                        if (planeEnemy.get(i).alive()) {
                            planeEnemy.get(i).fly();
                        } else {
                            planeEnemy.remove(i);
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }

                System.out.println(planeEnemy.size());
                try {
                    for (int i = 0; i < bulletEnemy.size(); i++) {
                        if (bulletEnemy.get(i).getY() == BACKGROUND_HEIGHT) {
                            bulletEnemy.get(i).setAlive(false);
                        }
                        if (bulletEnemy.get(i).alive()) {
                            bulletEnemy.get(i).fly2();
                        } else {
                            bulletEnemy.remove(i);
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }

                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            respawnTime++;
        }
    }
}
