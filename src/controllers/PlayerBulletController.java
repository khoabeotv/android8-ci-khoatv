package controllers;

import models.PlayerBulletModel;
import views.PlayerBulletView;
import java.awt.*;

/**
 * Created by KhoaBeo on 2/26/2017.
 */
public class PlayerBulletController {

    private PlayerBulletModel model;
    private PlayerBulletView view;

    public PlayerBulletController(PlayerBulletModel model, PlayerBulletView view) {
        this.model = model;
        this.view = view;
    }

    public PlayerBulletController(int x, int y, Image image) {
        this(new PlayerBulletModel(x, y, image.getWidth(null), image.getHeight(null)),
                new PlayerBulletView(image));
    }

    public void run() {
        model.fly();
    }

    public void draw(Graphics2D g2d) {
        view.draw(g2d, model);
    }

    public Rectangle getRect() {
        return new Rectangle(model.getX(), model.getY(), model.getWidth(), model.getHeight());
    }

    public PlayerBulletModel getModel() {
        return model;
    }
}
