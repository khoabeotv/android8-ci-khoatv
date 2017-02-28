package models;

import java.awt.*;

/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class EnemyPlaneModel extends GameModel {

    public static final int SPEED = 2;
    private String orient;
    private boolean dead;

    public EnemyPlaneModel(int x, int y, int width, int height, String orient) {
        super(x, y, width, height);
        this.orient = orient;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }

    public void move() {
        switch (orient) {
            case "DOWN":
                y += SPEED;
                break;
            case "LEFT":
                y += SPEED;
                x -= SPEED;
                break;
            case "RIGHT":
                y += SPEED;
                x += SPEED;
                break;
            default:
                break;
        }
    }
}
