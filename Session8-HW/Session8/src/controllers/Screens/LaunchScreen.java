package controllers.Screens;

import controllers.manager.GameScreen;
import controllers.manager.ScreenManager;
import models.GameConfig;
import utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by asus on 10/26/2016.
 */
public class LaunchScreen extends GameScreen {

    private Rectangle playButton;
    private Rectangle optionButton;
    private Rectangle quitButton;

    private final int WIDTH_BUTTON = 100;
    private final int HEIGHT_BUTTON = 25;

    private Image backgroundImage = null;
    public LaunchScreen(ScreenManager screenManager) {
        super(screenManager);
        backgroundImage = Utils.loadImageFromRes("launch-game.png");

    }

    @Override
    public void run() {

    }

    @Override
    public void update(Graphics g) {

        g.drawImage(backgroundImage, 0 ,0 ,
                GameConfig.instance.getScreenWidth(),
                GameConfig.instance.getScreenHeight(),
                null);

    }

    @Override
    public void draw(Graphics graphics) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            this.screenManager.change(
                    new PlayGameScreen(screenManager),
                    true
            );
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
