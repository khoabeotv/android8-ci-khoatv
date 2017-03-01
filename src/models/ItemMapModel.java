package models;

import gui.GameFrame;

/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class ItemMapModel extends GameModel {

    public ItemMapModel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void move() {
        y++;
    }

    @Override
    public boolean outScreen() {
        if (y > GameFrame.HEIGHT_F) {
            return true;
        }
        return false;
    }

    public void setY(int y) {
        this.y = y;
    }
}
