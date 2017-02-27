package views;

import models.EnemyBulletModel;
import models.PlayerBulletModel;

import java.awt.*;

/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class EnemyBulletView {

    private Image image;

    public EnemyBulletView(Image image) {
        this.image = image;
    }

    public void draw(Graphics2D g2d, EnemyBulletModel model) {
        g2d.drawImage(image, model.getX(), model.getY(), model.getWidth(), model.getHeight(), null);
    }
}
