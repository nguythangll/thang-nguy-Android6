package controllers;

import models.Bullet;
import models.GameConfig;
import models.Plane;
import utils.Utils;
import views.GameView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by asus on 10/9/2016.
 */
public class PlaneController extends SingleController implements Contactable {
    private static final int SHOOT_DURATION = 20;

    private int dx;
    private int dy;
    public static final int SPEED = 10;
    private int numberBullet = 0;
    private ControllerManager bulletControllers;

    private PlaneController(Plane gameObject, GameView gameView) {
        super(gameObject, gameView);
        bulletControllers = new ControllerManager();
        CollisionPool.instance.register(this);
    }

    public void getHit(int damage) {
        ((Plane) gameObject).decreaseHP(damage);
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                dx = SPEED;
                break;
            case KeyEvent.VK_LEFT:
                dx = -SPEED;
                break;
            case KeyEvent.VK_UP:
                dy = -SPEED;
                break;
            case KeyEvent.VK_DOWN:
                dy = SPEED;
                break;
            case KeyEvent.VK_SPACE:
                createBullet();
                break;
        }
    }

    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_LEFT:
                dx = 0;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                dy = 0;
                break;

        }
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            createBullet();
        }
    }

    @Override
    public void run() {
        if (gameObject.getX() >= GameConfig.instance.getScreenWidth()) {
            gameObject.moveTo(-Plane.PLANE_WIDTH + 10, gameObject.getY());
        }

        if (gameObject.getX() <= -Plane.PLANE_WIDTH) {
            gameObject.moveTo(GameConfig.instance.getScreenWidth() - 10, gameObject.getY());
        }
        gameObject.move(dx, dy);
        bulletControllers.run();
    }

    private void createBullet() {
        if (numberBullet == 0) {
            BulletController bulletController = new BulletController(
                    new Bullet(gameObject.getMiddleX(), gameObject.getY()),
                    new GameView(Utils.loadImageFromRes("bullet.png"))
            );
            bulletControllers.add(bulletController);
        } else if(numberBullet == 1){
            BulletController doubleBulletController = new BulletController(
                    new Bullet(gameObject.getMiddleX(), gameObject.getY()),
                    new GameView(Utils.loadImageFromRes("bullet-double.png"))
            );
            bulletControllers.add(doubleBulletController);
        }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        bulletControllers.draw(g);
    }


    public void mouseMoved(MouseEvent e) {
        gameObject.moveTo(e.getX() - (gameObject.getWidth() / 2),
                e.getY() - (gameObject.getHeight() / 2));
    }

    public final static PlaneController planeController = new PlaneController(
            new Plane(GameConfig.instance.getScreenWidth() / 2, GameConfig.instance.getScreenHeight() - Plane.PLANE_HEIGHT),
            new GameView(Utils.loadImageFromRes("plane3.png"))
    );

    public final static PlaneController planeController2 = new PlaneController(
            new Plane(GameConfig.instance.getScreenWidth() / 2, GameConfig.instance.getScreenHeight() - Plane.PLANE_HEIGHT),
            new GameView(Utils.loadImageFromRes("plane4.png"))
    );

    @Override
    public void onCollide(Contactable contactable) {

    }
}
