package program;

import com.sun.org.glassfish.gmbal.GmbalException;
import controllers.*;
import gui.GameFrame;
import models.EnemyPlaneModel;
import models.ItemMapModel;
import utils.Utils;
import views.EnemyPlaneView;

import java.awt.*;
import java.util.*;
import java.util.List;


/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class GameManager {

    public static final List<GameController> gameControllers = new ArrayList<>();
    public static final int SUM_ITEM_MAP = GameFrame.HEIGHT_F / ItemMapController.HEIGHT_ITEM;

    private GameController playerPlane;
    private long lastTimeAddEnemy;
    private long lastTimeAddPowerUp;
    private int itemMapIndex;

    public GameManager() {
        Image image = Utils.loadImageFromRes("plane1.png");
        playerPlane = new PlayerPlaneController(
                (GameFrame.WIDTH_F - image.getWidth(null)) / 2,
                GameFrame.HEIGHT_F - image.getHeight(null) - 50,
                image
        );

        GameController backgroundOne = new ItemMapController(0, 0);
        GameController backgroundTwo = new ItemMapController(0, -Utils.loadImageFromRes("background.png").getHeight(null));

        gameControllers.add(backgroundOne);
        gameControllers.add(backgroundTwo);

        itemMapIndex = 102;
        for (int i = 1; i <= SUM_ITEM_MAP + 1; i++) {
            image = Utils.loadImageFromRes("BAS/BAS_" + itemMapIndex + ".png");
            GameController itemMap = new ItemMapController(0, GameFrame.HEIGHT_F - i * ItemMapController.HEIGHT_ITEM, image);
            gameControllers.add(itemMap);
            itemMapIndex--;
        }
        gameControllers.add(playerPlane);
    }

    public void draw(Graphics2D g2d) {
        for (int i = 0; i < gameControllers.size(); i++) {
            gameControllers.get(i).draw(g2d);
        }
    }

    public void run() {
        for (int i = 0; i < gameControllers.size(); i++) {
            GameController gameController = gameControllers.get(i);
            gameController.run();
            if (gameController.outScreen() && i > 1) {
                if (gameController instanceof Collision) {
                    Utils.gameRemove(gameController);
                } else {
                    gameControllers.remove(gameController);
                    addItemMap();
                }
                i--;
            }
        }
        setBackground();
        addEnemy();
        addPowerUp();
        CollisionController.instance.checkCollide();
    }

    private void addEnemy() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTimeAddEnemy > 700) {
            Random rd = new Random();
            int x = rd.nextInt(GameFrame.WIDTH_F + 200) - 100;
            if (x < 0) {
                Image image = Utils.loadImageFromRes("enemy-green-1.png");
                GameController enemyPlane = new EnemyPlaneController(x, -50, image, "RIGHT");
                gameControllers.add(enemyPlane);
            } else if (x > GameFrame.WIDTH_F) {
                Image image = Utils.loadImageFromRes("enemy-green-2.png");
                GameController enemyPlane = new EnemyPlaneController(x, -50, image, "LEFT");
                gameControllers.add(enemyPlane);
            } else {
                Image image = Utils.loadImageFromRes("enemy-green-3.png");
                GameController enemyPlane = new EnemyPlaneController(x, -50, image, "DOWN");
                gameControllers.add(enemyPlane);
            }
            lastTimeAddEnemy = currentTime;
        }
    }

    private void addItemMap() {
        if (itemMapIndex > 0) {
            Image image = Utils.loadImageFromRes("BAS/BAS_" + itemMapIndex + ".png");
            GameController newItemMap = new ItemMapController(0, -ItemMapController.HEIGHT_ITEM, image);
            gameControllers.add(SUM_ITEM_MAP + 2, newItemMap);
            itemMapIndex--;
        }
    }

    private void addPowerUp() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTimeAddPowerUp > 10000) {
            Random rd = new Random();
            int x = rd.nextInt(GameFrame.WIDTH_F - 100);
            GameController itemMap = new PowerUpController(x, -50, Utils.loadImageFromRes("power-up.png"));
            gameControllers.add(itemMap);
            lastTimeAddPowerUp = currentTime;
        }
    }

    private void setBackground() {
        ItemMapController backgroundOne = (ItemMapController) gameControllers.get(0);
        ItemMapController backgroundTwo = (ItemMapController) gameControllers.get(1);
        if (backgroundOne.getModel().getY() == 0) {
            ((ItemMapModel) backgroundTwo.getModel()).setY(-backgroundTwo.getModel().getHeight());
        } else if (backgroundTwo.getModel().getY() == 0) {
            ((ItemMapModel) backgroundOne.getModel()).setY(-backgroundOne.getModel().getHeight());
        }
    }

    public BitSet getBitSet() {
        return ((PlayerPlaneController) playerPlane).getBitSet();
    }
}
