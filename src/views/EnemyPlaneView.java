package views;

import models.EnemyPlaneModel;
import models.PlayerPlaneModel;
import utils.Utils;

import javax.rmi.CORBA.Util;
import java.awt.*;

/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class EnemyPlaneView {

    private Image image;
    private long lastTimeExpl;
    private int index;

    public EnemyPlaneView(Image image) {
        this.image = image;
    }

    public void draw(Graphics2D g2d, EnemyPlaneModel model) {
        g2d.drawImage(image, model.getX(), model.getY(), null);
    }

    public boolean explode() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTimeExpl > 100) {
            image = Utils.loadImageFromRes("expl-" + index + ".png");
            if(++index == 14) {
                return false;
            }
            lastTimeExpl = currentTime;
        }
        return true;
    }
}
