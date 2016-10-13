package controllers;

import models.Bullet;
import models.EnemyBullet;
import models.GameObject;
import utils.Utils;
import views.GameView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * Created by asus on 10/9/2016.
 */
public class PlaneController extends SingleController {


    public static final int SPEED = 5;
    private int dx;
    private int dy;
    public static Vector<BulletController> bulletControllers;
    private int SHOOT_DURATION = 20;

    public PlaneController(GameObject gameObject, GameView gameView) {
        super(gameObject, gameView);
        bulletControllers = new Vector<>();
    }


    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed");
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
                // Create BulletController (Model, View)
                creatBullet();
                break;

        }
    }

    public void keyReleased(KeyEvent e) {

        System.out.println("keyReleased");
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

    public void mouseClicked(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1){
            creatBullet();
        }
    }

    public void run() {
        //update model
        gameObject.move(dx, dy);

        for (BulletController bulletController : bulletControllers) {
            bulletController.run();
        }
    }

    public void creatBullet() {
        BulletController bulletController = new BulletController(
                new Bullet(gameObject.getMiddleX() - 5, gameObject.getY() - Bullet.BULLET_WIDTH / 2 - 10),
                new GameView(Utils.loadImageFromRes("bullet.png"))
        );
        bulletControllers.add(bulletController);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        for (BulletController bulletController : bulletControllers) {
            bulletController.draw(g);
        }
    }

    public void mouseMoved(MouseEvent e) {
        gameObject.moveTo(
                e.getX() - (gameObject.getWidth() / 2),
                e.getY() - (gameObject.getHeight() / 2)
        );
    }

}
