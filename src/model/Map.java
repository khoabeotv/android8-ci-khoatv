package model;

import gui.GameFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by KhoaBeo on 2/22/2017.
 */
public class Map {

    private ItemMap[] background;
    private ArrayList<ItemMap> itemMaps;
    private ArrayList<Image> itemMapImages;
    private long lastTime;

    public Map(Image backgroundImage, ArrayList<Image> itemMapImages) {
        background = new ItemMap[2];
        background[0] = new ItemMap(0, 0, backgroundImage);
        background[1] = new ItemMap(0, -backgroundImage.getHeight(null), backgroundImage);
        this.itemMapImages = itemMapImages;
        itemMaps = new ArrayList<>();
    }

    private void moveBackground() {
        background[0].setY(background[0].getY() + 1);
        background[1].setY(background[1].getY() + 1);
        if (background[1].getY() == 0) {
            background[0].setY(-background[0].getImage().getHeight(null));
        }
        if (background[0].getY() == 0) {
            background[1].setY(-background[0].getImage().getHeight(null));
        }
    }

    public void drawMap(Graphics2D g2d) {
        moveBackground();
        g2d.drawImage(background[0].getImage(), background[0].getX(), background[0].getY(), null);
        g2d.drawImage(background[1].getImage(), background[1].getX(), background[1].getY(), null);

        addItemMap();
        for(int i = 0; i < itemMaps.size(); i++) {
            ItemMap itemMap = itemMaps.get(i);
            itemMap.setY(itemMap.getY() + 1);
            g2d.drawImage(itemMap.getImage(), itemMap.getX(), itemMap.getY(), null);
            if(itemMap.getY() > GameFrame.HEIGHT_F - 30) {
                itemMaps.remove(i);
            }
        }
    }

    private void addItemMap() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime > 50) {
            Random rd = new Random();
            if (rd.nextInt(50) == 0) {
                itemMaps.add(new ItemMap(rd.nextInt(GameFrame.WIDTH_F), -100, itemMapImages.get(rd.nextInt(itemMapImages.size()))));
            }
            lastTime = currentTime;
        }
    }
}
