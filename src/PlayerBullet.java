import java.awt.*;

/**
 * Created by KhoaBeo on 2/21/2017.
 */
public class PlayerBullet {
    private Image image;
    private int x;
    private int y;
    private int speed;

    public PlayerBullet() {

    }

    public void moveBullet() {
        setY(getY() - 10);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
