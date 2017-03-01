package views;

import utils.Utils;

import java.awt.*;

/**
 * Created by KhoaBeo on 3/2/2017.
 */
public class PlayerRocketView extends GameView {

    private int index;
    private long lastTime;
    private String rocketName;

    public PlayerRocketView(String rocketName) {
        super(Utils.loadImageFromRes(rocketName + "-0.png"));
        this.rocketName = rocketName;
    }

    public void setImage() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime > 200) {
            if (index > 1) {
                index = 0;
            }
            image = Utils.loadImageFromRes(rocketName + "-" + index++ + ".png");
            lastTime = currentTime;
        }
    }
}
