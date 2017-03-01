package views;

import models.GameModel;
import models.ItemMapModel;
import models.PlayerBulletModel;
import utils.Utils;

import java.awt.*;

/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class ItemMapView extends GameView {

    public ItemMapView(Image image) {
        super(image);
    }

    @Override
    public void draw(Graphics2D g2d, GameModel model) {
        g2d.drawImage(image, model.getX(), model.getY(), model.getWidth(), model.getHeight(), null);
    }
}
