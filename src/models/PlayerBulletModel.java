package models;

import java.awt.*;

/**
 * Created by KhoaBeo on 2/26/2017.
 */
public class PlayerBulletModel extends GameModel {

    public static final int SPEED = 10;

    public PlayerBulletModel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }

    public void fly() {
        y -= SPEED;
    }
}
