package controllers;

import collision.Collision;
import models.PlayerBulletModel;
import models.PlayerPlaneModel;
import program.GameManager;
import utils.Utils;
import views.PlayerPlaneView;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.BitSet;


/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class PlayerPlaneController extends GameController implements Collision {

    public static final int DELAY_ADD_ROCKET = 1000;
    private long lastShoot;
    private long lastTimeAddRocket;

    public PlayerPlaneController(PlayerPlaneModel model, PlayerPlaneView view) {
        super(view, model);
        CollisionController.instance.add(this);
    }

    public PlayerPlaneController(int x, int y, Image image) {
        this(new PlayerPlaneModel(x, y, image.getWidth(null), image.getHeight(null)),
                new PlayerPlaneView(image)
                );
    }

    @Override
    public void run() {
        PlayerPlaneModel model = (PlayerPlaneModel) this.model;

        if (!model.isDead()) {
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
                if (currentTime - lastShoot > 200) {
                    shoot();
                    lastShoot = currentTime;
                }
            }
            addRocket();
        } else if (!((PlayerPlaneView)view).explode()) {
            if (model.getTurn() > 0) {
                model.setDead(false);
                CollisionController.instance.add(this);
            } else {
                GameManager.gameControllers.remove(this);
            }
        }
    }

    private void shoot() {
        int bulletLevel = ((PlayerPlaneModel)model).getBulletLevel();
        if (bulletLevel == 0) {
            Image image = Utils.loadImageFromRes("bullet-single.png");
            addBullet(
                    model.getMidWidth() - image.getWidth(null) / 2,
                    model.getY() - image.getHeight(null),
                    image,
                    PlayerBulletModel.UP
            );
            return;
        }

        if (bulletLevel > 0) {
            Image image = Utils.loadImageFromRes("bullet-double.png");
            addBullet(
                    model.getMidWidth() - image.getWidth(null) / 2,
                    model.getY() - image.getHeight(null),
                    image,
                    PlayerBulletModel.UP
            );
        }

        if (bulletLevel > 1) {
            Image image = Utils.loadImageFromRes("bullet-right.png");
            addBullet(
                    model.getX() - image.getWidth(null) / 2,
                    model.getY() - image.getHeight(null),
                    image,
                    PlayerBulletModel.LEFT
            );

            image = Utils.loadImageFromRes("bullet-left.png");
            addBullet(
                    model.getX() + model.getWidth() - image.getWidth(null) / 2,
                    model.getY() - image.getHeight(null),
                    image,
                    PlayerBulletModel.RIGHT
            );
        }
    }

    private void addBullet(int x, int y, Image image, String orient) {
        GameController playerBullet = new PlayerBulletController(x, y, image, orient);
        GameManager.gameControllers.add(playerBullet);
    }

    private void addRocket() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTimeAddRocket > DELAY_ADD_ROCKET) {
            if (((PlayerPlaneModel) model).getBulletLevel() > 2) {
                GameController playerRocket = new PlayerRocketController(
                        model.getMidWidth() - Utils.loadImageFromRes("rocket-0.png").getWidth(null) / 2,
                        model.getY() - Utils.loadImageFromRes("rocket-0.png").getHeight(null),
                        "rocket"
                );
                GameManager.gameControllers.add(playerRocket);
            }
            lastTimeAddRocket = currentTime;
        }
    }

    public BitSet getBitSet() {
        return ((PlayerPlaneModel)model).getBitSet();
    }

    @Override
    public void collide(Collision other) {
        PlayerPlaneModel model = (PlayerPlaneModel)this.model;
        if (other instanceof PowerUpController) {
            model.upBulletLevel();
        }

        if (other instanceof EnemyBulletController) {
            CollisionController.instance.remove(this);
            model.setDead(true);
            model.downBulletLevel();
            model.setTurn();
        }
    }
}
