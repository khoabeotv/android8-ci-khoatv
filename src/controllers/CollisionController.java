package controllers;

import collision.Collision;
import models.GameModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KhoaBeo on 3/1/2017.
 */
public class CollisionController {

    public static final CollisionController instance = new CollisionController();

    public List<Collision> collisionList;

    private CollisionController() {
        collisionList = new ArrayList<>();
    }

    public void add(Collision collision) {
        collisionList.add(collision);
    }

    public void remove(Collision collision) {
        collisionList.remove(collision);
    }

    public void checkCollide() {
        for (int i = 0; i < collisionList.size() - 1; i++) {
            Collision collisionI = collisionList.get(i);
            GameModel modelI = collisionI.getModel();
            for (int j = i + 1; j < collisionList.size(); j++) {
                Collision collisionJ = collisionList.get(j);
                GameModel modelJ = collisionJ.getModel();

                if (modelI.intersects(modelJ)) {
                    collisionJ.collide(collisionI);
                    collisionI.collide(collisionJ);
                }
            }
        }
    }
}
