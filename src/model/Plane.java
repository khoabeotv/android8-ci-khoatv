package model;

import gui.GameFrame;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by KhoaBeo on 2/22/2017.
 */
public abstract class Plane {
    private int planeX;
    private int planeY;
    private Image image;
    private int speed;
    private ArrayList<Bullet> arrBullet;

    public Plane(Image image, int planeX, int planeY, int speed) {
        this.image = image;
        this.planeX = planeX;
        this.planeY = planeY;
        this.speed = speed;
        arrBullet = new ArrayList<>();
    }

    public void move(String orient) {
        int x = planeX;
        int y = planeY;

        switch (orient) {
            case "RIGHT":
                x += speed;
                break;
            case "LEFT":
                x -= speed;
                break;
            case "DOWN":
                y += speed;
                break;
            case "UP":
                y -= speed;
                break;
            default:
                break;
        }

        if (x < 0 || x > GameFrame.WIDTH_F - image.getWidth(null)) {
            planeY = y;
            return;
        }

        if (y < 0 || y > GameFrame.HEIGHT_F - image.getHeight(null) - 30) {
            planeX = x;
            return;
        }

        planeX = x;
        planeY = y;
    }

    protected abstract void addBullet(Image image);
    public abstract void shoot(Image image);
    public abstract void drawBullet(Graphics2D g2d);

    public int getPlaneX() {
        return planeX;
    }

    public void setPlaneX(int planeX) {
        this.planeX = planeX;
    }

    public int getPlaneY() {
        return planeY;
    }

    public void setPlaneY(int planeY) {
        this.planeY = planeY;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Rectangle getRect() {
        Rectangle rectangle = new Rectangle(planeX, planeY, image.getWidth(null), image.getHeight(null));
        return rectangle;
    }
}
