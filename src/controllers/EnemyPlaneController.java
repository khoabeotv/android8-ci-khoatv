package controllers;

import models.EnemyPlaneModel;
import program.GameManager;
import utils.Utils;
import views.EnemyPlaneView;
import views.EnemyWhiteView;

import java.awt.Image;


/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class EnemyPlaneController extends GameController implements Collision {

    private long lastTimeAddBullet;

    public EnemyPlaneController(EnemyPlaneModel model, EnemyPlaneView view) {
        super(view, model);
        CollisionController.instance.add(this);
    }

    public EnemyPlaneController(int x, int y, Image image, String orient, int hp) {
        this(new EnemyPlaneModel(
                        x,
                        y,
                        image.getWidth(null),
                        image.getHeight(null),
                        orient,
                        hp),
                new EnemyPlaneView(image)
        );
    }

    @Override
    public void run() {
        EnemyPlaneModel model = (EnemyPlaneModel) this.model;
        if (!model.isDead()) {
            shoot();
            model.move();
            if (view instanceof EnemyWhiteView) {
                ((EnemyWhiteView) view).setImage();
            }
        } else if (!((EnemyPlaneView) view).explode()) {
            GameManager.gameControllers.remove(this);
        }
    }

    private void shoot() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTimeAddBullet > 1000) {
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
}
