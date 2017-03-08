package controllers;

import collision.Collision;
import models.EnemyBulletModel;
import utils.Utils;
import views.EnemyBulletView;

import java.awt.*;

/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class EnemyBulletController extends GameController implements Collision {

    public EnemyBulletController(EnemyBulletModel model, EnemyBulletView view) {
        super(view, model);
        CollisionController.instance.add(this);
    }

    public EnemyBulletController(int x, int y, Image image) {
        this(new EnemyBulletModel(x, y, image.getWidth(null), image.getHeight(null)),
                new EnemyBulletView(image));
    }

    @Override
    public void run() {
        ((EnemyBulletModel)model).fly();
    }

    @Override
    public void collide(Collision other) {
        if (other instanceof PlayerPlaneController) {
            Utils.gameRemove(this);
        }
    }
}
