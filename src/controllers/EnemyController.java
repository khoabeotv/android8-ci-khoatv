package controllers;

import collision.Collision;
import controllers.strategies.MoveBehavior;
import models.EnemyPlaneModel;
import program.GameManager;
import utils.Utils;
import views.EnemyPlaneView;

import java.awt.Image;


/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class EnemyController extends GameController implements Collision {

    public static final int DELAY_SHOOT = 3000;

    private long lastTimeAddBullet;
    private MoveBehavior moveBehavior;

    public EnemyController(EnemyPlaneModel model, EnemyPlaneView view) {
        super(view, model);
        CollisionController.instance.add(this);
    }

    public EnemyController(int x, int y, Image image, int hp) {
        this(new EnemyPlaneModel(
                        x,
                        y,
                        image.getWidth(null),
                        image.getHeight(null),
                        hp),
                new EnemyPlaneView(image)
        );
    }

    @Override
    public void run() {
        EnemyPlaneModel model = (EnemyPlaneModel) this.model;
        if (!model.isDead()) {
            shoot();
            moveBehavior.move(model);
        } else if (!((EnemyPlaneView) view).explode()) {
            GameManager.gameControllers.remove(this);
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
            EnemyPlaneModel model = (EnemyPlaneModel) this.model;
            model.setHp(model.getHp() - 1);
            if (model.getHp() == 0) {
                model.setDead(true);
                CollisionController.instance.remove(this);
            }
        }
    }

    public void setMoveBehavior(MoveBehavior moveBehavior) {
        this.moveBehavior = moveBehavior;
    }
}
