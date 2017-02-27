package controllers;

import models.EnemyPlaneModel;

import org.w3c.dom.css.Rect;
import views.EnemyPlaneView;


import java.awt.*;

/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class EnemyPlaneController {

    private EnemyPlaneModel model;
    private EnemyPlaneView view;

    public EnemyPlaneController(EnemyPlaneModel model, EnemyPlaneView view) {
        this.model = model;
        this.view = view;
    }

    public EnemyPlaneController(int x, int y, Image image, String orient) {
        this(new EnemyPlaneModel(x, y, image.getWidth(null), image.getHeight(null), orient),
                new EnemyPlaneView(image));
    }

    public void run() {
        model.move();
    }

    public Rectangle getRect() {
        return new Rectangle(model.getX(), model.getY(), model.getWidth(), model.getHeight());
    }

    public EnemyPlaneModel getModel() {
        return model;
    }

    public void draw(Graphics2D g2d) {
        view.draw(g2d, model);
    }
}
