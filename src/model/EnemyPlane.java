package model;

import gui.GameFrame;
import service.GameManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by KhoaBeo on 2/22/2017.
 */
public class EnemyPlane extends Plane {
    private long lastTime;
    private long lastTimeExpl;
    private static ArrayList<Bullet> arrBullet;
    private int orient;
    private boolean dead;
    private int index;

    public EnemyPlane(Image image, int planeX, int planeY, int speed) {
        super(image, planeX, planeY, speed);
        arrBullet = new ArrayList<>();
    }

    @Override
    protected void addBullet(Image image) {
        Bullet bullet = new Bullet(image, -2);
        bullet.setImage(image);
        bullet.setX((getImage().getWidth(null
        ) - image.getWidth(null)) / 2 + getPlaneX());
        bullet.setY(getPlaneY() + getImage().getHeight(null));
        getArrBullet().add(bullet);
    }

    @Override
    public void drawBullet(Graphics2D g2d) {
        for (int i = 0; i < getArrBullet().size(); i++) {
            Bullet bullet = getArrBullet().get(i);
            g2d.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), null);
        }
        if (getArrBullet().size() > 0) {
            for (int i = 0; i < getArrBullet().size(); i++) {
                getArrBullet().get(i).moveBullet();
                if (getArrBullet().get(i).getY() > GameFrame.HEIGHT_F - 30) {
                    getArrBullet().remove(i);
                }
            }
        }
    }

    @Override
    public void shoot(Image image) {
        Random rd = new Random();
        int temp = rd.nextInt(100);
        if (temp == 0) {
            addBullet(image);
        }
    }

    public void move() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime >= 1000) {
            Random rd = new Random();
            orient = rd.nextInt(10);
            lastTime = currentTime;
        }

        if (orient < 3) {
            super.move("DOWN");
        } else if (orient < 6) {
            super.move("HOLD");
        } else if (orient == 7) {
            super.move("LEFT");
        } else if (orient == 8) {
            super.move("RIGHT");
        } else {
            super.move("UP");
        }
    }

    public boolean exploed() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTimeExpl > 100) {
            setImage(GameManager.loadImageFromRes("expl-" + index + ".png"));
            index++;
            lastTimeExpl = currentTime;
        }
        if (index == 14) {
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<Bullet> getArrBullet() {
        return arrBullet;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
