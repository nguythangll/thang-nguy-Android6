package controllers;

import models.EnemyPlane;
import models.GameConfig;
import models.GameObject;
import utils.Utils;
import views.GameView;

import java.awt.*;

/**
 * Created by apple on 10/11/16.
 */
public class EnemyPlaneController extends SingleController implements Contactable {

    private static final int SPEED = 1;

    private ControllerManager butletControllerManager;

    private int count = 0;

    private FlyBehavior flyBehavior;
    private ShootBehavior shootBehavior;

    public EnemyPlaneController(GameObject gameObject, GameView gameView,
                                FlyBehavior flyBehavior,
                                ShootBehavior shootBehavior) {
        super(gameObject, gameView);
        this.flyBehavior = flyBehavior;
        this.shootBehavior = shootBehavior;

        butletControllerManager = new ControllerManager();
        CollisionPool.instance.register(this);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        butletControllerManager.draw(g);
    }




    @Override
    public void run() {
        // Fly
        // Needs game object
        // gameObject.move(0, SPEED);
        if(flyBehavior != null) {
            this.flyBehavior.doFly(this.gameObject);
        }
        // End Fly
        // Shot
        // Needs: count, butletControllerManager
        count++;
        if (GameConfig.instance.getSeconds(count) > 1   ) {
            count = 0;
            if (shootBehavior != null) {
                shootBehavior.doShoot(this.gameObject, this.butletControllerManager);
            }
        }
        // End Shot

        butletControllerManager.run();
    }

    @Override
    public void onCollide(Contactable contactable) {
        if(contactable instanceof BulletController) {
            ((BulletController) contactable).destroy();
        }
    }

    public static EnemyPlaneController create(int x, int y, EnemyPlaneType enemyPlaneType){
        Image image = null;
        FlyBehavior flyBehavior = null;
        ShootBehavior shootBehavior = null;

        if (enemyPlaneType == EnemyPlaneType.GRAY) {
            image = Utils.loadImageFromRes("plane1.png");
            flyBehavior =  new DownFlyBehavior(0,1);
            shootBehavior = new DownShootBehavior();
        } else if(enemyPlaneType == EnemyPlaneType.RED) {
            image = Utils.loadImageFromRes("enemy_plane_yellow_1.png");
            flyBehavior =  new DownRightFlyBehavior();
            shootBehavior = new TrackShootBehavior();
        }
        return new EnemyPlaneController(
                new EnemyPlane(x, y),
                new GameView(image),
                flyBehavior,
                shootBehavior);
    }
}
