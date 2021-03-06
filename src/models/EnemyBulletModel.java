package models;

import gui.GameFrame;

/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class EnemyBulletModel extends GameModel {

    public static final int SPEED = 5;

    public EnemyBulletModel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public boolean outScreen() {
        if (y > GameFrame.HEIGHT_F) {
            return true;
        }
        return false;
    }

    public void fly() {
        y += SPEED;
    }
}
