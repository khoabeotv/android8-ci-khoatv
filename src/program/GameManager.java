package program;

import collision.Collision;
import controllers.*;
import controllers.strategies.MoveDownBehavior;
import controllers.strategies.MoveDownLeftBehavior;
import controllers.strategies.MoveDownRightBehavior;
import gui.GameFrame;
import models.EnemyModel;
import models.ItemMapModel;
import models.PlayerPlaneModel;
import utils.Utils;
import views.EnemyTankView;
import views.EnemyWhiteView;

import java.awt.*;
import java.util.*;
import java.util.List;


/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class GameManager {

    public static final int SUM_ITEM_MAP = GameFrame.HEIGHT_F / ItemMapController.HEIGHT_ITEM;
    public static final int DELAY_ADD_POWER_UP = 10000;
    public static List<GameController> gameControllers;

    private GameController playerPlane;
    private long lastTimeAddEnemy;
    private long lastTimeAddPowerUp;
    private int itemMapIndex;

    public GameManager() {
        gameControllers = new ArrayList<>();

        Image image = Utils.loadImageFromRes("plane1.png");
        playerPlane = new PlayerPlaneController(
                (GameFrame.WIDTH_F - image.getWidth(null)) / 2,
                GameFrame.HEIGHT_F - image.getHeight(null) - 50,
                image
        );

        GameController backgroundOne = new ItemMapController(0, 0);
        gameControllers.add(backgroundOne);
        GameController backgroundTwo = new ItemMapController(0, -Utils.loadImageFromRes("background.png").getHeight(null));
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
            } else if (!gameController.getModel().isAlive()) {
                if (gameController instanceof EnemyController) {
                    int score = ((EnemyModel) gameController.getModel()).getScore();
                    ((PlayerPlaneModel) playerPlane.getModel()).increaseScore(score);
                }
                Utils.gameRemove(gameController);
            }
        }
        setBackground();
        addEnemy();
        addPowerUp();
        checkScore();
        CollisionController.instance.checkCollide();
    }

    private void addEnemy() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTimeAddEnemy > EnemyController.DELAY_ADD_ENEMY) {
            Random rd = new Random();
            EnemyController enemy;
            int x = rd.nextInt(GameFrame.WIDTH_F + 200) - 100;
            if (x < 0) {
                Image image = Utils.loadImageFromRes("enemy-green-1.png");
                enemy = new EnemyController(x, -50, image, 1, 10, 2);
                enemy.setMoveBehavior(new MoveDownRightBehavior());
            } else if (x > GameFrame.WIDTH_F) {
                Image image = Utils.loadImageFromRes("enemy-green-2.png");
                enemy = new EnemyController(x, -50, image, 1, 10, 2);
                enemy.setMoveBehavior(new MoveDownLeftBehavior());
            } else {
                if (x % 2 == 0) {
                    Image image = Utils.loadImageFromRes("enemy-green-3.png");
                    enemy = new EnemyController(x, -50, image, 1, 10, 2);
                    enemy.setMoveBehavior(new MoveDownBehavior());
                }

                if (x % 3 == 0){
                    enemy = new EnemyController(
                            new EnemyModel(rd.nextInt(GameFrame.WIDTH_F), -50, 32, 32, 3, 20, 2),
                            new EnemyWhiteView("enemy_plane_white")
                    );
                    enemy.setMoveBehavior(new MoveDownBehavior());
                }

                if (x % 4 == 0 && ((PlayerPlaneModel) playerPlane.getModel()).getScore() > 100) {
                    enemy = new EnemyController(
                            new EnemyModel(rd.nextInt(GameFrame.WIDTH_F), -50, 30, 50, 5, 30, 1),
                            new EnemyTankView()
                    );
                    enemy.setMoveBehavior(new MoveDownBehavior());
                }
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
        if (currentTime - lastTimeAddPowerUp > DELAY_ADD_POWER_UP) {
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

    private void checkScore() {
        int score = ((PlayerPlaneModel) playerPlane.getModel()).getScore();

        if (score > 100) {
            EnemyController.DELAY_ADD_ENEMY = 800;
        }

        if (score > 200) {
            EnemyController.DELAY_ADD_ENEMY = 500;
        }

        if (score > 400) {
            EnemyController.DELAY_ADD_ENEMY = 200;
        }
    }

    public PlayerPlaneModel getPlayerPlaneModel() {
        return (PlayerPlaneModel) playerPlane.getModel();
    }
}
