package controllers;

import collision.Collision;
import models.EnemyModel;
import models.GameModel;
import models.PlayerRocketModel;
import utils.Utils;
import views.GameView;
import views.PlayerRocketView;

/**
 * Created by KhoaBeo on 3/2/2017.
 */
public class PlayerRocketController extends GameController implements Collision {

    public PlayerRocketController(GameView view, GameModel model) {
        super(view, model);
        CollisionController.instance.add(this);
    }

    public PlayerRocketController(int x, int y, String rocketName) {
        this(
                new PlayerRocketView(rocketName),
                new PlayerRocketModel(x, y,
                        Utils.loadImageFromRes(rocketName + "-0.png").getWidth(null),
                        Utils.loadImageFromRes(rocketName + "-0.png").getHeight(null)));
    }

    @Override
    public void run() {
        ((PlayerRocketModel)model).move();
        ((PlayerRocketView)view).setImage();
    }

    @Override
    public void collide(Collision other) {
        if (other instanceof EnemyController) {
            if (!(((EnemyModel)other.getModel()).isDead())) {
                model.setAlive(false);
            }
        }
    }
}
