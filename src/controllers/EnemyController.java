package controllers;

import collision.Collision;
import controllers.strategies.MoveBehavior;
import models.EnemyModel;
import program.GameManager;
import utils.Utils;
import views.EnemyTankView;
import views.EnemyView;

import java.awt.Image;


/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class EnemyController extends GameController implements Collision {

    public static final int DELAY_SHOOT = 3000;
    public static int DELAY_ADD_ENEMY = 1500;

    private long lastTimeAddBullet;
    private MoveBehavior moveBehavior;

    public EnemyController(EnemyModel model, EnemyView view) {
        super(view, model);
        CollisionController.instance.add(this);
        GameManager.gameControllers.add(this);
    }

    public EnemyController(int x, int y, Image image, int hp, int score, int speed) {
        this(new EnemyModel(
                        x,
                        y,
                        image.getWidth(null),
                        image.getHeight(null),
                        hp,
                        score,
                        speed),
                new EnemyView(image)
        );
    }

    @Override
    public void run() {
        EnemyModel model = (EnemyModel) this.model;
        if (!model.isDead()) {
            shoot();
            moveBehavior.move(model);
        } else if (!((EnemyView) view).explode()) {
            model.setAlive(false);
        }
    }

    private void shoot() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTimeAddBullet > DELAY_SHOOT) {
            Image image = Utils.loadImageFromRes("bullet-round.png");
            GameController enemyBullet = new EnemyBulletController(
                    (model.getWidth() - image.getWidth(null)) / 2 + model.getX(),
                    model.getHeight() + model.getY(),
                    image);
            GameManager.gameControllers.add(enemyBullet);
            lastTimeAddBullet = currentTime;
        }
    }

    @Override
    public void collide(Collision other) {
        if (other instanceof PlayerBulletController || other instanceof PlayerRocketController) {
            EnemyModel model = (EnemyModel) this.model;
            model.setHp(model.getHp() - 1);
            if (model.getHp() == 0) {
                if (!(view instanceof EnemyTankView))
                    model.setDead(true);
                CollisionController.instance.remove(this);
            }
        }
    }

    public void setMoveBehavior(MoveBehavior moveBehavior) {
        this.moveBehavior = moveBehavior;
    }
}
