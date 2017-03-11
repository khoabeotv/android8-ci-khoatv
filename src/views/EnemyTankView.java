package views;

import utils.Utils;

/**
 * Created by KhoaBeo on 3/11/2017.
 */
public class EnemyTankView extends EnemyView {

    public EnemyTankView() {
        super(Utils.loadImageFromRes("tank.png"));
    }

    public boolean explode() {
        image = Utils.loadImageFromRes("tankexpl.png");
        return true;
    }
}
