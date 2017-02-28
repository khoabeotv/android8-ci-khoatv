package controllers;

import models.GameModel;
import views.GameView;

import java.awt.*;

/**
 * Created by KhoaBeo on 2/28/2017.
 */
public class GameController {

    protected GameView view;
    protected GameModel model;

    public GameController(GameView view, GameModel model) {
        this.view = view;
        this.model = model;
    }

    public void run() {}

    public void draw(Graphics2D g2d) {
        view.draw(g2d, model);
    }
}
