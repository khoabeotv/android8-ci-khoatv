package views;

import utils.Utils;

import java.awt.*;

/**
 * Created by KhoaBeo on 3/4/2017.
 */
public class EnemyWhiteView extends EnemyPlaneView {

    private Animation animation;

    public EnemyWhiteView(String url) {
        super(Utils.loadImageFromRes(url + "-1.png"));
        animation = new Animation(3, 200, url);
    }

    public void setImage() {
        if (animation.getImage() != null) {
            image = animation.getImage();
        } else {
            animation.reload();
        }
    }
}