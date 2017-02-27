package views;

import models.PlayerBulletModel;

import java.awt.*;

/**
 * Created by KhoaBeo on 2/26/2017.
 */
public class PlayerBulletView {

    private Image image;

    public PlayerBulletView(Image image) {
        this.image = image;
    }

    public void draw(Graphics2D g2d, PlayerBulletModel model) {
        g2d.drawImage(image, model.getX(), model.getY(), null);
    }
}
