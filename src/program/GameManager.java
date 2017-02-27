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

    public GameManager() {
        Image image = Utils.loadImageFromRes("plane1.png");
        playerPlane = new PlayerPlaneController((GameFrame.WIDTH_F - image.getWidth(null)) / 2,
                GameFrame.HEIGHT_F - image.getHeight(null) - 50,
                image);

        playerBullets = new ArrayList<>();
        enemyBullets = new ArrayList<>();
        enemyPlanes = new ArrayList<>();
        itemMaps = new ArrayList<>();
    }

    public void draw(Graphics2D g2d) {
        for (int i = 0; i < playerBullets.size(); i++) {
            PlayerBulletController playerBullet = playerBullets.get(i);

            if (playerBullet.getModel().getY() < 0) {
                playerBullets.remove(i);
            }

            playerBullet.draw(g2d);
            playerBullet.run();
        }
        playerPlane.draw(g2d);

        for (int i = 0; i < enemyPlanes.size(); i++) {
            EnemyPlaneController enemyPlane = enemyPlanes.get(i);
            if (enemyPlane.getModel().getX() > GameFrame.HEIGHT_F)  {
                enemyPlanes.remove(i);
            }
            enemyPlane.draw(g2d);
            enemyPlane.run();
        }
        for (int i = 0; i < enemyBullets.size(); i++) {
            enemyBullets.get(i).draw(g2d);
            enemyBullets.get(i).run();
        }

        for (int i = 0; i < itemMaps.size(); i++) {
            itemMaps.get(i).draw(g2d);
            itemMaps.get(i).run();
        }
    }

    public void run() {
        playerPlane.run(playerBullets);
        addEnemy();
    }

    private void addEnemy() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTimeAddEnemy > 1000) {
            Random rd = new Random();
            int x = rd.nextInt(GameFrame.WIDTH_F + 200) - 100;
            if (x < 0) {
                Image image =  Utils.loadImageFromRes("enemy-green-1.png");
                EnemyPlaneController enemyPlane = new EnemyPlaneController(
                        new EnemyPlaneModel(x,
                                -50,
                                image.getWidth(null),
                                image.getHeight(null),
                                "RIGHT"),
                        new EnemyPlaneView(image)
                );
                enemyPlanes.add(enemyPlane);
            } else if (x > GameFrame.WIDTH_F) {
                Image image =  Utils.loadImageFromRes("enemy-green-2.png");
                EnemyPlaneController enemyPlane = new EnemyPlaneController(
                        new EnemyPlaneModel(x,
                                -50, image.getWidth(null),
                                image.getHeight(null),
                                "LEFT"),
                        new EnemyPlaneView(image)
                );
                enemyPlanes.add(enemyPlane);
            } else {
                Image image =  Utils.loadImageFromRes("enemy-green-3.png");
                EnemyPlaneController enemyPlane = new EnemyPlaneController(
                        new EnemyPlaneModel(x,
                                -50, image.getWidth(null),
                                image.getHeight(null),
                                "DOWN"),
                        new EnemyPlaneView(image)
                );
                enemyPlanes.add(enemyPlane);
            }
            lastTimeAddEnemy = currentTime;
        }
    }

    public void collide() {
        for (int i = 0; i < enemyPlanes.size(); i++) {
            for (int j = 0; j < playerBullets.size(); i++) {

            }
        }
    }

    public BitSet getBitSet() {
        return playerPlane.getBitSet();
    }
}
