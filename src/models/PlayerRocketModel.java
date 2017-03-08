package models;

import controllers.EnemyController;
import controllers.GameController;
import program.GameManager;

/**
 * Created by KhoaBeo on 3/2/2017.
 */
public class PlayerRocketModel extends GameModel {

    public static final int SPEED = 5;

    private GameController enemyPlane;

    public PlayerRocketModel(int x, int y, int width, int height) {
        super(x, y, width, height);
        for (int i = 0; i < GameManager.gameControllers.size(); i++) {
            GameController gameController = GameManager.gameControllers.get(i);
            if (gameController instanceof EnemyController) {
                enemyPlane = gameController;
            }
        }
    }

    @Override
    public boolean outScreen() {
        if (x < -height) {
            return true;
        }
        return false;
    }

    public void move() {
        if (GameManager.gameControllers.contains(enemyPlane) && !((EnemyPlaneModel) enemyPlane.getModel()).isDead()) {
            if (x - 10 > enemyPlane.getModel().x) {
                x -= SPEED;
            } else if (x + 10 < enemyPlane.getModel().x) {
                x += SPEED;
            }

            if (y > enemyPlane.getModel().y) {
                y -= SPEED;
            } else if (y < enemyPlane.getModel().y) {
                y += SPEED;
            }
        } else {
            y -= SPEED;
        }
    }
}
