package models;

import gui.GameFrame;

/**
 * Created by KhoaBeo on 3/1/2017.
 */
public class PowerUpModel extends GameModel {

    public static final int SPEED = 2;

    public PowerUpModel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public boolean outScreen() {
        if (y > GameFrame.HEIGHT_F) {
            return true;
        }
        return false;
    }

    public void move() {
        y += SPEED;
    }
}
