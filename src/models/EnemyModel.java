package models;

import gui.GameFrame;

/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class EnemyModel extends GameModel {

    public int speed;
    private int hp;
    private boolean dead;
    private int score;

    public EnemyModel(int x, int y, int width, int height, int hp, int score, int speed) {
        super(x, y, width, height);
        this.hp = hp;
        this.score = score;
        this.speed = speed;
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
        x += dx * speed;
        y += dy * speed;
    }

    public int getScore() {
        return score;
    }
}
