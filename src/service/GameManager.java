package service;

import model.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;

/**
 * Created by KhoaBeo on 2/23/2017.
 */
public class GameManager {
    private PlayerPlane playerPlane;
    private ArrayList<EnemyPlane> arrEnemy;
    private int sumEnemy;
    private Map map;


    public GameManager() {
        playerPlane = new PlayerPlane(loadImageFromRes("plane3.png"));
        arrEnemy = new ArrayList<>();
        sumEnemy = 10;
        for (int i = 0; i < sumEnemy; i++) {
            EnemyPlane enemy = new EnemyPlane(loadImageFromRes("enemy-green-3.png"), i * 40, 0, 1);
            arrEnemy.add(enemy);
        }
        ArrayList<Image> itemMapImages = new ArrayList<>();

        itemMapImages.add(loadImageFromRes("island.png"));
        itemMapImages.add(loadImageFromRes("island-2.png"));
        map = new Map(loadImageFromRes("background.png"), itemMapImages);
    }

    public void drawImage(Graphics2D g2d) {
        map.drawMap(g2d);

        g2d.drawImage(playerPlane.getImage(), playerPlane.getPlaneX(), playerPlane.getPlaneY(), null);
        playerPlane.drawBullet(g2d);

        for (int i = 0; i < arrEnemy.size(); i++) {
            EnemyPlane enemy = arrEnemy.get(i);
            g2d.drawImage(enemy.getImage(), enemy.getPlaneX(), enemy.getPlaneY(), null);
            if (!enemy.isDead()) {
                enemy.shoot(loadImageFromRes("bullet-round.png"));
                enemy.move();
            } else if (enemy.exploed()) {
                arrEnemy.remove(i);
            }
        }
        if (arrEnemy.size() > 0) {
            arrEnemy.get(0).drawBullet(g2d);
        }
    }

    public void event() {

        if (getBitSet().get(KeyEvent.VK_UP)) {
            playerPlane.move("UP");
        }
        if (getBitSet().get(KeyEvent.VK_DOWN)) {
            playerPlane.move("DOWN");
        }
        if (getBitSet().get(KeyEvent.VK_RIGHT)) {
            playerPlane.move("RIGHT");
        }
        if (getBitSet().get(KeyEvent.VK_LEFT)) {
            playerPlane.move("LEFT");
        }
        if (getBitSet().get(KeyEvent.VK_NUMPAD0)) {
            playerPlane.shoot(loadImageFromRes("bullet.png"));
        }

        for (int i = 0; i < playerPlane.getArrBullet().size(); i++) {
            Bullet bullet = playerPlane.getArrBullet().get(i);
            for (int j = 0; j < arrEnemy.size(); j++) {
                EnemyPlane enemyPlane = arrEnemy.get(j);
                Rectangle rectangle = enemyPlane.getRect().intersection(bullet.getRect());
                if (!rectangle.isEmpty() && !enemyPlane.isDead()) {
                    playerPlane.getArrBullet().remove(i);
                    enemyPlane.setDead(true);
                }
            }
        }

        for (int i = 0; i < EnemyPlane.getArrBullet().size(); i++) {
            Bullet bullet = EnemyPlane.getArrBullet().get(i);
            Rectangle rectangle = playerPlane.getRect().intersection(bullet.getRect());
            if (!rectangle.isEmpty()) {
                //TODO: GameOver
            }
        }
    }

    public BitSet getBitSet() {
        return playerPlane.getBitSet();
    }

    public static Image loadImageFromRes(String url) {
        try {
            Image image = ImageIO.read(new File("resources/" + url));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
