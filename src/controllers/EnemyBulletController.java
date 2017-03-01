package controllers;

import models.EnemyBulletModel;
import models.GameModel;
import program.GameManager;
import utils.Utils;
import views.EnemyBulletView;

import java.awt.*;

/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class EnemyBulletController extends GameController implements Collision {

    public EnemyBulletController(EnemyBulletModel model, EnemyBulletView view) {
        super(view, model);
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

    }
}
