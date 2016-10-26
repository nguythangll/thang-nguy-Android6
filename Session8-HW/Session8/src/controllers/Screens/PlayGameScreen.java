package controllers.Screens;

import controllers.GiftController;
import controllers.PlaneController;
import controllers.manager.*;
import models.GameConfig;
import utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by asus on 10/26/2016.
 */
public class PlayGameScreen extends GameScreen {
    Image backgroundImage = null;
    private PlaneController planeController;
    private PlaneController planeController2;

    private ControllerManager controllerManager;

    public PlayGameScreen(ScreenManager screenManager) {
        super(screenManager);
        controllerManager = new ControllerManager();

        planeController = PlaneController.planeController;
        planeController2 = PlaneController.planeController2;

        controllerManager.add(planeController);
        controllerManager.add(planeController2);
        controllerManager.add(new EnemyPlaneControllerManager());
        controllerManager.add(CollisionPool.instance);
        controllerManager.add(ControllerManager.explosionManager);
        controllerManager.add(GiftController.create(GameConfig.instance.getScreenWidth()/2 , 0));
        backgroundImage = Utils.loadImageFromRes("background.png");
    }

    @Override
    public void run() {
        controllerManager.run();
        if(planeController.isDie() == true || planeController2.isDie() == true){
            this.screenManager.change(
                    new GameOverScreen(screenManager),
                    true
            );
        }
    }

    @Override
    public void update(Graphics graphics) {
        graphics.drawImage(backgroundImage,
                0, 0,
                GameConfig.instance.getScreenWidth(), GameConfig.instance.getScreenHeight(), null);

        controllerManager.draw(graphics);
    }

    @Override
    public void draw(Graphics graphics) {

    }

    //-----------------------------keyListener---------------------------
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        planeController.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        planeController.keyReleased(e);
    }
    //----------------------MouseListener--------------------
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
    //----------------MouseMotion---------------------
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        planeController2.mouseMoved(e);
    }
}
