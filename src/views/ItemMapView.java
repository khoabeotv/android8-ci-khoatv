package views;

import models.ItemMapModel;
import models.PlayerBulletModel;

import java.awt.*;

/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class ItemMapView {

    private Image image;

    public ItemMapView(Image image) {
        this.image = image;
    }

    public void draw(Graphics2D g2d, ItemMapModel model) {
        g2d.drawImage(image, model.getX(), model.getY(), null);
    }
}
