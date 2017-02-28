package models;

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

    public void setY(int y) {
        this.y = y;
    }
}
