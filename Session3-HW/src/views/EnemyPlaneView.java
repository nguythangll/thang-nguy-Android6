package views;

import models.EnemyPlane;

import java.awt.*;

/**
 * Created by asus on 10/10/2016.
 */
public class EnemyPlaneView {
    private Image image;

    public EnemyPlaneView(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void drawImage(Graphics g, EnemyPlane enemyPlane) {
        g.drawImage(image, enemyPlane.getX(), enemyPlane.getY(), enemyPlane.ENEMY_PLANE_WIDTH, enemyPlane.ENEMY_PLANE_HEIGHT, null);
    }
}
