package model;

import java.awt.*;

/**
 * Created by KhoaBeo on 2/21/2017.
 */
public class Bullet {
    private Image image;
    private int x;
    private int y;
    private int speed;

    public Bullet(Image image, int speed) {
        this.image = image;
        this.speed = speed;

    }

    public void moveBullet() {
        setY(getY() - speed);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getRect() {
        Rectangle rectangle = new Rectangle(x, y, this.image.getWidth(null), this.image.getHeight(null));
        return rectangle;
    }
}
