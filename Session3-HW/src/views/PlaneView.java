package views;

import models.Plane;

import java.awt.*;

/**
 * Created by asus on 10/9/2016.
 */
public class PlaneView {
    private Image image;

    public PlaneView(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
    public void drawImage(Graphics g, Plane plane) {
        g.drawImage(image, plane.getX(), plane.getY(), Plane.PLANE_WIDTH, Plane.PLANE_HEIGHT, null);
    }
}
