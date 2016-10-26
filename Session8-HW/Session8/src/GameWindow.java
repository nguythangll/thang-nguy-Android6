import controllers.NotificationCenter;
import controllers.Screens.LaunchScreen;
import controllers.manager.*;
import models.GameConfig;
import utils.Utils;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Stack;

/**
 * Created by apple on 10/2/16.
 */
public class GameWindow extends Frame implements Runnable,ScreenManager {


    Image backBufferImage;

    int backgroundWidth;
    int backgroundHeight;
    private Stack<GameScreen> screenStack;


    private GameScreen currentGameScreen;

    public GameWindow() {
        screenStack = new Stack<>();

        backgroundWidth = GameConfig.instance.getScreenWidth();
        backgroundHeight = GameConfig.instance.getScreenHeight();

        backBufferImage = new BufferedImage(backgroundWidth,
                backgroundHeight, BufferedImage.TYPE_INT_ARGB);

        this.setVisible(true);

        this.setSize(GameConfig.instance.getScreenWidth(),
                GameConfig.instance.getScreenHeight());
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("windowOpened");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("windowClosing");
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("windowClosed");
            }

            @Override
            public void windowIconified(WindowEvent e) {
                System.out.println("windowIconified");
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                System.out.println("windowDeiconified");
            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        change(new LaunchScreen(this), false);
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                    back();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        new Thread(() -> Utils.playSound("resources/sound-omfg.wav", true)).start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("draw background image");
    }

    @Override
    public void update(Graphics g) {

        Graphics backBufferGraphics = backBufferImage.getGraphics();

        currentGameScreen.update(backBufferGraphics);

        g.drawImage(backBufferImage,
                0, 0,
                backgroundWidth, backgroundHeight, null);
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(GameConfig
                        .instance
                        .getThreadDelayInMiliseconds());
                repaint();
                currentGameScreen.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void change(GameScreen newGameScreen, boolean addToBackStack) {
        if (currentGameScreen != null){
            this.detach(currentGameScreen);
        }
        if (addToBackStack && currentGameScreen != null){
            screenStack.push(currentGameScreen);

        }
        this.attach(newGameScreen);
    }
    public void back(){
        if(screenStack.size() > 0){
            GameScreen newGameScreen  = screenStack.pop();
            detach(currentGameScreen);
            attach(newGameScreen );
            NotificationCenter.instance.reset();
        }
    }
    public void attach(GameScreen newGameScreen){
        this.currentGameScreen = newGameScreen;
        this.addMouseListener(newGameScreen);
        this.addMouseMotionListener(newGameScreen);
        this.addKeyListener(newGameScreen);
    }
    public void detach(GameScreen oldGameScreen){
        this.removeKeyListener(oldGameScreen);
        this.removeMouseListener(oldGameScreen);
        this.removeMouseMotionListener(oldGameScreen);
    }
}
