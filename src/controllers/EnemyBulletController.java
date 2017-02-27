package controllers;

import models.EnemyBulletModel;
import views.EnemyBulletView;

import java.awt.*;

/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class EnemyBulletController {

    private EnemyBulletModel model;
    private EnemyBulletView view;

    public EnemyBulletController(EnemyBulletModel model, EnemyBulletView view) {
        this.model = model;
        this.view = view;
    }

    public EnemyBulletController(int x, int y, Image image) {
        this(new EnemyBulletModel(x, y, image.getWidth(null), image.getHeight(null)),
                new EnemyBulletView(image));
    }

    public void run() {
        model.fly();
    }

    public EnemyBulletModel getModel() {
        return model;
    }

    public void draw(Graphics2D g2d) {
        view.draw(g2d, model);
    }
}
