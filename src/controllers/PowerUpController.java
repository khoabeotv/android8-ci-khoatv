package controllers;


import collision.Collision;
import models.GameModel;
import models.PowerUpModel;
import utils.Utils;
import views.GameView;

import java.awt.*;

/**
 * Created by KhoaBeo on 3/1/2017.
 */
public class PowerUpController extends GameController implements Collision {

    public PowerUpController(GameView view, GameModel model) {
        super(view, model);
        CollisionController.instance.add(this);
    }

    public PowerUpController(int x, int y, Image image) {
        this(
                new GameView(image),
                new PowerUpModel(x, y, image.getWidth(null), image.getHeight(null))
        );
    }

    @Override
    public void collide(Collision other) {
        if (other instanceof PlayerPlaneController) {
            model.setAlive(false);
        }
    }

    @Override
    public void run() {
        ((PowerUpModel)model).move();
    }
}
