package views;

import models.GameModel;

import java.awt.*;

/**
 * Created by KhoaBeo on 2/28/2017.
 */
public class GameView {

    protected Image image;

    public GameView(Image image) {
        this.image = image;
    }

    public void draw(Graphics2D g2d, GameModel model) {
        g2d.drawImage(image, model.getX(), model.getY(), null);
    }
}
