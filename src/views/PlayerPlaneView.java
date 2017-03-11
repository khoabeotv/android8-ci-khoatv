package views;

import gui.GameFrame;
import models.GameModel;
import models.PlayerPlaneModel;

import java.awt.*;

/**
 * Created by KhoaBeo on 2/26/2017.
 */
public class PlayerPlaneView extends GameView {

    private Animation explosion;
    private Image imageTurn;

    public PlayerPlaneView(Image image) {
        super(image);
        explosion = new Animation(15, 100, "expl");
        imageTurn = image;
    }

    @Override
    public void draw(Graphics2D g2d, GameModel model) {
        super.draw(g2d, model);
        for (int i = 0; i < ((PlayerPlaneModel)model).getTurn(); i++) {
            g2d.drawImage(imageTurn, i * 20, 0, 20, 20, null );
        }
        g2d.setFont(new Font(null, Font.BOLD, 15));
        g2d.setColor(Color.white);
        g2d.drawString("Score: " + ((PlayerPlaneModel) model).getScore(), GameFrame.WIDTH_F - 100, 15);
    }

    public boolean explode() {
        Image temp = explosion.getImage();
        if (temp != null) {
            image = temp;
            return true;
        }
        image = imageTurn;
        explosion.reload();
        return false;
    }
}
