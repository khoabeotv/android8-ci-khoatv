package controllers;

import models.EnemyPlaneModel;
import utils.Utils;
import views.EnemyPlaneView;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.util.List;


/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class EnemyPlaneController {

    private EnemyPlaneModel model;
    private EnemyPlaneView view;
    private long lastTimeAddBullet;

    public EnemyPlaneController(EnemyPlaneModel model, EnemyPlaneView view) {
        this.model = model;
        this.view = view;
    }

    public EnemyPlaneController(int x, int y, Image image, String orient) {
        this(new EnemyPlaneModel(x, y, image.getWidth(null), image.getHeight(null), orient),
                new EnemyPlaneView(image));
    }

    public boolean run(List<PlayerBulletController> playerBullets, List<EnemyBulletController> enemyBullets) {
        if (!model.isDead()) {
            model.move();
            collide(playerBullets);
            addBullet(enemyBullets);
            return true;
        } else {
            return view.explode();
        }
    }

    private void collide(List<PlayerBulletController> playerBullets) {
        for (int i = 0; i < playerBullets.size(); i++) {
            Rectangle rectangle = getRect().intersection(playerBullets.get(i).getRect());
            if (!rectangle.isEmpty()) {
                playerBullets.remove(i);
                model.setDead(true);
            }
        }
    }

    private void addBullet(List<EnemyBulletController> enemyBullets) {
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

    private Rectangle getRect() {
        return new Rectangle(model.getX(), model.getY(), model.getWidth(), model.getHeight());
    }

    public EnemyPlaneModel getModel() {
        return model;
    }

    public void draw(Graphics2D g2d) {
        view.draw(g2d, model);
    }
}
