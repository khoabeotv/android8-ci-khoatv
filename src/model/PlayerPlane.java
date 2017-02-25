package model;

import gui.GameFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.BitSet;

/**
 * Created by KhoaBeo on 2/22/2017.
 */
public class PlayerPlane extends Plane {
    private BitSet bitSet;
    private ArrayList<Bullet> arrBullet;

    public PlayerPlane(Image image) {
        super(
                image,
                (GameFrame.WIDTH_F - image.getWidth(null)) / 2,
                GameFrame.HEIGHT_F - image.getHeight(null) - 50,
                4
        );
        arrBullet = new ArrayList<>();
        bitSet = new BitSet(256);
    }

    @Override
    protected void addBullet(Image image) {
        Bullet bullet = new Bullet(image, 10);
        bullet.setImage(image);
        bullet.setX((getImage().getWidth(null
        ) - image.getWidth(null)) / 2 + getPlaneX());
        bullet.setY(getPlaneY() - image.getHeight(null));
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
            }
            if (getArrBullet().get(0).getY() < 0) {
                getArrBullet().remove(0);
            }
        }
    }

    @Override
    public void shoot(Image image) {
        if (getArrBullet().size() == 0) {
            addBullet(image);
        } else if (getPlaneY() - getArrBullet().get(getArrBullet().size() - 1).getY() > 100) {
            addBullet(image);
        }
    }

    public ArrayList<Bullet> getArrBullet() {
        return arrBullet;
    }

    public void setArrBullet(ArrayList<Bullet> arrBullet) {
        this.arrBullet = arrBullet;
    }

    public BitSet getBitSet() {
        return bitSet;
    }
}
