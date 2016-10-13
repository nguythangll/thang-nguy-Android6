package views;

import models.Bullet;
import models.GameObject;

import java.awt.*;

/**
 * Created by asus on 10/11/2016.
 */
public class GameView {
    private Image image;

    public Image getImage() {
        return image;
    }

    public GameView(Image image) {
        this.image = image;
    }

    public void drawImage(Graphics g, GameObject gameObject) {
        g.drawImage(
                image,
                gameObject.getX(),
                gameObject.getY(),
                gameObject.getWidth(),
                gameObject.getHeight(),
                null);
    }
}
