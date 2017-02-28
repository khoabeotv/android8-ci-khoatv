package controllers;

import models.EnemyPlaneModel;
import utils.Utils;
import views.EnemyPlaneView;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;


/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class EnemyPlaneController extends GameController {

    private long lastTimeAddBullet;
    private List<EnemyBulletController> enemyBullets;

    public EnemyPlaneController(EnemyPlaneModel model, EnemyPlaneView view, List<EnemyBulletController> playerBullets) {
        super(view, model);
        this.enemyBullets = playerBullets;
    }

    public EnemyPlaneController(int x, int y, Image image, String orient, List<EnemyBulletController> playerBullets) {
        this(new EnemyPlaneModel(
                        x,
                        y,
                        image.getWidth(null),
                        image.getHeight(null), orient),
                new EnemyPlaneView(image),
                playerBullets);
    }

    public boolean run(List<PlayerBulletController> playerBullets) {
        EnemyPlaneModel model = (EnemyPlaneModel) this.model;
        EnemyPlaneView view = (EnemyPlaneView) this.view;
        if (!model.isDead()) {
            model.move();
            collide(playerBullets);
            addBullet();
            return true;
        } else {
            return view.explode();
        }
    }

    private void collide(List<PlayerBulletController> playerBullets) {
        for (int i = 0; i < playerBullets.size(); i++) {
            Rectangle rectangle = ((EnemyPlaneModel)model).getRect().intersection(playerBullets.get(i).getRect());
            if (!rectangle.isEmpty()) {
                playerBullets.remove(i);
                ((EnemyPlaneModel)model).setDead(true);
            }
        }
    }

    private void addBullet() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTimeAddBullet > 1000) {
            Image image = Utils.loadImageFromRes("bullet-round.png");
            EnemyBulletController enemyBullet = new EnemyBulletController(
                    (model.getWidth() - image.getWidth(null)) / 2 + model.getX(),
                    model.getHeight() + model.getY(),
                    image);
            enemyBullets.add(enemyBullet);
            lastTimeAddBullet = currentTime;
        }
    }

    public EnemyPlaneModel getModel() {
        return (EnemyPlaneModel)model;
    }
}
