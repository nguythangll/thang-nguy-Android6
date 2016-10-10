package controllers;

import models.Bullet;
import views.BulletView;

import java.awt.*;


/**
 * Created by asus on 10/9/2016.
 */
public class BulletController {

    public static final int SPEED = 10;

    private int x;
    private int y;

    private Bullet bullet;
    private BulletView bulletView;


    public BulletController(Bullet bullet, BulletView bulletView) {
        this.bullet = bullet;
        this.bulletView = bulletView;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void  run(){
        //update model
      bullet.fly();
    }

    public void draw(Graphics g){
        bulletView.drawImage(g, bullet);


    }
}
