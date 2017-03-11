package models;

import gui.GameFrame;
import java.util.BitSet;

/**
 * Created by KhoaBeo on 2/26/2017.
 */
public class PlayerPlaneModel extends GameModel {

    public static final int SPEED = 4;
    private BitSet bitSet;
    private int bulletLevel;
    private int turn;
    private boolean dead;
    private int score;

    public PlayerPlaneModel(int x, int y, int width, int height) {
        super(x, y, width, height);
        bitSet = new BitSet();
        turn = 3;
    }

    @Override
    public boolean outScreen() {
        return false;
    }

    public BitSet getBitSet() {
        return bitSet;
    }

    public void move(String orient) {

            int x = this.x;
            int y = this.y;

            switch (orient) {
                case "RIGHT":
                    x += SPEED;
                    break;
                case "LEFT":
                    x -= SPEED;
                    break;
                case "DOWN":
                    y += SPEED;
                    break;
                case "UP":
                    y -= SPEED;
                    break;
                default:
                    break;
            }

            if (x < 0 || x > GameFrame.WIDTH_F - width) {
                this.y = y;
                return;
            }

            if (y < 0 || y > GameFrame.HEIGHT_F - height - 30) {
                this.x = x;
                return;
            }

            this.x = x;
            this.y = y;
    }

    public int getBulletLevel() {
        return bulletLevel;
    }

    public void upBulletLevel() {
        bulletLevel++;
    }

    public void downBulletLevel() {
        bulletLevel = 0;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn() {
        turn--;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(int score) {
        this.score += score;
    }
}
