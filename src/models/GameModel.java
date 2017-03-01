package models;

import gui.GameFrame;

import java.awt.*;

/**
 * Created by KhoaBeo on 2/28/2017.
 */
public abstract class GameModel {

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public GameModel(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMidWidth() {
        return width / 2 + x;
    }

    public boolean intersects(GameModel other) {
        Rectangle rect1 = new Rectangle(x, y, width, height);
        Rectangle rect2 = new Rectangle(other.x, other.y, other.width, other.height);
        return rect1.intersects(rect2);
    }

    public abstract boolean outScreen();
}
