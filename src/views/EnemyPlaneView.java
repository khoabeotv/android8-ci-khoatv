package views;

import models.EnemyPlaneModel;
import models.PlayerPlaneModel;
import utils.Utils;

import javax.rmi.CORBA.Util;
import java.awt.*;

/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class EnemyPlaneView extends GameView {

    private Animation explosion;

    public EnemyPlaneView(Image image) {
        super(image);
        explosion = new Animation(15, 100, "expl");
    }

    public boolean explode() {
        Image temp = explosion.getImage();
        if (temp != null) {
            image = temp;
            return true;
        }
        return false;
    }
}
