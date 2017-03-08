package models;

import gui.GameFrame;

/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class EnemyPlaneModel extends GameModel {

    public static final int SPEED = 2;
    private int hp;
    private boolean dead;

    public EnemyPlaneModel(int x, int y, int width, int height, int hp) {
        super(x, y, width, height);
        this.hp = hp;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public boolean outScreen(){
        if (y > GameFrame.HEIGHT_F || x < -width || x > GameFrame.WIDTH_F + width) {
            return true;
        }
        return false;
    }

    public void move(int dx, int dy) {
        x += dx * SPEED;
        y += dy * SPEED;
    }
}
