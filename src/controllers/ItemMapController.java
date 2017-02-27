package controllers;

import models.ItemMapModel;
import views.ItemMapView;

import java.awt.*;
import java.util.Random;

/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class ItemMapController {

    private ItemMapModel model;
    private ItemMapView view;

    public ItemMapController(ItemMapModel model, ItemMapView view) {
        this.model = model;
        this.view = view;
    }

    public ItemMapController(int x, int y, Image image) {
        this(new ItemMapModel(x, y, image.getWidth(null), image.getHeight(null)),
                new ItemMapView(image));
    }

    public void run() {
        model.move();
    }

    public ItemMapModel getModel() {
        return model;
    }

    public void draw(Graphics2D g2d) {
        view.draw(g2d, model);
    }
}
