package views;

import models.PlayerPlaneModel;

import java.awt.*;

/**
 * Created by KhoaBeo on 2/26/2017.
 */
public class PlayerPlaneView {

    private Image image;

    public PlayerPlaneView(Image image) {
        this.image = image;
    }

    public void draw(Graphics2D g2d, PlayerPlaneModel model) {
        g2d.drawImage(image, model.getX(), model.getY(), model.getWidth(), model.getHeight(), null);
    }
}
