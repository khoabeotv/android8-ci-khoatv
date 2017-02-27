package models;

import controllers.PlayerBulletController;
import gui.GameFrame;
import views.PlayerPlaneView;

import java.util.ArrayList;
import java.util.BitSet;

/**
 * Created by KhoaBeo on 2/26/2017.
 */
public class PlayerPlaneModel {

    public static final int SPEED = 4;
    private int x;
    private int y;
    private int width;
    private int height;
    private BitSet bitSet;

    public PlayerPlaneModel(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bitSet = new BitSet();
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
}
