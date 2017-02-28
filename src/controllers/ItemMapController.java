package controllers;

import models.ItemMapModel;
import views.ItemMapView;

import java.awt.*;
import java.util.Random;

/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class ItemMapController extends GameController {

    public ItemMapController(ItemMapModel model, ItemMapView view) {
        super(view, model);
    }

    public ItemMapController(int x, int y, Image image) {
        this(new ItemMapModel(x, y, image.getWidth(null), image.getHeight(null)),
                new ItemMapView(image));
    }

    @Override
    public void run() {
        ((ItemMapModel)model).move();
    }

    public ItemMapModel getModel() {
        return (ItemMapModel)model;
    }
}
