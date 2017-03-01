package controllers;

import models.GameModel;

/**
 * Created by KhoaBeo on 3/1/2017.
 */
public interface Collision {
    GameModel getModel();
    void collide(Collision other);
}
