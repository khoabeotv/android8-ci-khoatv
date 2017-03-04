package views;

import utils.Utils;

import java.awt.*;

/**
 * Created by KhoaBeo on 3/2/2017.
 */
public class PlayerRocketView extends GameView {

    private Animation animation;

    public PlayerRocketView(String rocketName) {
        super(Utils.loadImageFromRes(rocketName + "-0.png"));
        animation = new Animation(4, 200, rocketName);
    }

    public void setImage() {
       if (animation.getImage() != null) {
           image = animation.getImage();
       } else {
           animation.reload();
       }
    }
}
