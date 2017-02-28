package controllers;

import models.PlayerPlaneModel;
import utils.Utils;
import views.PlayerPlaneView;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.BitSet;
import java.util.List;

/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class PlayerPlaneController extends GameController {

    private long lastShoot;
    private List<PlayerBulletController> playerBullets;

    public PlayerPlaneController(PlayerPlaneModel model, PlayerPlaneView view, List<PlayerBulletController> playerBullets) {
        super(view, model);
        this.playerBullets = playerBullets;
    }

    public PlayerPlaneController(int x, int y, Image image, List<PlayerBulletController> playerBullets) {
        this(new PlayerPlaneModel(x, y, image.getWidth(null), image.getHeight(null)),
                new PlayerPlaneView(image),
                playerBullets);
    }

    @Override
    public void run() {
        PlayerPlaneModel model = (PlayerPlaneModel) this.model;
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
            if (currentTime - lastShoot > 100) {
                addBullet();
                lastShoot = currentTime;
            }
        }
    }

    private void addBullet() {
        Image image = Utils.loadImageFromRes("bullet.png");
        PlayerBulletController playerBullet = new PlayerBulletController(
                (model.getWidth() - image.getWidth(null)) / 2 + model.getX(),
                model.getY() - image.getHeight(null),
                image);
        playerBullets.add(playerBullet);
    }

    public BitSet getBitSet() {
        return ((PlayerPlaneModel)model).getBitSet();
    }
}
