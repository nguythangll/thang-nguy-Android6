package controllers;

import controllers.Screens.LaunchScreen;
import controllers.Screens.PlayGameScreen;
import controllers.manager.CollisionPool;
import controllers.manager.ControllerManager;
import controllers.manager.GameScreen;
import controllers.manager.ScreenManager;
import models.Bullet;
import models.GameConfig;
import models.Plane;
import utils.Utils;
import views.SingleDrawer;

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

    private boolean die = false;

    public boolean isDie() {
        return die;
    }

    public void setDie(boolean die) {
        this.die = die;
    }

    private ControllerManager bulletControllers;

    private PlaneController(Plane gameObject, SingleDrawer singleDrawer) {
        super(gameObject, singleDrawer);
        bulletControllers = new ControllerManager();
        CollisionPool.instance.register(this);
    }

    public void getHit(int damage) {
        ((Plane)gameObject).decreaseHP(damage);
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
        if(e.getButton() == MouseEvent.BUTTON1) {
            createBullet();
        }
    }

    @Override
    public void run() {
        gameObject.move(dx, dy);
        bulletControllers.run();
    }


    //    public void run(){
//        // update model
//        gameObject.move(dx, dy);
//
//        for(BulletController bulletController : bulletControllers) {
//            bulletController.run();
//        }
//    }

    private void createBullet() {
        BulletController bulletController = new BulletController(
                new Bullet(gameObject.getMiddleX() - Bullet.BULLET_WIDTH/2, gameObject.getY() - Bullet.BULLET_HEIGHT/2),
                new SingleDrawer(Utils.loadImageFromRes("bullet.png"))
        );
        new Thread(() -> Utils.playSound("resources/Laser_Shoot3.wav", false)).start();
        bulletControllers.add(bulletController);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        bulletControllers.draw(g);
    }

    //    public void draw(Graphics g){
//        planeView.drawImage(g, plane);
//        for(BulletController bulletController : bulletControllers) {
//            bulletController.draw(g);
//        }
//    }

    public void mouseMoved(MouseEvent e) {
        gameObject.moveTo( e.getX() - (gameObject.getWidth() / 2),
                e.getY() - (gameObject.getHeight() / 2));
    }

    public final static PlaneController planeController = new PlaneController(
                new Plane(GameConfig.instance.getScreenWidth() / 2, GameConfig.instance.getScreenHeight() - Plane.PLANE_HEIGHT),
                new SingleDrawer(Utils.loadImageFromRes("plane3.png"))
            );

    public final static PlaneController planeController2 = new PlaneController(
                new Plane(GameConfig.instance.getScreenWidth()  / 2, GameConfig.instance.getScreenHeight() - Plane.PLANE_HEIGHT),
                new SingleDrawer(Utils.loadImageFromRes("plane4.png"))
            );

    @Override
    public void onCollide(Contactable contactable) {
        if(contactable instanceof EnemyBulletController) {
            setDie(true);
        }
    }
}
