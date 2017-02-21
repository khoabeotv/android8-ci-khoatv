import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;

/**
 * Created by KhoaBeo on 2/19/2017.
 */
public class GameWindow extends JFrame implements Runnable {
    private static final int SPEED = 4;
    private static final int WIDTH_F = 600;
    private static final int HEIGHT_F = 800;
    private JPanel panel;
    private Image backgroundImage;
    private Image planeImage;
    private Image enemyPlane;
    private int planeX;
    private int planeY;
    private int enemyX;
    private int enemyY;
    private BitSet bitSet;
    private Thread thread;
    private boolean isRunning;
    private ArrayList<PlayerBullet> arrBullet;


    public GameWindow() {
        setVisible(true);
        setSize(WIDTH_F, HEIGHT_F);
        setLocationRelativeTo(null);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {
                super.windowClosed(windowEvent);
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                super.keyPressed(keyEvent);
                bitSet.set(keyEvent.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased(keyEvent);
                bitSet.clear(keyEvent.getKeyCode());
            }
        });

        arrBullet = new ArrayList<>();
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                Graphics2D g2d = (Graphics2D) graphics;
                super.paintComponent(graphics);
                g2d.drawImage(backgroundImage, 0, 0, WIDTH_F, HEIGHT_F, null);
                g2d.drawImage(planeImage, planeX, planeY, null);
                g2d.drawImage(enemyPlane, enemyX, enemyY, null);
                if(arrBullet.size() > 0) {
                    for (int i = 0; i < arrBullet.size(); i++) {
                        PlayerBullet playerBullet = arrBullet.get(i);
                        g2d.drawImage(playerBullet.getImage(), playerBullet.getX(), playerBullet.getY(), null);
                    }
                }
            }
        };
        panel.setLayout(null);
        panel.setFocusable(true);
        add(panel);

        backgroundImage = loadImageFromRes("background.png");
        planeImage = loadImageFromRes("plane3.png");
        enemyPlane = loadImageFromRes("enemy-green-3.png");

        planeX = (WIDTH_F - planeImage.getWidth(null)) / 2;
        planeY = HEIGHT_F - planeImage.getHeight(null) - 50;
        enemyX = (WIDTH_F - enemyPlane.getWidth(null)) / 2;
        enemyY = 50;
        bitSet = new BitSet(256);

        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private Image loadImageFromRes(String url) {
        try {
            Image image = ImageIO.read(new File("resources/" + url));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void move(String orient) {
        int x = planeX;
        int y = planeY;

        switch (orient) {
            case "RIGHT":
                x += SPEED;
                break;
            case "LEFT":
                x -= SPEED;
                break;
            case "DOWN":
                y += SPEED;
                break;
            case "UP":
                y -= SPEED;
                break;
            default:
                break;
        }

        if (x < 0 || x > WIDTH_F - planeImage.getWidth(null)) {
            planeY = y;
            return;
        }

        if (y < 0 || y > HEIGHT_F - planeImage.getHeight(null) - 30) {
            planeX = x;
            return;
        }

        planeX = x;
        planeY = y;
    }

    @Override
    public void run() {
        while (isRunning) {
            panel.repaint();

            if (bitSet.get(KeyEvent.VK_UP)) {
                move("UP");
            }
            if (bitSet.get(KeyEvent.VK_DOWN)) {
                move("DOWN");
            }
            if (bitSet.get(KeyEvent.VK_RIGHT)) {
                move("RIGHT");
            }
            if (bitSet.get(KeyEvent.VK_LEFT)) {
                move("LEFT");
            }
            if (bitSet.get(KeyEvent.VK_SPACE)) {
                if (arrBullet.size() == 0) {
                    PlayerBullet playerBullet = new PlayerBullet();
                    playerBullet.setImage(loadImageFromRes("bullet.png"));
                    playerBullet.setX((planeImage.getWidth(null
                    ) - playerBullet.getImage().getWidth(null)) / 2 + planeX);
                    playerBullet.setY(planeY - playerBullet.getImage().getHeight(null));
                    arrBullet.add(playerBullet);
                } else if (planeY - arrBullet.get(arrBullet.size() - 1).getY() > 100) {
                    PlayerBullet playerBullet = new PlayerBullet();
                    playerBullet.setImage(loadImageFromRes("bullet.png"));
                    playerBullet.setX((planeImage.getWidth(null
                    ) - playerBullet.getImage().getWidth(null)) / 2 + planeX);
                    playerBullet.setY(planeY - playerBullet.getImage().getHeight(null));
                    arrBullet.add(playerBullet);
                }
            }

            if(arrBullet.size() > 0) {
                for(int i = 0; i < arrBullet.size(); i++) {
                   arrBullet.get(i).moveBullet();
                   if(arrBullet.get(i).getY() < 0) {
                       arrBullet.remove(i);
                   }
                }
            }

            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
