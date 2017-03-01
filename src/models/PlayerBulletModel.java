package models;

/**
 * Created by KhoaBeo on 2/26/2017.
 */
public class PlayerBulletModel extends GameModel {

    public static final int SPEED = 8;
    public static final String UP = "up";
    public static final String LEFT = "left";
    public static final String RIGHT = "right";

    private String orient;

    public PlayerBulletModel(int x, int y, int width, int height, String orient) {
        super(x, y, width, height);
        this.orient = orient;
    }

    @Override
    public boolean outScreen() {
        if (y < -height) {
            return true;
        }
        return false;
    }

    public void fly() {
        switch (orient) {
            case UP:
                y -= SPEED;
                break;
            case LEFT:
                y -= SPEED / Math.sqrt(2);
                x -= SPEED / Math.sqrt(2);
                break;
            case RIGHT:
                y -= SPEED;
                x += SPEED;
                break;
            default:
                break;
        }
    }
}
