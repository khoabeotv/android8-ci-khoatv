package controllers;

import models.PlayerPlaneModel;
import utils.Utils;
import views.PlayerPlaneView;

import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.BitSet;
import java.util.List;

/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class PlayerPlaneController {

    private PlayerPlaneModel model;
    private PlayerPlaneView view;
    private long lastTimeShoot;

    public PlayerPlaneController(PlayerPlaneModel model, PlayerPlaneView view) {
        this.model = model;
        this.view = view;
    }

    public PlayerPlaneController(int x, int y, Image image) {
        this(new PlayerPlaneModel(x, y, image.getWidth(null), image.getHeight(null)),
                new PlayerPlaneView(image));
    }

    public void run(List<PlayerBulletController> playerBullets) {
        if (model.getBitSet().get(KeyEvent.VK_UP)) {
            model.move("UP");
        }
        if (model.getBitSet().get(KeyEvent.VK_DOWN)) {
            model.move("DOWN");
        }
        if (model.getBitSet().get(KeyEvent.VK_LEFT)) {
            model.move("LEFT");
        }
        if (model.getBitSet().get(KeyEvent.VK_RIGHT)) {
            model.move("RIGHT");
        }
        if (model.getBitSet().get(KeyEvent.VK_NUMPAD0)) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastTimeShoot > 100) {
                addBullet(playerBullets);
                lastTimeShoot = currentTime;
            }
        }
    }

    private void addBullet(List<PlayerBulletController> playerBullets) {
        Image image = Utils.loadImageFromRes("bullet.png");
        PlayerBulletController playerBullet = new PlayerBulletController(
                (model.getWidth() - image.getWidth(null)) / 2 + model.getX(),
                model.getY() - image.getHeight(null),
                image);
        playerBullets.add(playerBullet);
    }

    public void draw(Graphics2D g2d) {
        view.draw(g2d, model);
    }

    public BitSet getBitSet() {
        return model.getBitSet();
    }
}
