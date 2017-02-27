package models;

/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class EnemyPlaneModel {

    public static final int SPEED = 2;
    private int x;
    private int y;
    private int width;
    private int height;
    private String orient;

    public EnemyPlaneModel(int x, int y, int width, int height, String orient) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.orient = orient;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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
