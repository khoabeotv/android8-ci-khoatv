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

    private GameController playerPlane;
    private long lastTimeAddEnemy;
    private long lastTimeAddItemMap;
    private long lastTimeAddPowerUp;

    public GameManager() {
        Image image = Utils.loadImageFromRes("plane1.png");
        playerPlane = new PlayerPlaneController(
                (GameFrame.WIDTH_F - image.getWidth(null)) / 2,
                GameFrame.HEIGHT_F - image.getHeight(null) - 50,
                image
        );

        Image background = Utils.loadImageFromRes("background.png");
        GameController backgroundOne = new ItemMapController(0, 0, background);
        GameController backgroundTwo = new ItemMapController(0, -background.getHeight(null), background);

        gameControllers.add(backgroundOne);
        gameControllers.add(backgroundTwo);
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
                }
            }
        }
        setBackground();
        addItemMap();
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
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTimeAddItemMap > 10000) {
            Random rd = new Random();
            int x = rd.nextInt(GameFrame.WIDTH_F - 100);
            GameController itemMap = new ItemMapController(x, -50, Utils.loadImageFromRes("island-" + rd.nextInt(2) + ".png"));
            gameControllers.add(2, itemMap);
            lastTimeAddItemMap = currentTime;
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
