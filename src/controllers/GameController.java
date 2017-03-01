package controllers;

import models.GameModel;
import views.GameView;

import java.awt.*;

/**
 * Created by KhoaBeo on 2/28/2017.
 */
public abstract class GameController {

    protected GameView view;
    protected GameModel model;

    public GameController(GameView view, GameModel model) {
        this.view = view;
        this.model = model;
    }

    public abstract void run();

    public boolean outScreen() {
        return model.outScreen();
    }

    public void draw(Graphics2D g2d) {
        view.draw(g2d, model);
    }

    public GameModel getModel() {
        return model;
    }
}
