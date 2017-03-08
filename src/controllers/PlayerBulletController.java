package controllers;

import collision.Collision;
import models.EnemyPlaneModel;
import models.PlayerBulletModel;
import utils.Utils;
import views.PlayerBulletView;
import java.awt.Image;

/**
 * Created by KhoaBeo on 2/26/2017.
 */
public class PlayerBulletController extends GameController implements Collision {

    public PlayerBulletController(PlayerBulletModel model, PlayerBulletView view) {
        super(view, model);
        CollisionController.instance.add(this);
    }

    public PlayerBulletController(int x, int y, Image image, String orient) {
        this(new PlayerBulletModel(x, y, image.getWidth(null), image.getHeight(null), orient),
                new PlayerBulletView(image));
    }

    @Override
    public void run() {
        ((PlayerBulletModel)model).fly();
    }

    @Override
    public void collide(Collision other) {
        if (other instanceof EnemyController) {
            if (!(((EnemyPlaneModel)other.getModel()).isDead())) {
                Utils.gameRemove(this);
            }
        }
    }
}
