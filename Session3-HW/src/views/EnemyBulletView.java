package views;

import models.EnemyBullet;

import java.awt.*;

/**
 * Created by asus on 10/10/2016.
 */
public class EnemyBulletView {
    private Image image;

    public Image getImage() {
        return image;
    }

    public EnemyBulletView(Image image) {
        this.image = image;
    }

    public void drawImage(Graphics g, EnemyBullet enemyBullet) {
        g.drawImage(image, enemyBullet.getX(), enemyBullet.getY(), enemyBullet.BULLET_WIDTH, enemyBullet.BULLET_HEIGHT, null);
    }
}
