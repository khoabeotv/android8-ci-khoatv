package controllers;

import gui.GameFrame;
import models.ItemMapModel;
import utils.Utils;
import views.ItemMapView;

import java.awt.*;
import java.util.Random;

/**
 * Created by KhoaBeo on 2/27/2017.
 */
public class ItemMapController extends GameController {

    public static final int WIDTH_ITEM = GameFrame.WIDTH_F;
    public static final int HEIGHT_ITEM = 70;

    public ItemMapController(ItemMapModel model, ItemMapView view) {
        super(view, model);
    }

    public ItemMapController(int x, int y, Image image) {
        this(new ItemMapModel(x, y, WIDTH_ITEM, HEIGHT_ITEM),
                new ItemMapView(image));
    }

    public ItemMapController(int x, int y) {
        this(new ItemMapModel(x, y,
                        Utils.loadImageFromRes("background.png").getWidth(null),
                        Utils.loadImageFromRes("background.png").getHeight(null)),
                new ItemMapView(Utils.loadImageFromRes("background.png")));
    }

    @Override
    public void run() {
        ((ItemMapModel)model).move();
    }
}
