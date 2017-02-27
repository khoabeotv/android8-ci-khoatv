package views;

import models.EnemyPlaneModel;
import models.PlayerPlaneModel;

import java.awt.*;

/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class EnemyPlaneView {

    private Image image;

    public EnemyPlaneView(Image image) {
        this.image = image;
    }

    public void draw(Graphics2D g2d, EnemyPlaneModel model) {
        g2d.drawImage(image, model.getX(), model.getY(), model.getWidth(), model.getHeight(), null);
    }
}
