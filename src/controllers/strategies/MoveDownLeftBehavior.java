package controllers.strategies;

import models.EnemyPlaneModel;
import models.GameModel;

/**
 * Created by KhoaBeo on 3/9/2017.
 */
public class MoveDownLeftBehavior implements MoveBehavior {
    @Override
    public void move(GameModel model) {
        ((EnemyPlaneModel)model).move(-1, 1);
    }
}
