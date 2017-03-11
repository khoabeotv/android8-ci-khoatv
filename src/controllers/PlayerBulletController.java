package controllers;

import collision.Collision;
import models.EnemyModel;
import models.PlayerBulletModel;
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
            if (!(((EnemyModel)other.getModel()).isDead())) {
                model.setAlive(false);
            }
        }
    }
}
