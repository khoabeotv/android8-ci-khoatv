package program;

import com.sun.org.glassfish.gmbal.GmbalException;
import controllers.*;
import gui.GameFrame;
import models.EnemyPlaneModel;
import utils.Utils;
import views.EnemyPlaneView;

import java.awt.*;
import java.util.*;
import java.util.List;


/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class GameManager {
    private PlayerPlaneController playerPlane;
    private List<PlayerBulletController> playerBullets;
    private List<EnemyPlaneController> enemyPlanes;
    private List<EnemyBulletController> enemyBullets;
    private List<ItemMapController> itemMaps;
    private long lastTimeAddEnemy;
    private long lastTimeAddItemMap;

    public GameManager() {
        Image image = Utils.loadImageFromRes("plane1.png");
        playerPlane = new PlayerPlaneController((GameFrame.WIDTH_F - image.getWidth(null)) / 2,
                GameFrame.HEIGHT_F - image.getHeight(null) - 50,
                image);

        playerBullets = new ArrayList<>();
        enemyBullets = new ArrayList<>();
        enemyPlanes = new ArrayList<>();
        itemMaps = new ArrayList<>();

        Image background = Utils.loadImageFromRes("background.png");
        ItemMapController backgroundOne = new ItemMapController(0, 0, background);
        ItemMapController backgroundTwo = new ItemMapController(0, -background.getHeight(null), background);
        itemMaps.add(backgroundOne);
        itemMaps.add(backgroundTwo);
    }

    public void draw(Graphics2D g2d) {
        //Draw map
        for (int i = 0; i < itemMaps.size(); i++) {
            ItemMapController itemMap = itemMaps.get(i);

            if (i < 2 && itemMap.getModel().getY() == GameFrame.HEIGHT_F) {
                itemMap.getModel().setY(-itemMap.getModel().getHeight());
            }

            if (itemMap.getModel().getY() > GameFrame.HEIGHT_F) {
                itemMaps.remove(itemMap);
            }

            itemMap.run();
            itemMap.draw(g2d);
        }

        //Draw player
        for (int i = 0; i < playerBullets.size(); i++) {
            PlayerBulletController playerBullet = playerBullets.get(i);

            if (playerBullet.getModel().getY() < 0) {
                playerBullets.remove(i);
            }

            playerBullet.run();
            playerBullet.draw(g2d);
        }
        playerPlane.draw(g2d);

        //Draw enemies
        for (int i = 0; i < enemyPlanes.size(); i++) {
            EnemyPlaneController enemyPlane = enemyPlanes.get(i);
            if (!enemyPlane.run(playerBullets, enemyBullets) || enemyPlane.getModel().getX() > GameFrame.HEIGHT_F)  {
                enemyPlanes.remove(i);
            }
            enemyPlane.draw(g2d);
        }
        for (int i = 0; i < enemyBullets.size(); i++) {
            EnemyBulletController enemyBullet = enemyBullets.get(i);

            if (enemyBullet.getModel().getY() > GameFrame.HEIGHT_F) {
                enemyBullets.remove(i);
            }

            enemyBullet.run();
            enemyBullet.draw(g2d);
        }
    }

    public void run() {
        playerPlane.run(playerBullets);
        addEnemy();
        addItemMap();
    }

    private void addEnemy() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTimeAddEnemy > 700) {
            Random rd = new Random();
            int x = rd.nextInt(GameFrame.WIDTH_F + 200) - 100;
            if (x < 0) {
                Image image =  Utils.loadImageFromRes("enemy-green-1.png");
                EnemyPlaneController enemyPlane = new EnemyPlaneController(x, -50, image, "RIGHT");
                enemyPlanes.add(enemyPlane);
            } else if (x > GameFrame.WIDTH_F) {
                Image image =  Utils.loadImageFromRes("enemy-green-2.png");
                EnemyPlaneController enemyPlane = new EnemyPlaneController(x, -50, image, "LEFT");
                enemyPlanes.add(enemyPlane);
            } else {
                Image image =  Utils.loadImageFromRes("enemy-green-3.png");
                EnemyPlaneController enemyPlane = new EnemyPlaneController(x, -50, image, "DOWN");
                enemyPlanes.add(enemyPlane);
            }
            lastTimeAddEnemy = currentTime;
        }
    }

    private void addItemMap() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTimeAddItemMap > 10000) {
            Random rd = new Random();
            int x = rd.nextInt(GameFrame.WIDTH_F - 100);
            ItemMapController itemMap = new ItemMapController(x, -50, Utils.loadImageFromRes("island-"  + rd.nextInt(2) + ".png"));
            itemMaps.add(itemMap);
            lastTimeAddItemMap = currentTime;
        }
    }

    public BitSet getBitSet() {
        return playerPlane.getBitSet();
    }
}
