package controllers;

import models.PlayerBulletModel;
import views.PlayerBulletView;
import java.awt.*;

/**
 * Created by KhoaBeo on 2/26/2017.
 */
public class PlayerBulletController extends GameController {

    public PlayerBulletController(PlayerBulletModel model, PlayerBulletView view) {
        super(view, model);
    }

    public PlayerBulletController(int x, int y, Image image) {
        this(new PlayerBulletModel(x, y, image.getWidth(null), image.getHeight(null)),
                new PlayerBulletView(image));
    }

    @Override
    public void run() {
        ((PlayerBulletModel)model).fly();
    }

    public Rectangle getRect() {
        return ((PlayerBulletModel)model).getRect();
    }

    public PlayerBulletModel getModel() {
        return (PlayerBulletModel)model;
    }
}
