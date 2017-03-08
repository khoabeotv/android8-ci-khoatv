package utils;

import collision.Collision;
import controllers.CollisionController;
import controllers.GameController;
import program.GameManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by KhoaBeo on 2/26/2017.
 */
public class Utils {
    public static Image loadImageFromRes(String url) {
        try {
            Image image = ImageIO.read(new File("resources/" + url));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void gameRemove(GameController gameController) {
        GameManager.gameControllers.remove(gameController);
        CollisionController.instance.remove((Collision) gameController);
    }
}
