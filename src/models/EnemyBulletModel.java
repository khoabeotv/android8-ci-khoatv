package models;

/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class EnemyBulletModel extends GameModel {

    public static final int SPEED = 5;

    public EnemyBulletModel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void fly() {
        y += SPEED;
    }
}
